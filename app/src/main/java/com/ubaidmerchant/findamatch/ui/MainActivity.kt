package com.ubaidmerchant.findamatch.ui

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.ubaidmerchant.findamatch.R
import com.ubaidmerchant.findamatch.utils.NetworkUtils
import com.ubaidmerchant.findamatch.utils.getColorRes
import com.ubaidmerchant.findamatch.utils.hide
import com.ubaidmerchant.findamatch.utils.show
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme) // Set AppTheme before setting content view.
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        handleNetworkChanges()
    }

    /**
     * Observe network changes i.e. Internet Connectivity
     */
    private fun handleNetworkChanges() {
        NetworkUtils.getNetworkLiveData(applicationContext).observe(
            this,
            Observer { isConnected ->
                if (!isConnected) {
                    textViewNetworkStatus.text =
                        getString(R.string.text_no_connectivity)
                    networkStatusLayout.apply {
                        show()
                        setBackgroundColor(getColorRes(R.color.colorStatusNotConnected))
                    }
                } else {
                    textViewNetworkStatus.text = getString(R.string.text_connectivity)
                    networkStatusLayout.apply {
                        setBackgroundColor(getColorRes(R.color.colorStatusConnected))

                        animate()
                            .alpha(1f)
                            .setStartDelay(ANIMATION_DURATION)
                            .setDuration(ANIMATION_DURATION)
                            .setListener(object : AnimatorListenerAdapter() {
                                override fun onAnimationEnd(animation: Animator) {
                                    hide()
                                }
                            }
                            )
                    }
                }
            }
        )
    }

    companion object {
        const val ANIMATION_DURATION = 1000.toLong()
    }
}