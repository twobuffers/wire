@file:Suppress("unused")

package com.twobuffers.wire.sample_initializer.sampleinitializers

import com.twobuffers.wire.sample_initializer.BuildConfig
import dagger.Module
import dagger.Provides
import javax.inject.Named

const val BUILD_CONFIG_DEBUG = "BUILD_CONFIG_DEBUG"
const val BUILD_CONFIG_BUILD_TYPE = "BUILD_CONFIG_BUILD_TYPE"
const val BUILD_CONFIG_VERSION_NAME = "BUILD_CONFIG_VERSION_NAME"

@Module
object BuildConfigModule {

    @Provides
    @Named(BUILD_CONFIG_DEBUG)
    fun provideBuildConfigDebug(): Boolean = BuildConfig.DEBUG

    @Provides
    @Named(BUILD_CONFIG_BUILD_TYPE)
    fun provideBuildConfigBuildType(): String = BuildConfig.BUILD_TYPE

    @Provides
    @Named(BUILD_CONFIG_VERSION_NAME)
    fun provideBuildConfigVersionName(): String = BuildConfig.VERSION_NAME
}
