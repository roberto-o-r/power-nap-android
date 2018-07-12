package com.isscroberto.powernap.setup

import com.isscroberto.powernap.BasePresenter
import com.isscroberto.powernap.BaseView
import com.isscroberto.powernap.data.NapType

interface SetupContract {

    interface View : BaseView<Presenter> {
        fun showDescription(napType: NapType)
    }

    interface Presenter : BasePresenter {

        var napType: NapType

        fun selectNapType(napType: NapType)

    }
}