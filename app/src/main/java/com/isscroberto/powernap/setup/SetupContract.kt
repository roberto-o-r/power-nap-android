package com.isscroberto.powernap.setup

import com.isscroberto.powernap.BasePresenter
import com.isscroberto.powernap.BaseView

interface SetupContract {

    interface View : BaseView<Presenter> {
        fun showDescription(napType: Int)
    }

    interface Presenter : BasePresenter {

        fun selectDescription(napType: Int)

    }
}