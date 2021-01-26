package com.nanolabs.currencyconversion.api

import com.google.gson.GsonBuilder
import com.nanolabs.currencyconversion.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {

    companion object {

        private const val BASE_URL = "http://apilayer.net/api/"
        private var retrofit: Retrofit? = null

        val client: Retrofit
            get() {

                if (retrofit == null) {

                    val okHttpClient = if (BuildConfig.DEBUG) {
                        val loggingInterceptor = HttpLoggingInterceptor()
                        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                        OkHttpClient.Builder().addInterceptor(loggingInterceptor)
                                .connectTimeout(1, TimeUnit.MINUTES)
                                .readTimeout(30, TimeUnit.SECONDS)
                                .writeTimeout(30, TimeUnit.SECONDS).build()
                    } else OkHttpClient.Builder()
                            .connectTimeout(1, TimeUnit.MINUTES)
                            .readTimeout(30, TimeUnit.SECONDS)
                            .writeTimeout(30, TimeUnit.SECONDS).build()

                    val gson = GsonBuilder()
                            .setLenient()
                            .create()

                    retrofit = Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .client(okHttpClient)
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .build()
                }
                return retrofit!!
            }

    }
}