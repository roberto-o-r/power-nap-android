package com.isscroberto.powernap.nap

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.isscroberto.powernap.R

class NapActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nap)

        // Add fragment.
        val napFragment = supportFragmentManager.findFragmentById(R.id.contentFrame) as NapFragment? ?:
        NapFragment.newInstance().also {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.contentFrame, it)
            transaction.commit()
        }

        // Create the presenter
        NapPresenter(napFragment, 1)
    }
}
