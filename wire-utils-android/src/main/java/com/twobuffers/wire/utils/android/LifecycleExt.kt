package com.twobuffers.wire.utils.android

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job

fun AppCompatActivity.launchWhenCreated(
    block: suspend CoroutineScope.() -> Unit
): Job = lifecycleScope.launchWhenCreated(block = block)

fun Fragment.launchWhenCreated(
    block: suspend CoroutineScope.() -> Unit
): Job = lifecycleScope.launchWhenCreated(block = block)
