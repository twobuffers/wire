package com.twobuffers.wire.retrofit

import com.google.common.base.Optional
import com.twobuffers.wire.di.ApplicationScoped
import dagger.BindsOptionalOf
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import dagger.multibindings.Multibinds
import javax.inject.Qualifier
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit

// The file provides annotations for wiring a OkHttp and Retrofit:
// annotations and a module providing a retrofit instance that should be enough in most of the simple applications.

@Module(includes = [WireDefaultRetrofitModule.BindingInputsModule::class])
object WireDefaultRetrofitModule {
    @Retention(AnnotationRetention.RUNTIME) @Qualifier @MustBeDocumented annotation class DefaultBaseUrl

    @Module
    abstract class BindingInputsModule {
        @Multibinds abstract fun bindConverterFactorySet(): Set<Converter.Factory>
    }

    @Provides
    @ApplicationScoped
    fun provideDefaultRetrofit(
        @DefaultBaseUrl baseUrl: HttpUrl,
        okHttpClient: OkHttpClient,
        converterFactories: Set<@JvmSuppressWildcards Converter.Factory>,
    ) = makeRetrofit(
        okHttpClient = okHttpClient,
        baseUrl = baseUrl,
        converterFactories = converterFactories.toList(),
    )
}

@Module(includes = [WireDefaultRetrofitModule::class])
object WireApplicationScopedRetrofitModule {
    @Provides
    @ApplicationScoped
    fun provideApplicationScopedRetrofit(default: Retrofit) = default
}

@Module(includes = [WireDefaultRetrofitDebugExtrasModule.InputsBindingsModule::class])
object WireDefaultRetrofitDebugExtrasModule {
    @Retention(AnnotationRetention.RUNTIME) @Qualifier @MustBeDocumented annotation class HttpLoggingLevel

    @Module
    abstract class InputsBindingsModule {
        @BindsOptionalOf @HttpLoggingLevel abstract fun bindOptionalHttpLoggingLevel(): HttpLoggingInterceptor.Level
    }

    private val DEFAULT_LOGGING_LEVEL = HttpLoggingInterceptor.Level.BODY

    @Provides @IntoSet @OkHttpNetworkInterceptor
    fun provideHttpLoggingInterceptor(@HttpLoggingLevel logLevel: Optional<HttpLoggingInterceptor.Level>): Interceptor =
        HttpLoggingInterceptor().apply { level = logLevel.or(DEFAULT_LOGGING_LEVEL) }
}
