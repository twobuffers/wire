package com.twobuffers.wire.retrofit

import java.util.concurrent.TimeUnit
import okhttp3.Authenticator
import okhttp3.Cache
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit

fun Retrofit.Builder.addConverterFactories(factories: List<Converter.Factory>?) =
    apply { factories?.forEach(::addConverterFactory) }

fun makeRetrofit(
    baseUrl: HttpUrl,
    converterFactories: List<Converter.Factory> = listOf(),
    connectTimeout: Pair<Long, TimeUnit>? = null,
    readTimeout: Pair<Long, TimeUnit>? = null,
    cache: Cache? = null,
    applicationInterceptors: List<Interceptor> = emptyList(),
    networkInterceptors: List<Interceptor> = emptyList(),
    authenticator: Authenticator? = null,
) = makeRetrofit(
    baseUrl = baseUrl,
    converterFactories = converterFactories,
    okHttpClient = makeOkHttpClient(
        connectTimeout = connectTimeout,
        readTimeout = readTimeout,
        cache = cache,
        applicationInterceptors = applicationInterceptors,
        networkInterceptors = networkInterceptors,
        authenticator = authenticator,
    ),
)

fun makeRetrofit(
    baseUrl: HttpUrl,
    converterFactories: List<Converter.Factory> = listOf(),
    okHttpClient: OkHttpClient = makeOkHttpClient(),
): Retrofit = Retrofit.Builder()
    .client(okHttpClient)
    .baseUrl(baseUrl)
    .addConverterFactories(converterFactories)
    .build()
