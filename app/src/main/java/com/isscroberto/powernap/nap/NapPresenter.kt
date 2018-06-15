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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun stopNap() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun finishNap() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}