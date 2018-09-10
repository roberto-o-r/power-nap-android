package com.isscroberto.powernap.setup


import android.content.Intent
import android.os.Bundle
import android.support.transition.TransitionManager
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.isscroberto.powernap.R
import com.isscroberto.powernap.data.*
import com.isscroberto.powernap.nap.NapActivity
import com.isscroberto.powernap.util.PrefUtil
import kotlinx.android.synthetic.main.fragment_setup.*

/**
 * Setup section view.
 */
class SetupFragment : Fragment(), SetupContract.View {

    override lateinit var presenter: SetupContract.Presenter

    override fun onResume() {
        super.onResume()
        presenter.start();
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setup, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set click listeners for nap types.
        layout_power.setOnClickListener(View.OnClickListener {
            if(presenter.napType == NapType.NULL) {
                presenter.selectNapType(NapType.NAP_TYPE_POWER);
            } else {
                presenter.selectNapType(NapType.NULL);
            }
        })

        layout_refresh.setOnClickListener(View.OnClickListener {
            if(presenter.napType == NapType.NULL) {
                presenter.selectNapType(NapType.NAP_TYPE_REFRESH);
            } else {
                presenter.selectNapType(NapType.NULL);
            }
        })

        layout_recharge.setOnClickListener(View.OnClickListener {
            if(presenter.napType == NapType.NULL) {
                presenter.selectNapType(NapType.NAP_TYPE_RECHARGE);
            } else {
                presenter.selectNapType(NapType.NULL);
            }
        })

        layout_coffee.setOnClickListener(View.OnClickListener {
            if(presenter.napType == NapType.NULL) {
                presenter.selectNapType(NapType.NAP_TYPE_COFFEE);
            } else {
                presenter.selectNapType(NapType.NULL);
            }
        })

        // Click listeners for nap type selections.
        button_power.setOnClickListener(View.OnClickListener {
            // Save nap in preferences.
            PrefUtil.setNapType(presenter.napType, context);

            val intent = Intent(context, NapActivity::class.java)
            startActivity(intent)
        })

        button_refresh.setOnClickListener(View.OnClickListener {
            // Save nap in preferences.
            PrefUtil.setNapType(presenter.napType, context);

            val intent = Intent(context, NapActivity::class.java)
            startActivity(intent)
        })

        button_recharge.setOnClickListener(View.OnClickListener {
            // Save nap in preferences.
            PrefUtil.setNapType(presenter.napType, context);

            val intent = Intent(context, NapActivity::class.java)
            startActivity(intent)
        })

        button_coffee.setOnClickListener(View.OnClickListener {
            // Save nap in preferences.
            PrefUtil.setNapType(presenter.napType, context);

            val intent = Intent(context, NapActivity::class.java)
            startActivity(intent)
        })
    }

    override fun showDescription(napType: NapType) {

        TransitionManager.beginDelayedTransition(setup_fragment_content);

        when(napType){
            NapType.NAP_TYPE_POWER -> {
                layout_refresh.visibility = View.GONE
                layout_recharge.visibility = View.GONE
                layout_coffee.visibility = View.GONE
            }
            NapType.NAP_TYPE_REFRESH -> {
                layout_power.visibility = View.GONE
                layout_recharge.visibility = View.GONE
                layout_coffee.visibility = View.GONE
            }
            NapType.NAP_TYPE_RECHARGE -> {
                layout_power.visibility = View.GONE
                layout_refresh.visibility = View.GONE
                layout_coffee.visibility = View.GONE
            }
            NapType.NAP_TYPE_COFFEE -> {
                layout_power.visibility = View.GONE
                layout_refresh.visibility = View.GONE
                layout_recharge.visibility = View.GONE
            }
            NapType.NULL -> {
                layout_power.visibility = View.VISIBLE
                layout_refresh.visibility = View.VISIBLE
                layout_recharge.visibility = View.VISIBLE
                layout_coffee.visibility = View.VISIBLE
            }
        }
    }

    companion object {
        fun newInstance():SetupFragment {
            return SetupFragment()
        }
    }

}
