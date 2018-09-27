package com.isscroberto.powernap.start

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.isscroberto.powernap.R
import com.github.stkent.amplify.tracking.Amplify
import com.github.stkent.amplify.prompt.DefaultLayoutPromptView
import kotlinx.android.synthetic.main.activity_start.*


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

        // Feedback.
        if (savedInstanceState == null) {
            var promptView = prompt_view as DefaultLayoutPromptView
            Amplify.getSharedInstance().promptIfReady(promptView)
        }

        // Create the presenter
        StartPresenter(startFragment)
    }
}
