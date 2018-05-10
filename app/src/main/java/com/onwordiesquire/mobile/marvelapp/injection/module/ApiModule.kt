@file:JvmName("ApiModule")
package com.onwordiesquire.mobile.marvelapp.injection.module

import com.google.gson.Gson
import com.onwordiesquire.mobile.marvelapp.BuildConfig
import com.onwordiesquire.mobile.marvelapp.data.sources.remote.MarvelApiService
import com.onwordiesquire.mobile.marvelapp.util.ENDPOINT
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

/**
 * Created by michel.onwordi on 13/06/2017.
 */
@Module
class ApiModule {

    @Provides
    @Named(ENDPOINT)
    fun providesEndpoint() = BuildConfig.ENDPOINT

    @Provides
    fun providesOkHttpClient(gson: Gson): OkHttpClient = if (BuildConfig.DEBUG) {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder().addInterceptor(interceptor).build()
    } else {
        OkHttpClient.Builder().build()
    }

    @Provides
    fun providesGson(): Gson = Gson()

    @Provides
    fun providesRetrofit(@Named(ENDPOINT) endpoint: String, gson: Gson, client: OkHttpClient):
            Retrofit =
            Retrofit.Builder()
                    .baseUrl(endpoint)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(client)
                    .build()

    @Provides
    fun providesMarvelApiService(retrofit: Retrofit): MarvelApiService =
            retrofit.create(MarvelApiService::class.java)
}