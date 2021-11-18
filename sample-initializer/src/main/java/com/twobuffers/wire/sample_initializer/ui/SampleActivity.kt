package com.twobuffers.wire.sample_initializer.ui

import android.os.Bundle
import com.twobuffers.wire.sample_initializer.R
import dagger.android.support.DaggerAppCompatActivity

class SampleActivity : DaggerAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample)
    }
}
