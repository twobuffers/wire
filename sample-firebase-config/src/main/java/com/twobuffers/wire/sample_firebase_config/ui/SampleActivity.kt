package com.twobuffers.wire.sample_firebase_config.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.twobuffers.wire.firebase_config.FirebaseRemoteConfigObserver
import com.twobuffers.wire.firebase_config.fetchAndActivateWithCb
import com.twobuffers.wire.sample_firebase_config.databinding.ActivitySampleBinding
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class SampleActivity : DaggerAppCompatActivity() {
    private val binding by lazy { ActivitySampleBinding.inflate(layoutInflater) }

    @Inject lateinit var remoteConfig: FirebaseRemoteConfig
    @Inject lateinit var remoteConfigObserver: FirebaseRemoteConfigObserver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        showFeatureStatus()
    }

    private fun showFeatureStatus() {
        // taking value once:
        remoteConfig.fetchAndActivateWithCb {
            val ret = remoteConfig.getBoolean("feature_a_enabled")
            @SuppressLint("SetTextI18n")
            binding.enabledCheckOnce.text = "$ret"
        }

        // subscribing to the remoteConfig:
        remoteConfigObserver.configValues
            .onEach { map ->
                val ret = map["feature_a_enabled"]?.asBoolean() ?: "missing"
                @SuppressLint("SetTextI18n")
                binding.enabledCheckContinuously.text = "$ret"
            }
            .launchIn(lifecycleScope)
    }
}
