package com.isscroberto.powernap.nap

class NapPresenter (
        private val startView: NapContract.View,
        private val type: Int
) : NapContract.Presenter {


    init {
        startView.presenter = this
    }

    override fun start() {
        when(type) {
            1 -> {
                startView.startCountdown(10000)
            }
        }
    }

    override fun startNap() {
    }

    override fun stopNap() {
    }

    override fun finishNap() {
    }

}