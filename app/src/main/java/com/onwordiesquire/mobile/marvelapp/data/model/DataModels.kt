package com.onwordiesquire.mobile.marvelapp.data.model

/**
 * Created by michel.onwordi on 09/07/2017.
 */

class MarvelCharacter(val id: String, val name: String, val description: String,
                      val modified: String, val resourceURI: String, val urls: List<Url>, val thumbnail: Thumbnail)

class Thumbnail(val path: String, val extension: String, val id: String)

class Url(val type: String, val url: String)