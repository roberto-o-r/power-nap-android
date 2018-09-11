package com.isscroberto.powernap.util

import android.content.ComponentName
import android.content.Context
import android.preference.PreferenceManager
import com.isscroberto.powernap.data.AppConstants
import com.isscroberto.powernap.data.NapState
import com.isscroberto.powernap.data.NapType

class PrefUtil {

    companion object {

        fun getTimerLength(context: Context): Int {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            val napType: NapType = NapType.fromInt(preferences.getInt(NAP_TYPE, 0)) as NapType
            when(napType) {
                NapType.NAP_TYPE_POWER -> return AppConstants.POWER_NAP_TIME
                NapType.NAP_TYPE_REFRESH -> return AppConstants.REFRESH_NAP_TIME
                NapType.NAP_TYPE_RECHARGE -> return AppConstants.RECHARGE_NAP_TIME
                NapType.NAP_TYPE_COFFEE -> return AppConstants.COFFEE_NAP_TIME
                else -> return 1
            }
        }

        fun getNapType(context: Context): NapType {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            val napType: NapType = NapType.fromInt(preferences.getInt(NAP_TYPE, 0)) as NapType
            return napType
        }

        private const val NAP_TYPE = "com.isscroberto.powernap.nap_type";

        fun setNapType(napType: NapType, context: Context) {
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            editor.putInt(NAP_TYPE, napType.value)
            editor.apply()
        }

        private const val PREVIOUS_TIMER_LENGTH_SECONDS_ID = "com.isscroberto.powernap.previous_timer_length"

        fun getPreviousTimerLengthSeconds(context: Context): Long {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            return preferences.getLong(PREVIOUS_TIMER_LENGTH_SECONDS_ID, 0)
        }

        fun setPreviousTimerLengthSeconds(seconds: Long, context: Context) {
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            editor.putLong(PREVIOUS_TIMER_LENGTH_SECONDS_ID, seconds)
            editor.apply()
        }

        private const val TIMER_STATE_ID = "com.isscroberto.powernap.timer_state"

        fun getTimerState(context: Context): NapState {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            val ordinal = preferences.getInt(TIMER_STATE_ID, 0)
            return NapState.values()[ordinal]
        }

        fun setTimerState(state: NapState, context: Context) {
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            val ordinal = state.ordinal
            editor.putInt(TIMER_STATE_ID, ordinal)
            editor.apply()
        }

        private const val SECONDS_REMAINING_ID = "com.isscroberto.powernap.seconds_remaining"

        fun getSecondsRemaining(context: Context): Long {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            return preferences.getLong(SECONDS_REMAINING_ID, 0)
        }

        fun setSecondsRemaining(seconds: Long, context: Context) {
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            editor.putLong(SECONDS_REMAINING_ID, seconds)
            editor.apply()
        }

        private const val ALARM_SET_TIME_ID = "com.isscroberto.powernap.backgrounded_time"

        fun getAlarmSetTime(context: Context): Long {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            return preferences.getLong(ALARM_SET_TIME_ID, 0)
        }

        fun setAlarmSetTime(time: Long, context: Context) {
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            editor.putLong(ALARM_SET_TIME_ID, time)
            editor.apply()
        }

    }

}