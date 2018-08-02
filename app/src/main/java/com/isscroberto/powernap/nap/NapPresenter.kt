package com.isscroberto.powernap.nap

import com.isscroberto.powernap.data.NapType

class NapPresenter (
        private val napView: NapContract.View,
        private val napType: NapType
) : NapContract.Presenter {

    val POWER_NAP_TIME = 1200000
    val REFRESH_NAP_TIME = 3600000
    val RECHARGE_NAP_TIME = 5400000
    val COFFEE_NAP_TIME = 1200000

    init {
        napView.presenter = this
    }

    override fun start() {
        startNap()
    }

    override fun startNap() {
        when(napType) {
            NapType.NAP_TYPE_POWER -> {
                napView.startTimer(POWER_NAP_TIME)
            }
            NapType.NAP_TYPE_REFRESH -> {
                napView.startTimer(REFRESH_NAP_TIME)
            }
            NapType.NAP_TYPE_RECHARGE -> {
                napView.startTimer(RECHARGE_NAP_TIME)
            }
            NapType.NAP_TYPE_COFFEE -> {
                napView.startTimer(COFFEE_NAP_TIME)
            }
        }
    }

    override fun stopNap() {
        napView.stopTimer()
        napView.showSummary()
    }

    override fun finishNap() {
        napView.showStart()
    }

}