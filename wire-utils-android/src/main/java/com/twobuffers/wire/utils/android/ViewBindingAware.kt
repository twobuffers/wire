package com.twobuffers.wire.utils.android

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding

interface ViewBindingAware<T : ViewDataBinding> {
    var b: T
}

inline fun <reified T : ViewDataBinding> ViewBindingAware<T>.bindView(v: View): T {
    val bindMethod = T::class.java.getMethod("bind", View::class.java)
    b = bindMethod.invoke(null, v) as T
    return b
}

inline fun <reified T : ViewDataBinding> ViewBindingAware<T>.inflateView(v: View): T {
    val inflateMethod = T::class.java.getMethod("inflate", LayoutInflater::class.java)
    b = inflateMethod.invoke(null, v) as T
    return b
}

inline fun <reified T : ViewDataBinding> ViewBindingAware<T>.inflateView(
    v: View,
    root: ViewGroup?,
    attachToRoot: Boolean,
    component: Any?
): T {
    val inflateMethod = T::class.java.getMethod(
        "inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java, Object::class.java
    )
    b = inflateMethod.invoke(null, v, root, attachToRoot, component) as T
    return b
}
