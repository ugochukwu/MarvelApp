package com.onwordiesquire.mobile.marvelapp.data

import com.onwordiesquire.mobile.marvelapp.BuildConfig
import com.onwordiesquire.mobile.marvelapp.data.model.MarvelCharacter
import com.onwordiesquire.mobile.marvelapp.data.model.Thumbnail
import com.onwordiesquire.mobile.marvelapp.data.model.Url
import com.onwordiesquire.mobile.marvelapp.data.sources.local.RecentSearchDao
import com.onwordiesquire.mobile.marvelapp.data.sources.remote.MarvelApi
import com.onwordiesquire.mobile.marvelapp.util.EmptyResultsException
import io.reactivex.Single
import org.apache.commons.codec.binary.Hex
import org.apache.commons.codec.digest.DigestUtils
import javax.inject.Inject

/**
 * Created by michel.onwordi on 13/06/2017.
 */
class MarvelDataManager @Inject constructor(private val remoteDataSource: MarvelApi,
                                            private val recentSearchDao: RecentSearchDao) {

    fun getCharacter(name: String, timeStamp: String):
            Single<MarvelCharacter> =
            remoteDataSource.getMarvelCharacter(name, BuildConfig.PUBLIC_API_KEY_MARVEL, timeStamp, createHash
            (timeStamp, BuildConfig.PUBLIC_API_KEY_MARVEL, BuildConfig.PUBLIC_API_KEY_MARVEL))
                    .flatMap { dataResponse ->
                        dataResponse.data.results.first().let {
                            Single.just<MarvelCharacter>(MarvelCharacter(it.id, it.name,
                                    it.description, it.modified,
                                    it.resourceURI, it.urls.map { Url(it.type, it.url) },
                                    it.thumbnail.let { Thumbnail(it.path, it.extension, it.id) }))
                        } ?: Single.error(EmptyResultsException())
                    }

    private fun createHash(timestamp: String, apiKey: String, privateKey: String) =
            String(Hex.encodeHex(DigestUtils.md5(timestamp + privateKey + apiKey)))

}
