package com.isscroberto.powernap.setup

class SetupPresenter(private val setupView: SetupContract.View) : SetupContract.Presenter {

    init {
        setupView.presenter = this
    }

    override fun start() {
    }

    override fun selectDescription(napType: Int) {
        setupView.showDescription(napType)
    }

}