package com.onwordiesquire.mobile.marvelapp.data.refactor

import com.onwordiesquire.mobile.marvelapp.BuildConfig
import com.onwordiesquire.mobile.marvelapp.data.model.CharacterData
import com.onwordiesquire.mobile.marvelapp.data.refactor.data.MarvelCharacter
import com.onwordiesquire.mobile.marvelapp.data.refactor.data.Thumbnail
import com.onwordiesquire.mobile.marvelapp.data.refactor.data.Url
import com.onwordiesquire.mobile.marvelapp.data.sources.local.DatabaseDataSource
import com.onwordiesquire.mobile.marvelapp.data.sources.refactor.remote.MarvelApi
import com.onwordiesquire.mobile.marvelapp.util.EmptyResultsException
import org.apache.commons.codec.binary.Hex
import org.apache.commons.codec.digest.DigestUtils
import rx.Observable
import javax.inject.Inject

/**
 * Created by michel.onwordi on 13/06/2017.
 */
class MarvelDataManager @Inject constructor(val remoteDataSource: MarvelApi, val databaseDataSource:
DatabaseDataSource) {

    fun getCharacter(name: String, timeStamp: String):
            Observable<MarvelCharacter> =
            remoteDataSource.getMarvelCharacter(name, BuildConfig.PUBLIC_API_KEY_MARVEL, timeStamp, createHash
            (timeStamp, BuildConfig.PUBLIC_API_KEY_MARVEL, BuildConfig.PUBLIC_API_KEY_MARVEL))
                    .flatMap { dataResponse ->
                        dataResponse?.data?.results?.first()?.let {
                            Observable.just<MarvelCharacter>(MarvelCharacter(it.id, it.name,
                                    it.description, it.modified,
                                    it.resourceURI, it.urls.map { Url(it.type, it.url) },
                                    it.thumbnail.let { Thumbnail(it.path, it.extension, it.id) }))
                        } ?: Observable.error(EmptyResultsException())
                    }

    fun getAllCharacters(): Observable<List<CharacterData>> = databaseDataSource.allCharacters

    private fun createHash(timestamp: String, apiKey: String, privateKey: String) =
            String(Hex.encodeHex(DigestUtils.md5(timestamp + privateKey + apiKey)))

}
