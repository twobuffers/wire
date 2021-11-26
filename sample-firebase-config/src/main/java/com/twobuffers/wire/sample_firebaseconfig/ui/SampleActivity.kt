package com.twobuffers.wire.sample_firebaseconfig.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.twobuffers.wire.firebaseconfig.FirebaseRemoteConfigObserver
import com.twobuffers.wire.sample_firebaseconfig.databinding.ActivitySampleBinding
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class SampleActivity : DaggerAppCompatActivity() {
    private val binding by lazy { ActivitySampleBinding.inflate(layoutInflater) }

    @Inject lateinit var remoteConfig: FirebaseRemoteConfig
    @Inject lateinit var remoteConfigObserver: FirebaseRemoteConfigObserver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // taking value once:
//        remoteConfig.fetchAndActivateWithCb {
//            val ret = remoteConfig.getBoolean("feature_a_enabled")
//            @SuppressLint("SetTextI18n")
//            binding.textView.text = "result: $ret"
//        }

        // subscribing to the remoteConfig:
        remoteConfigObserver.configValues
            .onEach { map ->
                val ret = map["feature_a_enabled"]?.asBoolean() ?: "missing"
                @SuppressLint("SetTextI18n")
                binding.textView.text = "result: $ret"
            }
            .flowOn(Dispatchers.Main)
            .launchIn(lifecycleScope)
    }
}
