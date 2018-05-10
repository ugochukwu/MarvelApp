package com.onwordiesquire.mobile.marvelapp.data

import android.util.Log

import com.onwordiesquire.mobile.marvelapp.data.model.CharacterData
import com.onwordiesquire.mobile.marvelapp.data.model.CharacterDataContainer
import com.onwordiesquire.mobile.marvelapp.data.model.RecentSearches
import com.onwordiesquire.mobile.marvelapp.data.sources.local.DatabaseDataSource
import com.onwordiesquire.mobile.marvelapp.data.sources.remote.MarvelApiService

import org.apache.commons.codec.binary.Hex
import org.apache.commons.codec.digest.DigestUtils

import javax.inject.Inject
import javax.inject.Singleton

import rx.Observable

/**
 * Created by michelonwordi on 10/23/16.
 */
@Singleton
class DataManager @Inject
constructor(private val apiService: MarvelApiService, private val databaseDataSource: DatabaseDataSource) {
    private val cache: List<CharacterData>? = null


    fun getCharacter(name: String, apikey: String, timestamp: String, privateKey: String): Observable<CharacterData> {

        //network observable source
        val api = apiService
                .getMarvelCharacter(name, apikey, timestamp, createHash(timestamp, apikey, privateKey))
                .flatMap<CharacterData> { characterDataWrapper ->
                    val data = characterDataWrapper.data()

                    val results = data!!.results()
                    if (results != null && results.size != 0) {
                        return@apiService
                                .getMarvelCharacter(name, apikey, timestamp, createHash(timestamp, apikey, privateKey))
                                .flatMap Observable . just < CharacterData >(results[0])
                    } else {
                        return@apiService
                                .getMarvelCharacter(name, apikey, timestamp, createHash(timestamp, apikey, privateKey))
                                .flatMap Observable . error < CharacterData >(EmptyResultsException("No Data information contained"))
                    }

                }
                .doOnNext { data ->

                    if (data != null) {
                        Log.i(TAG, "getCharacter: Writing to db....")
                        databaseDataSource.insertCharacter(data)
                    }
                }


        //disk (SQLite DB) observable source
        val disk = databaseDataSource.getCharacter(name)

        //RX design pattern to implement caching. Data is fetched first from the disk if available before the Api.
        return Observable.concat(disk, api).first { characterData -> characterData != null }
    }

    val allCharacters: Observable<List<CharacterData>>
        get() = databaseDataSource.allCharacters

    fun getCharacterById(characterId: String): Observable<CharacterData> {
        return databaseDataSource.getCharacterById(characterId)
                .doOnNext { characterData ->
                    //once we reach this point a search has successfully executed, therefore, store it in the DB
                    databaseDataSource.insertRecentSearches(RecentSearches.builder().name(characterData.name())
                            .characterId(characterData.id())
                            .build())
                }
    }

    val recentSearches: Observable<List<RecentSearches>>
        get() = databaseDataSource.recentSearches

    //Helper method to generate an MD5 hash necessary for communicating with the marvel api
    private fun createHash(timestamp: String, apikey: String, privateKey: String): String {
        return String(Hex.encodeHex(DigestUtils.md5(timestamp + privateKey + apikey)))
    }


    /**
     * An exception representing an empty result from the api. This will help subscribers in the presenter layer
     * understand what happened.
     */
    class EmptyResultsException(message: String) : Exception(message)

    companion object {

        private val TAG = DataManager::class.java.simpleName
    }
}
