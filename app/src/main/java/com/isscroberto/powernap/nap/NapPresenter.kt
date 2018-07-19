package com.isscroberto.powernap.nap

import com.isscroberto.powernap.data.NapType

class NapPresenter (
        private val startView: NapContract.View,
        private val napType: NapType
) : NapContract.Presenter {

    val POWER_NAP_TIME = 1200000
    val REFRESH_NAP_TIME = 3600000

    init {
        startView.presenter = this
    }

    override fun start() {
        startNap()
    }

    override fun startNap() {
        when(napType) {
            NapType.NAP_TYPE_POWER -> {
                startView.startCountdown(POWER_NAP_TIME)
            }
            NapType.NAP_TYPE_REFRESH -> {
                startView.startCountdown(REFRESH_NAP_TIME)
            }
            else -> {}
        }
    }

    override fun stopNap() {
    }

    override fun finishNap() {
    }

}