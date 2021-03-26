package com.social.test.api

import com.social.test.utils.AppConstants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {

    companion object {

        private var retrofit: Retrofit? = null
        var client = OkHttpClient()

        fun getClient(): Retrofit {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            client = OkHttpClient.Builder().connectTimeout(260, TimeUnit.SECONDS)
                .readTimeout(260, TimeUnit.SECONDS).addInterceptor(interceptor).build()
            retrofit = Retrofit.Builder()
                .baseUrl(AppConstants.BaseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            return retrofit!!
        }


    }
}
