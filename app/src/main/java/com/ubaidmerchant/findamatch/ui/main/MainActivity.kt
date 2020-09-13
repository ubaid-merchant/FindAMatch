package com.ubaidmerchant.findamatch.ui.main

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaidmerchant.findamatch.R
import com.ubaidmerchant.findamatch.databinding.ActivityMainBinding
import com.ubaidmerchant.findamatch.model.ResultsModel
import com.ubaidmerchant.findamatch.model.State
import com.ubaidmerchant.findamatch.ui.base.BaseActivity
import com.ubaidmerchant.findamatch.ui.main.adapter.CardListAdapter
import com.ubaidmerchant.findamatch.utils.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    override val mViewModel: MainViewModel by viewModels()

    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    private val mAdapter = CardListAdapter(this::onItemClicked)

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme) // Set AppTheme before setting content view.
        super.onCreate(savedInstanceState)
        setContentView(mViewBinding.root)

        // Initialize RecyclerView
        mViewBinding.cardsRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mAdapter
        }

        initResults()
        handleNetworkChanges()
    }

    private fun initResults() {
        mViewModel.resultsLiveData.observe(
            this,
            Observer { state ->
                when (state) {
                    is State.Loading -> showLoading(true)
                    is State.Success -> {
                        if (state.data.isNotEmpty()) {
                            mAdapter.submitList(state.data.toMutableList())
                            showLoading(false)
                        }
                    }
                    is State.Error -> {
                        showToast(state.message)
                        showLoading(false)
                    }
                }
            }
        )

        mViewBinding.swipeRefreshLayout.setOnRefreshListener {
            getResults()
        }
    }

    private fun getResults() {
        mViewModel.getResults()
    }

    private fun showLoading(isLoading: Boolean) {
        mViewBinding.swipeRefreshLayout.isRefreshing = isLoading
    }

    /**
     * Observe network changes i.e. Internet Connectivity
     */
    private fun handleNetworkChanges() {
        NetworkUtils.getNetworkLiveData(applicationContext).observe(
            this,
            Observer { isConnected ->
                if (!isConnected) {
                    mViewBinding.textViewNetworkStatus.text =
                        getString(R.string.text_no_connectivity)
                    mViewBinding.networkStatusLayout.apply {
                        visible()
                        setBackgroundColor(getColorRes(R.color.colorStatusNotConnected))
                    }
                } else {
                    if (mViewModel.resultsLiveData.value is State.Error || mAdapter.itemCount == 0) {
                        getResults()
                    }
                    mViewBinding.textViewNetworkStatus.text = getString(R.string.text_connectivity)
                    mViewBinding.networkStatusLayout.apply {
                        setBackgroundColor(getColorRes(R.color.colorStatusConnected))

                        animate()
                            .alpha(1f)
                            .setStartDelay(ANIMATION_DURATION)
                            .setDuration(ANIMATION_DURATION)
                            .setListener(object : AnimatorListenerAdapter() {
                                override fun onAnimationEnd(animation: Animator) {
                                    gone()
                                }
                            }
                            )
                    }
                }
            }
        )
    }

    private fun onItemClicked(result: ResultsModel, status: Boolean) {
        mViewModel.updateResult(result, status)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_theme -> {
                // Get new mode.
                val mode =
                    if ((resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK)
                        == Configuration.UI_MODE_NIGHT_NO
                    ) {
                        AppCompatDelegate.MODE_NIGHT_YES
                    } else {
                        AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY
                    }

                // Change UI Mode
                AppCompatDelegate.setDefaultNightMode(mode)
                true
            }

            else -> true
        }
    }

    override fun onBackPressed() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.exit_dialog_title))
            .setMessage(getString(R.string.exit_dialog_message))
            .setPositiveButton(getString(R.string.option_yes)) { dialogInterface, _ ->
                dialogInterface.dismiss()
                super.onBackPressed()
            }
            .setNegativeButton(getString(R.string.option_no)) { dialogInterface, _ ->
                dialogInterface.dismiss()
            }
            .show()
    }

    companion object {
        const val ANIMATION_DURATION = 1000.toLong()
    }
}