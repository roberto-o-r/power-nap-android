package com.isscroberto.powernap.start

class StartPresenter (
        private val startView: StartContract.View
) : StartContract.Presenter {

    init {
        startView.presenter = this
    }

    override fun start() {

    }

    override fun startSession() {
        startView.navigateToSetup()
    }

}