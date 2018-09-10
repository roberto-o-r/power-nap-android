package com.isscroberto.powernap.nap

import com.isscroberto.powernap.data.NapType

class NapPresenter (
        private val napView: NapContract.View
) : NapContract.Presenter {

    init {
        napView.presenter = this
    }

    override fun start() {
    }

    override fun startNap() {
    }

    override fun stopNap() {
        napView.stopTimer()
        napView.showSummary()
    }

    override fun finishNap() {
        napView.showStart()
    }

}