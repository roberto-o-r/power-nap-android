package com.isscroberto.powernap.start

import com.isscroberto.powernap.BasePresenter
import com.isscroberto.powernap.BaseView

interface StartContract {

    interface View : BaseView<Presenter> {

        fun navigateToSetup()

    }

    interface Presenter : BasePresenter {

        fun startSession()

    }

}