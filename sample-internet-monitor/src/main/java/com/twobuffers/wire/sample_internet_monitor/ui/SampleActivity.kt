package com.twobuffers.wire.sample_internet_monitor.ui

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.twobuffers.wire.sample_internet_monitor.databinding.ActivitySampleBinding
import com.twobuffers.wire.utils.android.Config
import com.twobuffers.wire.utils.android.InternetMonitor
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class SampleActivity : DaggerAppCompatActivity() {
    private val binding by lazy { ActivitySampleBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        showInternetStatus()
    }

    private fun showInternetStatus() {
        InternetMonitor.init(Config(applicationContext))
        // taking value once:
        InternetMonitor.status
            .onEach { binding.statusValue.text = "${it::class.simpleName}" }
            .launchIn(lifecycleScope)
        InternetMonitor.connected
            .onEach { binding.connectedValue.text = "$it" }
            .launchIn(lifecycleScope)
    }
}
