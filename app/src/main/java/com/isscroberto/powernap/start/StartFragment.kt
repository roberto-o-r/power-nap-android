package com.isscroberto.powernap.start


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.isscroberto.powernap.R
import com.isscroberto.powernap.nap.NapActivity
import com.isscroberto.powernap.setup.SetupActivity
import kotlinx.android.synthetic.main.fragment_start.*

/**
 * Start fragment view.
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

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view_nap.setOnClickListener(View.OnClickListener {
            presenter.startSession()
        })
    }

    override fun navigateToSetup() {
        val intent = Intent(context, NapActivity::class.java)
        startActivity(intent)
    }

    companion object {
        fun newInstance():StartFragment {
            return StartFragment()
        }
    }

}
