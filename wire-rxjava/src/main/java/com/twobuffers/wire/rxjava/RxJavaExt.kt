package com.twobuffers.wire.rxjava

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun Disposable.addTo(disposables: CompositeDisposable): Boolean = disposables.add(this)
