package com.isscroberto.powernap.nap


import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.os.CountDownTimer
import android.support.transition.TransitionManager
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.isscroberto.powernap.R
import com.isscroberto.powernap.start.StartActivity
import kotlinx.android.synthetic.main.fragment_nap.*
import kotlinx.android.synthetic.main.fragment_setup.*

/**
 * Nap fragment view.
 */
class NapFragment : Fragment(), NapContract.View {

    // TODO: Test functionality.

    override lateinit var presenter: NapContract.Presenter

    lateinit var timer: CountDownTimer

    override fun onResume() {
        super.onResume()
        presenter.start();
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nap, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        layout_summary
    }

    override fun startCountdown(milliseconds: Int) {
        timer = object : CountDownTimer(milliseconds.toLong(), 1000) {
            override fun onFinish() {
                presenter.stopNap()
            }

            override fun onTick(millisUntilFinished: Long) {
                text_countdown?.text = toMinutesAndSeconds(millisUntilFinished)
            }
        }.start()
    }

    override fun stopCountdown() {
        timer.cancel()
    }

    override fun showSummary() {
        TransitionManager.beginDelayedTransition(nap_fragment_content);
        layout_summary.visibility = View.VISIBLE
    }

    override fun showStart() {
        val intent = Intent(context, StartActivity::class.java)
        startActivity(intent)
    }

    private fun toMinutesAndSeconds(milliseconds: Long): String {
        val minutes = (milliseconds / 1000 / 60).toString()
        val seconds = (milliseconds / 1000 % 60).toString()
        return minutes.padStart(2, '0') + ":" + seconds.padStart(2, '0')
    }

    companion object {
        fun newInstance():NapFragment {
            return NapFragment()
        }
    }

}
