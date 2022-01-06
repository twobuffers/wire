package com.twobuffers.wire.retrofit

import android.content.Context
import dagger.Module
import dagger.Provides
import java.io.File

@Module
object WireDefaultOkHttpAndroidHelperModule {
    @Provides
    @OkHttpCacheDir
    fun provideOkhttpCacheDir(context: Context): File = context.cacheDir
}
