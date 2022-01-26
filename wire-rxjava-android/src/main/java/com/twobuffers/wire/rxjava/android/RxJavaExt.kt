package com.twobuffers.wire.rxjava.android

import androidx.fragment.app.Fragment
import com.twobuffers.wire.utils.android.onViewDestroy
import io.reactivex.disposables.Disposable

fun Disposable.disposeOnViewDestroy(fragment: Fragment) {
    fragment.onViewDestroy {
        dispose()
    }
}
