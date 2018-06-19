package com.isscroberto.powernap.nap


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.isscroberto.powernap.R

/**
 * Nap fragment view.
 */
class NapFragment : Fragment(), NapContract.View {

    override lateinit var presenter: NapContract.Presenter

    override fun onResume() {
        super.onResume()
        presenter.start();
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nap, container, false)
    }

    override fun startCountdown(milliseconds: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun stopCountdown() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun goToEnd() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        fun newInstance():NapFragment {
            return NapFragment()
        }
    }

}
