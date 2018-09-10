package com.isscroberto.powernap.nap

import com.isscroberto.powernap.BasePresenter
import com.isscroberto.powernap.BaseView

interface NapContract {

    interface View : BaseView<Presenter> {

        fun initTimer()

        fun startTimer(milliseconds: Int)

        fun stopTimer()

        fun showSummary()

        fun showStart()

        fun onTimerFinished()

    }

    interface Presenter : BasePresenter {

        fun startNap()

        fun stopNap()

        fun finishNap()

    }

}