package com.twobuffers.wire.utils.android

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty
import kotlinx.coroutines.launch

class FragmentViewBindingDelegate<T : ViewBinding>(
    bindingClass: Class<T>,
    private val fragment: Fragment
) : ReadOnlyProperty<Fragment, T> {
    private val clearBindingHandler by lazy(LazyThreadSafetyMode.NONE) { Handler(Looper.getMainLooper()) }
    private var binding: T? = null

    private val bindMethod = bindingClass.getMethod("bind", View::class.java)

    init {
        fragment.lifecycleScope.launch {
            fragment.viewLifecycleOwnerLiveData.observe(fragment) { viewLifecycleOwner ->
                viewLifecycleOwner.onEvent(Lifecycle.Event.ON_DESTROY) {
                    clearBindingHandler.post { binding = null }
                }
            }
        }
    }

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        // onCreateView may be called between onDestroyView and next Main thread cycle.
        // In this case [binding] refers to the previous fragment view. Check that binding's root view matches current fragment view
        if (binding != null && binding?.root !== thisRef.view) {
            binding = null
        }
        binding?.let { return it }

        val lifecycle = fragment.viewLifecycleOwner.lifecycle
        if (!lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
            error("Cannot access view bindings. View lifecycle is ${lifecycle.currentState}!")
        }

        @Suppress("UNCHECKED_CAST")
        binding = bindMethod.invoke(null, thisRef.requireView()) as T
        return binding!!
    }
}

/**
 * Create bindings for a view similar to bindView.
 *
 * To use, just call
 * private val binding: FHomeWorkoutDetailsBinding by viewBinding()
 * with your binding class and access it as you normally would.
 */
inline fun <reified T : ViewBinding> Fragment.viewBinding() = FragmentViewBindingDelegate(T::class.java, this)

// ----------


/**
 * Create bindings for a view similar to bindView.
 *
 * To use, just call:
 * private val binding: FHomeWorkoutDetailsBinding by viewBinding()
 * with your binding class and access it as you normally would.
 */
inline fun <reified T : ViewBinding> ViewGroup.viewBinding() = ViewBindingDelegate(T::class.java, this)

class ViewBindingDelegate<T : ViewBinding>(
    private val bindingClass: Class<T>,
    val fragment: ViewGroup
) : ReadOnlyProperty<ViewGroup, T> {
    private var binding: T? = null

    override fun getValue(thisRef: ViewGroup, property: KProperty<*>): T {
        binding?.let { return it }

        val inflateMethod = bindingClass.getMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java)
        @Suppress("UNCHECKED_CAST")
        binding = inflateMethod.invoke(null, LayoutInflater.from(thisRef.context), thisRef) as T
        return binding!!
    }
}
