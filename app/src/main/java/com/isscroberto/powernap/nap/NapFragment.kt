package com.isscroberto.powernap.nap


import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
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
import com.isscroberto.powernap.util.AlarmService
import com.isscroberto.powernap.util.NotificationUtil
import com.isscroberto.powernap.util.PrefUtil
import kotlinx.android.synthetic.main.fragment_nap.*
import java.util.*
import android.app.ActivityManager



/**
 * Nap fragment view.
 */
class NapFragment : Fragment(), NapContract.View {

    // TODO: Add feedback.
    // TODO: Add advertising.

    override lateinit var presenter: NapContract.Presenter

    private lateinit var timer: CountDownTimer
    private var timerLengthSeconds = 0L
    private var timerState = NapState.Stopped
    private var secondsRemaining = 0L

    override fun onResume() {
        super.onResume()

        // Start presenter.
        presenter.start()

        // Initialize timer.
        initTimer()

        // The app is in focus, remove the alarm and notification if exists.
        removeAlarm(context)
        NotificationUtil.hideTimerNotification(context)
    }

    override fun onPause() {
        super.onPause()

        // If nap is running.
        if (timerState == NapState.Running){
            // Cancerl de timer.
            timer.cancel()
            // Set the alarm and notification.
            val wakeUpTime = setAlarm(context, nowSeconds, secondsRemaining)
            NotificationUtil.showTimerRunning(context, wakeUpTime)
        }

        // Save settings and time status.
        PrefUtil.setPreviousTimerLengthSeconds(timerLengthSeconds, context)
        PrefUtil.setSecondsRemaining(secondsRemaining, context)
        PrefUtil.setTimerState(timerState, context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nap, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        text_start.setOnClickListener(View.OnClickListener {
            startTimer(0)
        })

        layout_summary.setOnClickListener(View.OnClickListener {
            showStart()
        })

        // Set UI according to Nap.
        var napType = PrefUtil.getNapType(context)
        when(napType) {
            NapType.NAP_TYPE_POWER -> {
                nap_fragment_content.setBackgroundColor(resources.getColor(R.color.power))
            }
            NapType.NAP_TYPE_REFRESH -> {
                nap_fragment_content.setBackgroundColor(resources.getColor(R.color.refresh))
            }
            NapType.NAP_TYPE_RECHARGE -> {
                nap_fragment_content.setBackgroundColor(resources.getColor(R.color.recharge))
            }
            NapType.NAP_TYPE_COFFEE -> {
                text_instruction.visibility = View.VISIBLE
                nap_fragment_content.setBackgroundColor(resources.getColor(R.color.coffee))
            }
        }
    }

    override fun initTimer() {
        timerState = PrefUtil.getTimerState(context)

        if(timerState == NapState.Stopped) {
            setNewTimerLength()
        } else {
            setPreviousTimerLength()
        }

        secondsRemaining = if(timerState == NapState.Running)
            PrefUtil.getSecondsRemaining(context)
        else
            timerLengthSeconds

        val alarmSetTime = PrefUtil.getAlarmSetTime(context)
        if(alarmSetTime > 0) {
            secondsRemaining -= nowSeconds - alarmSetTime
        }

        if(timerState == NapState.Finished) {
            // Finish nap.
            onTimerFinished()
        }
        else {
            if (timerState == NapState.Running)
                startTimer(0)
        }

        updateCountdownUI()
    }

    override fun startTimer(milliseconds: Int) {
        text_start.visibility = View.INVISIBLE;
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
        try {
            timer.cancel()
        } catch (e: Exception) {}
    }

    override fun showSummary() {
        TransitionManager.beginDelayedTransition(nap_fragment_content);
        layout_summary.visibility = View.VISIBLE
    }

    override fun showStart() {
        // Stop alarm.
        context.stopService(Intent(context, AlarmService::class.java))

        val intent = Intent(context, StartActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        startActivity(intent)
    }

    override fun onTimerFinished() {
        // Set state of nap as finished.
        timerState = NapState.Finished

        // Play alarm sound if not playing already.
        if(!serviceRunning(AlarmService::class.java)) {
            context.startService(Intent(context, AlarmService::class.java))
        }

        // Trigger nap stop.
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

    private fun serviceRunning(serviceClass: Class<*>): Boolean {
        val manager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (service in manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.name == service.service.className) {
                return true
            }
        }
        return false
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
