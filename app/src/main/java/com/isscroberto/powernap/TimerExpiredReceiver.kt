package com.isscroberto.powernap

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.isscroberto.powernap.data.NapState
import com.isscroberto.powernap.util.NotificationUtil
import com.isscroberto.powernap.util.PrefUtil
import com.isscroberto.powernap.util.AlarmService


class TimerExpiredReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // Show notification of expiration.
        NotificationUtil.showTimerExpired(context)

        // Save status in preferences.
        PrefUtil.setTimerState(NapState.Finished, context)
        PrefUtil.setAlarmSetTime(0, context)

        // Play alarm sound.
        context.startService(Intent(context, AlarmService::class.java))

    }
}
