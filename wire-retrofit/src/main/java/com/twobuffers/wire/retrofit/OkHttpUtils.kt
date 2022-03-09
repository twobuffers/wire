@file:Suppress("NOTHING_TO_INLINE")

package com.twobuffers.wire.retrofit

import java.net.HttpURLConnection
import java.util.concurrent.TimeUnit
import okhttp3.Authenticator
import okhttp3.Cache
import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import okio.IOException

fun <A> OkHttpClient.Builder.trySet(
    a: A?,
    fn: OkHttpClient.Builder.(A) -> OkHttpClient.Builder,
) = apply {
    val newA = a ?: return@apply
    fn(newA)
}

fun <A, B> OkHttpClient.Builder.trySet(
    pair: Pair<A, B>?,
    fn: OkHttpClient.Builder.(A, B) -> OkHttpClient.Builder,
) = apply {
    val (a, b) = pair ?: return@apply
    fn(a, b)
}

fun OkHttpClient.Builder.addApplicationInterceptors(interceptors: List<Interceptor>) =
    apply { interceptors.forEach(::addInterceptor) }

fun OkHttpClient.Builder.addNetworkInterceptors(interceptors: List<Interceptor>) =
    apply { interceptors.forEach(::addNetworkInterceptor) }

fun makeOkHttpClient(
    okHttpClient: OkHttpClient? = null,
    connectTimeout: Pair<Long, TimeUnit>? = null,
    readTimeout: Pair<Long, TimeUnit>? = null,
    cache: Cache? = null,
    applicationInterceptors: List<Interceptor> = emptyList(),
    networkInterceptors: List<Interceptor> = emptyList(),
    authenticator: Authenticator? = null,
): OkHttpClient = (okHttpClient?.newBuilder() ?: OkHttpClient.Builder())
    .trySet(connectTimeout, OkHttpClient.Builder::connectTimeout)
    .trySet(readTimeout, OkHttpClient.Builder::readTimeout)
    .trySet(cache, OkHttpClient.Builder::cache)
    .addApplicationInterceptors(applicationInterceptors)
    .addNetworkInterceptors(networkInterceptors)
    .trySet(authenticator, OkHttpClient.Builder::authenticator)
    .build()

// https://square.github.io/okhttp/3.x/okhttp/okhttp3/OkHttpClient.html
// "OkHttpClients should be shared
//  OkHttp performs best when you create a single OkHttpClient instance and reuse it for all of your HTTP calls.
//  This is because each client holds its own connection pool and thread pools.
//  Reusing connections and threads reduces latency and saves memory.
//  Conversely, creating a client for each request wastes resources on idle pools."

fun Interceptor.Chain.addHeaders(headers: Map<String, String>): Response {
    val request = request()
        .newBuilder()
        .apply { headers.forEach { (h, v) -> addHeader(h, v) } }
        .build()
    return proceed(request)
}

fun Interceptor.Chain.addHeadersWithValueLambdas(headers: Map<String, () -> String?>): Response {
    @Suppress("UNCHECKED_CAST")
    val hv = headers.mapValues { it.value() }.filterValues { it != null } as Map<String, String>
    return addHeaders(hv)
}

fun Interceptor.Chain.addHeadersWithLambdas(headers: List<() -> Pair<String, String>?>): Response {
    val hv = headers.mapNotNull { it() }.toMap()
    return addHeaders(hv)
}

class AddHeadersInterceptor(private val headers: Map<String, String>) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response = chain.addHeaders(headers)
}

class AddHeadersWithValueLambdasInterceptor(private val headers: Map<String, () -> String?>) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response = chain.addHeadersWithValueLambdas(headers)
}

class AddHeadersWithLambdasInterceptor(private val headers: List<() -> Pair<String, String>?>) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response = chain.addHeadersWithLambdas(headers)
}

fun Interceptor.Chain.updateHeaders(headers: Map<String, (prev: String) -> String>): Response {
    val prevRequest = request()
    val updatedHeadersPairs = prevRequest.headers
        .map { (k, v) ->
            val newValue = headers[k]?.let { updateFn -> updateFn(v) } ?: v
            Pair(k, newValue)
        }
    val updatedHeaders = Headers.Builder()
        .apply { updatedHeadersPairs.forEach { (k, v) -> add(k, v) } }
        .build()
    val newRequest = prevRequest.newBuilder()
        .headers(updatedHeaders)
        .build()
    return proceed(newRequest)
}

class UpdateHeadersInterceptor(private val headersUpdateFns: Map<String, (prev: String) -> String>) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response = chain.updateHeaders(headersUpdateFns)
}

const val HEADER_AUTHORIZATION= "Authorization"
const val HEADER_AUTHORIZATION_VAL_TEMPLATE = "Bearer %s"
val is401 = { res: Response -> res.code == HttpURLConnection.HTTP_UNAUTHORIZED }
val authHeaderExists = { res: Response -> res.request.header(HEADER_AUTHORIZATION) != null }

fun createBearerAuthenticator(
    refreshTokenFn: Function0<String?>,
    consent: Function1<Response, Boolean> = { true },
): Authenticator = object : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        // TODO: Do I need `authHeaderExists` here?
        if (!is401(response) || !authHeaderExists(response) || !consent(response)) return null
        synchronized(this) {
            val newToken = refreshTokenFn() ?: return null
            val oldRequest = response.request
            val oldToken = oldRequest.header(HEADER_AUTHORIZATION)
            if (newToken == oldToken) return null // if both tokens are the same, there is nothing to change
            return oldRequest.newBuilder()
                .header(HEADER_AUTHORIZATION, HEADER_AUTHORIZATION_VAL_TEMPLATE.format(newToken))
                .build()
        }
    }
}
