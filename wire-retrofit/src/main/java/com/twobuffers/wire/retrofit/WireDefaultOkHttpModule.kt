package com.twobuffers.wire.retrofit

import com.google.common.base.Optional
import com.twobuffers.wire.di.ApplicationScoped
import dagger.BindsOptionalOf
import dagger.Module
import dagger.Provides
import dagger.multibindings.Multibinds
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import okhttp3.Authenticator
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient

@Retention(AnnotationRetention.RUNTIME) @Qualifier @MustBeDocumented annotation class DefaultOkHttpClient
@Retention(AnnotationRetention.RUNTIME) @Qualifier @MustBeDocumented annotation class OkHttpCacheDir
@Retention(AnnotationRetention.RUNTIME) @Qualifier @MustBeDocumented annotation class OkHttpConnectTimeout
@Retention(AnnotationRetention.RUNTIME) @Qualifier @MustBeDocumented annotation class OkHttpReadTimeout
@Retention(AnnotationRetention.RUNTIME) @Qualifier @MustBeDocumented annotation class OkHttpCacheMaxSizeBytes
@Retention(AnnotationRetention.RUNTIME) @Qualifier @MustBeDocumented annotation class OkHttpApplicationInterceptor
@Retention(AnnotationRetention.RUNTIME) @Qualifier @MustBeDocumented annotation class OkHttpNetworkInterceptor
@Retention(AnnotationRetention.RUNTIME) @Qualifier @MustBeDocumented annotation class OkHttpAuthenticator

@Module(includes = [WireDefaultOkHttpModule.BindingInputsModule::class])
object WireDefaultOkHttpModule {
    @Module
    abstract class BindingInputsModule {
        @BindsOptionalOf @OkHttpConnectTimeout      abstract fun bindOptionalConnectTimeout(): Pair<Long, TimeUnit>
        @BindsOptionalOf @OkHttpReadTimeout         abstract fun bindOptionalReadTimeout(): Pair<Long, TimeUnit>
        @BindsOptionalOf                            abstract fun bindOptionalCache(): Cache
        @BindsOptionalOf @OkHttpCacheDir            abstract fun bindOptionalCacheDir(): File
        @BindsOptionalOf @OkHttpCacheMaxSizeBytes   abstract fun bindOptionalCacheMaxSizeBytes(): Long
        @BindsOptionalOf @OkHttpAuthenticator       abstract fun bindOptionalAuthenticator(): Authenticator
        @Multibinds @OkHttpApplicationInterceptor   abstract fun bindApplicationInterceptorsSet(): Set<Interceptor>
        @Multibinds @OkHttpNetworkInterceptor       abstract fun bindNetworkInterceptorsSet(): Set<Interceptor>
    }

    @Provides @DefaultOkHttpClient
    fun provideDefaultOkHttpClient(
        @OkHttpConnectTimeout optionalConnectTimeout: Optional<Pair<Long, TimeUnit>>,
        @OkHttpReadTimeout optionalReadTimeout: Optional<Pair<Long, TimeUnit>>,
        optionalCache: Optional<Cache>,
        @OkHttpCacheDir optionalCacheDir: Optional<File>,
        @OkHttpCacheMaxSizeBytes optionalCacheMaxSizeBytes: Optional<Long>,
        @OkHttpApplicationInterceptor applicationInterceptors: Set<@JvmSuppressWildcards Interceptor>,
        @OkHttpNetworkInterceptor networkInterceptors: Set<@JvmSuppressWildcards Interceptor>,
        @OkHttpAuthenticator optionalAuthenticator: Optional<Authenticator>,
    ): OkHttpClient = makeOkHttpClient(
        connectTimeout = optionalConnectTimeout.orNull(),
        readTimeout = optionalReadTimeout.orNull(),
        cache = optionalCache.orNull() ?: createCache(
            cacheDir = optionalCacheDir.orNull(), cacheMaxSizeBytes = optionalCacheMaxSizeBytes.orNull()
        ),
        applicationInterceptors = applicationInterceptors.toList(),
        networkInterceptors = networkInterceptors.toList(),
        authenticator = optionalAuthenticator.orNull(),
    )

    private const val DEFAULT_MAX_CACHE_SIZE_10MB = 10L * 1024L * 1024L
    private fun createCache(cacheDir: File?, cacheMaxSizeBytes: Long?): Cache? {
        cacheDir ?: return null
        val finalMaxCacheSize = cacheMaxSizeBytes ?: DEFAULT_MAX_CACHE_SIZE_10MB
        return Cache(cacheDir, finalMaxCacheSize)
    }
}

@Module(includes = [WireDefaultOkHttpModule::class])
object WireUnscopedOkHttpModule {
    @Provides
    fun provideOkHttpClient(@DefaultOkHttpClient instance: OkHttpClient): OkHttpClient = instance
}


@Module(includes = [WireDefaultOkHttpModule::class])
object WireApplicationScopedOkHttpModule {
    @Provides @ApplicationScoped
    fun provideApplicationScopedOkHttpClient(@DefaultOkHttpClient instance: OkHttpClient): OkHttpClient = instance
}
