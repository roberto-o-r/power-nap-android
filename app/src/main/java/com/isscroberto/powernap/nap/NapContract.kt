package com.isscroberto.powernap.nap

import com.isscroberto.powernap.BasePresenter
import com.isscroberto.powernap.BaseView

interface NapContract {

    interface View : BaseView<Presenter> {

        fun startCountdown(milliseconds: Int)

        fun stopCountdown()

        fun showSummary()

        fun showStart()

    }

    interface Presenter : BasePresenter {

        fun startNap()

        fun stopNap()

        fun finishNap()

    }

}