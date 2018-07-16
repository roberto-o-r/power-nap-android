package com.isscroberto.powernap.setup

import com.isscroberto.powernap.data.NapType

class SetupPresenter(private val setupView: SetupContract.View) : SetupContract.Presenter {

    override  var napType: NapType = NapType.NULL

    init {
        setupView.presenter = this
    }

    override fun start() {
    }

    override fun selectNapType(napType: NapType) {
        this.napType = napType
        setupView.showDescription(napType)
    }

}