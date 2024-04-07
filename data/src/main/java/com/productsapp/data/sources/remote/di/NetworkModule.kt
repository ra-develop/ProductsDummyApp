package com.productsapp.data.sources.remote.di

import com.google.gson.GsonBuilder
import com.productsapp.data.BuildConfig
import com.productsapp.data.sources.remote.services.RequestInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(okHttp: OkHttpClient): Retrofit {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        return Retrofit.Builder().apply {
            addConverterFactory(GsonConverterFactory.create(gson))
//            addConverterFactory(BoxfitSerializer.getConverterFactory(ObjectBoxInstance.store))
            client(okHttp)
            baseUrl(BuildConfig.API_BASE_URL)
        }.build()
    }

    @Singleton
    @Provides
    fun provideOkHttp(requestInterceptor: RequestInterceptor): OkHttpClient {

        // Request black list patterns to filter
        val REQUEST_BLACK_LIST_PATTERNS = arrayOf(
            "Content-Type"
        )
        // Response black list patterns to filter
        val RESPONSE_BLACK_LIST_PATTERNS = arrayOf(
            // Insert a string which will be used of filter for un view in the log
            "Server",
            "server",
            "X-Powered-By",
            "Set-Cookie",
            "Expires",
            "Cache-Control",
            "Pragma",
            "Content-Length",
            "access-control-allow-origin",
            "Connection",
            "X-RateLimit",
            "Access-Control-Allow-Origin",
            "Vary",
            "Upgrade",
            "Accept-Ranges"
        )

        // Request white list patterns to filter
        val RESPONSE_WHITE_LIST_PATTERNS = arrayOf<String>(
            // Insert a string which will be used of filter for view in the log
//            "ALL",
            "https://ynap.github.io/data/yoox/mock/",
        )

        val loggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {

                // Blacklist the elements not required
                for (pattern in REQUEST_BLACK_LIST_PATTERNS) {
                    if (message.startsWith(pattern)) {
                        return
                    }
                }
                // Any response patterns as well...
                for (pattern in RESPONSE_BLACK_LIST_PATTERNS) {
                    if (message.startsWith(pattern)) {
                        return
                    }
                }

                // Show all messages
                if (RESPONSE_WHITE_LIST_PATTERNS.contains("ALL")) {
                    Timber.tag("OkHttpClient").d(message)
                    return
                }

                // Show specific messages by wait list
                for (pattern in RESPONSE_WHITE_LIST_PATTERNS) {
                    if (message.contains(pattern)) {
                        Timber.tag("OkHttpClient").d(message)
                    }
                }
            }
        })

        // Manage log level
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder().apply {
            connectTimeout(60, TimeUnit.SECONDS)
            readTimeout(60, TimeUnit.SECONDS)
            writeTimeout(60, TimeUnit.SECONDS)
            addInterceptor(requestInterceptor)
            addInterceptor(loggingInterceptor)
            protocols( listOf(Protocol.HTTP_1_1, Protocol.HTTP_2) )
        }.build()
    }

    @Provides
    fun provideRequestInterceptor(/*prefs: UserDataLocalStorage*/): RequestInterceptor {
        return RequestInterceptor(/*prefs*/)
    }

}