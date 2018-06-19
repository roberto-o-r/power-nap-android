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

        // Setup ActionBar.
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        // Add fragment.
        val setupFragment = supportFragmentManager.findFragmentById(R.id.contentFrame) as SetupFragment? ?:
        SetupFragment.newInstance().also {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.contentFrame, it)
            transaction.commit()
        }

        // Create the presenter
        SetupPresenter(setupFragment)
    }
}
