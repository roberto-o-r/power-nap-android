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
    }
}
