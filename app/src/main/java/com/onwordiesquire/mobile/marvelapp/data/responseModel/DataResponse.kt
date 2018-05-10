package com.onwordiesquire.mobile.marvelapp.data.responseModel

/**
 * Created by michel.onwordi on 13/06/2017.
 */
data class DataResponse(val code: String, val status: String, val copyright: String,
                        val attributionText: String, val attributionHTML: String,
                        val etag: String, val data: CharacterCollectionDto)