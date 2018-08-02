package com.isscroberto.powernap.nap


import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.os.CountDownTimer
import android.support.transition.TransitionManager
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.isscroberto.powernap.R
import com.isscroberto.powernap.TimerExpiredReceiver
import com.isscroberto.powernap.data.NapState
import com.isscroberto.powernap.data.NapType
import com.isscroberto.powernap.start.StartActivity
import com.isscroberto.powernap.util.PrefUtil
import kotlinx.android.synthetic.main.fragment_nap.*
import kotlinx.android.synthetic.main.fragment_setup.*
import java.util.*

/**
 * Nap fragment view.
 */
class NapFragment : Fragment(), NapContract.View {

    override lateinit var presenter: NapContract.Presenter

    private lateinit var timer: CountDownTimer
    private var timerLengthSeconds = 0L
    private var timerState = NapState.Stopped
    private var secondsRemaining = 0L

    override fun onResume() {
        super.onResume()
        //presenter.start();

        initTimer()

        removeAlarm(context)
        // TODO: Hide notification.
    }

    override fun onPause() {
        super.onPause()

        if(timerState == NapState.Running) {
            timer.cancel()
            val wakeUpTime = setAlarm(context, nowSeconds, secondsRemaining)
            // TODO: Show notification.
        }

        PrefUtil.setPreviousTimerLengthSeconds(timerLengthSeconds, context)
        PrefUtil.setSecondsRemaining(secondsRemaining, context)
        PrefUtil.setTimerState(timerState, context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nap, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        text_countdown.setOnClickListener(View.OnClickListener {
            startTimer(0)
        })
    }

    override fun initTimer() {
        timerState = PrefUtil.getTimerState(context)

        if(timerState == NapState.Stopped) {
            setNewTimerLength()
        } else {
            setPreviousTimerLength()
        }

        secondsRemaining = if(timerState == NapState.Running)
            PrefUtil.getPSecondsRemaining(context)
        else
            timerLengthSeconds

        val alarmSetTime = PrefUtil.getAlarmSetTime(context)
        if(alarmSetTime > 0) {
            secondsRemaining -= nowSeconds - alarmSetTime
        }

        if(secondsRemaining <= 0)
            onTimerFinished()
        else {
            if (timerState == NapState.Running)
                startTimer(0)
            else
                onTimerFinished()
        }

        updateCountdownUI()
    }

    override fun startTimer(milliseconds: Int) {
        timerState = NapState.Running

        timer = object : CountDownTimer(secondsRemaining * 1000, 1000) {
            override fun onFinish() {
                onTimerFinished()
            }

            override fun onTick(millisUntilFinished: Long) {
                secondsRemaining = millisUntilFinished / 1000;
                updateCountdownUI();
            }
        }.start()
    }

    override fun stopTimer() {
        timerState = NapState.Stopped

        timer.cancel()
    }

    override fun showSummary() {
        TransitionManager.beginDelayedTransition(nap_fragment_content);
        layout_summary.visibility = View.VISIBLE
    }

    override fun showStart() {
        val intent = Intent(context, StartActivity::class.java)
        startActivity(intent)
    }

    private fun onTimerFinished() {
        timerState = NapState.Stopped

        setNewTimerLength()

        PrefUtil.setSecondsRemaining(timerLengthSeconds, context)
        secondsRemaining = timerLengthSeconds

        updateCountdownUI()

        presenter.stopNap()
    }

    private fun setNewTimerLength() {
        val lengthInMinutes = PrefUtil.getTimerLength(context)
        timerLengthSeconds = (lengthInMinutes * 60L)
    }

    private fun setPreviousTimerLength() {
        timerLengthSeconds = PrefUtil.getPreviousTimerLengthSeconds(context)
    }

    private fun updateCountdownUI() {
        text_countdown.text = toMinutesAndSeconds(secondsRemaining * 1000)
    }

    private fun toMinutesAndSeconds(milliseconds: Long): String {
        val minutes = (milliseconds / 1000 / 60).toString()
        val seconds = (milliseconds / 1000 % 60).toString()
        return minutes.padStart(2, '0') + ":" + seconds.padStart(2, '0')
    }

    companion object {
        fun newInstance():NapFragment {
            return NapFragment()
        }

        fun setAlarm(context: Context, nowSeconds: Long, secondsRemaining: Long): Long {
            val wakeUpTime = (nowSeconds + secondsRemaining) * 1000
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val intent = Intent(context, TimerExpiredReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)

            alarmManager.setExact(AlarmManager.RTC_WAKEUP, wakeUpTime, pendingIntent)
            PrefUtil.setAlarmSetTime(nowSeconds, context)
            return wakeUpTime
        }

        fun removeAlarm(context: Context) {
            val intent = Intent(context, TimerExpiredReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

            alarmManager.cancel(pendingIntent)
            PrefUtil.setAlarmSetTime(0, context)
        }

        val nowSeconds: Long
            get() = Calendar.getInstance().timeInMillis / 1000
    }

}
