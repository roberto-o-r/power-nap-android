package com.isscroberto.powernap.setup

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.transition.TransitionManager
import android.view.View
import com.isscroberto.powernap.R
import kotlinx.android.synthetic.main.activity_setup.*

class SetupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setup)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        layout_power.setOnClickListener(View.OnClickListener {
            TransitionManager.beginDelayedTransition(layout_content);
            layout_refresh.visibility = View.GONE
            layout_recharge.visibility = View.GONE
            layout_coffee.visibility = View.GONE
        })
    }
}
