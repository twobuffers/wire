@file:Suppress("NOTHING_TO_INLINE")

package com.twobuffers.wire.utils.android

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Lifecycle.Event
import androidx.lifecycle.Lifecycle.Event.ON_DESTROY
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job

// Regular

fun Lifecycle.onEvent(event: Event, block: (source: LifecycleOwner) -> Unit): LifecycleEventObserver {
    val o = LifecycleEventObserver { source, receivedEvent -> if (receivedEvent == event) block(source) }
    addObserver(o)
    return o
}

inline fun LifecycleOwner.onEvent(event: Event, noinline block: (source: LifecycleOwner) -> Unit) =
    lifecycle.onEvent(event, block)

inline fun Fragment.onViewEvent(event: Event, noinline block: (source: LifecycleOwner) -> Unit) =
    viewLifecycleOwner.onEvent(event, block)

inline fun LifecycleOwner.onDestroy(noinline block: (source: LifecycleOwner) -> Unit) = onEvent(ON_DESTROY, block)
inline fun Fragment.onViewDestroy(noinline block: (source: LifecycleOwner) -> Unit) = onViewEvent(ON_DESTROY, block)

/// Cancels previously set onEvent or onViewEvent action.
fun Lifecycle.cancel(observer: LifecycleObserver) = removeObserver(observer)

// Coroutines

fun AppCompatActivity.launchWhenCreated(
    block: suspend CoroutineScope.() -> Unit
): Job = lifecycleScope.launchWhenCreated(block = block)

fun Fragment.launchWhenCreated(
    block: suspend CoroutineScope.() -> Unit
): Job = lifecycleScope.launchWhenCreated(block = block)

// Utilities

fun RecyclerView.clearAdapterOnDestroyOf(lo: LifecycleOwner) = lo.onDestroy { adapter = null }
fun ViewPager2.clearAdapterOnDestroyOf(lo: LifecycleOwner) = lo.onDestroy { adapter = null }
fun RecyclerView.clearAdapterOnViewDestroyOf(fragment: Fragment) = fragment.onViewDestroy { adapter = null }
fun ViewPager2.clearAdapterOnViewDestroyOf(fragment: Fragment) = fragment.onViewDestroy { adapter = null }

@Deprecated("Method deprecated", ReplaceWith("clearAdapterOnViewDestroyOf(fragment)"))
fun RecyclerView.clearAdapterOnViewDestroy(fragment: Fragment) = clearAdapterOnViewDestroyOf(fragment)

@Deprecated("Method deprecated", ReplaceWith("clearAdapterOnViewDestroyOf(fragment)"))
fun ViewPager2.clearAdapterOnViewDestroy(fragment: Fragment) = clearAdapterOnViewDestroyOf(fragment)
