package com.isscroberto.powernap

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.isscroberto.powernap.data.NapState
import com.isscroberto.powernap.util.NotificationUtil
import com.isscroberto.powernap.util.PrefUtil

class TimerExpiredReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        NotificationUtil.showTimerExpired(context)

        PrefUtil.setTimerState(NapState.Finished, context)
        PrefUtil.setAlarmSetTime(0, context)
    }
}
