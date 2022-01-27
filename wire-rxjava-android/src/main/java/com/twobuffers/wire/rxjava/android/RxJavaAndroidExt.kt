package com.twobuffers.wire.rxjava.android

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.twobuffers.wire.utils.android.onDestroy
import com.twobuffers.wire.utils.android.onViewDestroy
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun AppCompatActivity.disposeOnDestroy(disposable: Disposable) = onDestroy { disposable.dispose() }
fun Fragment.disposeOnDestroy(disposable: Disposable) = onDestroy { disposable.dispose() }
fun Fragment.disposeOnViewDestroy(disposable: Disposable) = onViewDestroy { disposable.dispose() }

fun Disposable.disposeOnDestroyOf(activity: AppCompatActivity) = activity.disposeOnDestroy(this)
fun Disposable.disposeOnDestroyOf(fragment: Fragment) = fragment.disposeOnDestroy(this)
fun Disposable.disposeOnViewDestroyOf(fragment: Fragment) = fragment.disposeOnViewDestroy(this)

@Deprecated(
    message = "Use Fragment.disposeOnDestroy",
    replaceWith = ReplaceWith("disposeOnViewDestroyOf(fragment)"),
)
fun Disposable.disposeOnViewDestroy(fragment: Fragment) = disposeOnViewDestroyOf(fragment)

fun AppCompatActivity.createAutoDisposableOnDestroy() = CompositeDisposable().apply { onDestroy { dispose() } }
fun Fragment.createAutoDisposableOnDestroy() = CompositeDisposable().apply { onDestroy { dispose() } }
fun Fragment.createAutoDisposableOnViewDestroy() = CompositeDisposable().apply { onViewDestroy { dispose() } }
