package moura.renan.remote.api

import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import moura.renan.remote.service.ApiType
import okhttp3.Credentials
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitBuilder(
    private val baseUrl: String,
    private val apiType: ApiType
) {
    private var retroFit: Retrofit? = null


    fun <T> build(service: Class<T>): T {
        if (retroFit == null) {
            retroFit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(httpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .build()
        }
        return retroFit!!.create(service)
    }


    private val httpClient: OkHttpClient
        get()  {
            val builder = OkHttpClient.Builder()
            builder.addInterceptor(makeLoggingInterceptor(true))
            builder.connectTimeout(120, TimeUnit.SECONDS)
            builder.readTimeout(120, TimeUnit.SECONDS)

            builder.addInterceptor {chain ->
                val requestBuilder = chain.request().newBuilder().apply {
                    addAuthorizationHeaders(apiType)
                    addHeader("Content-Type", "application/json; charset=utf-8")
                }

                chain.proceed(requestBuilder.build())
            }
            return builder.build()
        }


    private fun Request.Builder.addAuthorizationHeaders(apiType: ApiType) {
        when (apiType) {
            is ApiType.TwitterAPI -> {
                val authorization = apiType.authTokenSource.getTwitterAccessToken()
                    ?: Credentials.basic(apiType.apiKey, apiType.apiSecret)

                this.addHeader("Authorization", authorization)
            }
            is ApiType.GoogleApi -> {
                apiType.authTokenSource.getGoogleAccessToken()?.let {
                    this.addHeader("Authorization", "Bearer $it")
                }
            }
        }
    }

    private fun makeLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        val loggin = HttpLoggingInterceptor()
        loggin.level = if (isDebug) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        return loggin
    }

}