package com.onwordiesquire.mobile.marvelapp.data.sources.remote

import com.onwordiesquire.mobile.marvelapp.data.responseModel.DataResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by michel.onwordi on 09/07/2017.
 */
interface MarvelApi {

    @GET("Characters")
    fun getMarvelCharacter(@Query("name") name: String, @Query("apikey") apikey: String,
                           @Query("ts") timeStamp: String, @Query("hash") md5Hash: String): Single<DataResponse>
}