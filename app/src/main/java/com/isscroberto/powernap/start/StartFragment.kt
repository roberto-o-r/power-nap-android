package com.isscroberto.powernap.start


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.isscroberto.powernap.R

/**
 * Start activity view.
 */
class StartFragment : Fragment(), StartContract.View {

    override lateinit var presenter: StartContract.Presenter

    override fun onResume() {
        super.onResume()
        presenter.start();
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun navigateToSetup() {

    }

    companion object {
        fun newInstance():StartFragment {
            return StartFragment()
        }
    }

}
