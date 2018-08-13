package com.isscroberto.powernap

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.isscroberto.powernap.data.AppConstants
import com.isscroberto.powernap.data.NapState
import com.isscroberto.powernap.nap.NapActivity
import com.isscroberto.powernap.nap.NapFragment
import com.isscroberto.powernap.util.NotificationUtil
import com.isscroberto.powernap.util.PrefUtil

class TimerNotificationActionReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        when(intent.action) {
            AppConstants.ACTION_STOP -> {
                NapFragment.removeAlarm(context)
                PrefUtil.setTimerState(NapState.Stopped, context)
                NotificationUtil.hideTimerNotification(context)
            }
            AppConstants.ACTION_PAUSE -> {
                var secondsRemaining = PrefUtil.getSecondsRemaining(context)
                val alarmSetTime = PrefUtil.getAlarmSetTime(context)
                val nowSeconds = NapFragment.nowSeconds

                secondsRemaining -= nowSeconds - alarmSetTime
                PrefUtil.setSecondsRemaining(secondsRemaining, context)

                NapFragment.removeAlarm(context)
                PrefUtil.setTimerState(NapState.Paused, context)
                NotificationUtil.showTimerPaused(context)
            }
            AppConstants.ACTION_RESUME -> {
                val secondsRemaining = PrefUtil.getSecondsRemaining(context)
                val wakeUpTime = NapFragment.setAlarm(context, NapFragment.nowSeconds, secondsRemaining)
                PrefUtil.setTimerState(NapState.Running, context)
                NotificationUtil.showTimerRunning(context, wakeUpTime)
            }
            AppConstants.ACTION_START -> {
                val minutesRemaining = PrefUtil.getTimerLength(context)
                val secondsRemaining = minutesRemaining * 60L
                val wakeUpTime = NapFragment.setAlarm(context, NapFragment.nowSeconds, secondsRemaining)
                PrefUtil.setTimerState(NapState.Running, context)
                PrefUtil.setSecondsRemaining(secondsRemaining, context)
                NotificationUtil.showTimerRunning(context, wakeUpTime)
            }
        }
    }
}
