package com.onwordiesquire.mobile.marvelapp.data.responseModel

/**
 * Created by michel.onwordi on 13/06/2017.
 */
data class CharacterDto(val id: String, val name: String, val description: String, val modified: String,
                        val thumbnail: Thumbnail, val resourceURI: String, val comics: Comics,
                        val urls: List<Url>)