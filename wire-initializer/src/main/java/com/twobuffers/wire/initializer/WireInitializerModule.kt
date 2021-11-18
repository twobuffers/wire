package com.twobuffers.wire.initializer

import dagger.Module
import dagger.multibindings.Multibinds

@Module
abstract class WireInitializerModule {
    // The declaration of multi-binding is necessary in case
    // if there is no bindings and set is empty.
    @Multibinds
    abstract fun bindInitializersSet(): Set<WireInitializer>
}
