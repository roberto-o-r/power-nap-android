package com.isscroberto.powernap.start

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.isscroberto.powernap.R

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        // Add fragment.
        val startFragment = supportFragmentManager.findFragmentById(R.id.contentFrame) as StartFragment? ?:
        StartFragment.newInstance().also {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.contentFrame, it)
            transaction.commit()
        }

        // Create the presenter
        StartPresenter(startFragment)
    }
}
