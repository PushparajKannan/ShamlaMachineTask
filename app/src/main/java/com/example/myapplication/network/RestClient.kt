package com.example.myapplication.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


class RestClient private constructor() {
    companion object {
        private val BASE_URL = "https://reqres.in/api/"
        private lateinit var mApiServices: WebServices


        @Volatile
        private var mInstance: RestClient? = null


        fun getInstance(): RestClient {
            if (mInstance == null) {
                synchronized(this) {
                    mInstance = RestClient()
                }
            }
            return mInstance!!
        }
    }




    init {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val clientBuilder = OkHttpClient.Builder()
        clientBuilder.addInterceptor { chain ->
            val request = chain.request()
            val response = chain.proceed(request)
            response
        }


        val okHttpClient = OkHttpClient().newBuilder().connectTimeout(5, TimeUnit.MINUTES)
            .readTimeout(5, TimeUnit.MINUTES)
            .addInterceptor(logging)
            .build();
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

        mApiServices = retrofit.create(WebServices::class.java)
    }

    fun getApiService() = mApiServices


}