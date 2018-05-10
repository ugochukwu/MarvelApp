package com.onwordiesquire.mobile.marvelapp.data.responseModel

/**
 * Created by michel.onwordi on 13/06/2017.
 */
data class CharacterCollectionDto(val offset: Int, val limit: Int, val total: Int, val count: Int,
                                  val results: List<CharacterDto>)