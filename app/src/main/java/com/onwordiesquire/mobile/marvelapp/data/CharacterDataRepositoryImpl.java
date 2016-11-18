package com.onwordiesquire.mobile.marvelapp.data;

import android.util.Log;

import com.onwordiesquire.mobile.marvelapp.BuildConfig;
import com.onwordiesquire.mobile.marvelapp.DataConverter;
import com.onwordiesquire.mobile.marvelapp.characterdetails.domain.model.MarvelCharacter;
import com.onwordiesquire.mobile.marvelapp.data.model.CharacterData;
import com.onwordiesquire.mobile.marvelapp.data.model.CharacterDataContainer;
import com.onwordiesquire.mobile.marvelapp.data.model.RecentSearches;
import com.onwordiesquire.mobile.marvelapp.data.sources.local.DatabaseDataSource;
import com.onwordiesquire.mobile.marvelapp.data.sources.remote.MarvelApiService;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * Created by michelonwordi on 10/23/16.
 */
@Singleton
public class CharacterDataRepositoryImpl implements CharacterDataRepository {

    private static final String TAG = CharacterDataRepositoryImpl.class.getSimpleName();
    private final MarvelApiService apiService;
    private final DatabaseDataSource databaseDataSource;
    private List<CharacterData> cache = null;

    @Inject
    public CharacterDataRepositoryImpl(MarvelApiService apiService, DatabaseDataSource databaseDataSource) {
        this.apiService = apiService;
        this.databaseDataSource = databaseDataSource;
    }


    public Observable<CharacterData> getCharacter(String name, String apikey, String timestamp, String privateKey) {

        //network observable source
        Observable<CharacterData> api = apiService
                .getMarvelCharacter(name, apikey, timestamp, createHash(timestamp, apikey, privateKey))
                .flatMap(characterDataWrapper -> {
                    CharacterDataContainer data = characterDataWrapper.data();

                    List<CharacterData> results = data.results();
                    if (results != null && results.size() != 0) {
                        return Observable.just(results.get(0));
                    } else {
                        return Observable.error(new EmptyResultsException("No Character information contained"));
                    }

                })
                .doOnNext(data -> {

                    if (data != null) {
                        Log.i(TAG, "getCharacter: Writing to db....");
                        databaseDataSource.insertCharacter(data);
                    }
                });


        //disk (SQLite DB) observable source
        Observable<CharacterData> disk = databaseDataSource.getCharacter(name);

        //RX design pattern to implement caching. Data is fetched first from the disk if available before the Api.
        return Observable.concat(disk, api).first(characterData -> {
            return characterData != null;
        });
    }

    @Override
    public Observable<MarvelCharacter> getAllCharacters() {
        return databaseDataSource.getAllCharacters().flatMap(dataList -> {
            List<MarvelCharacter> marvelCharacters = new ArrayList<>();
            for (CharacterData data : dataList) {
                marvelCharacters.add(DataConverter.toMarvelCharacter(data));
            }
            return Observable.from(marvelCharacters);
        });
    }

    @Override
    public Observable<MarvelCharacter> getCharacterByName(String name) {

        int millis = Calendar.getInstance().get(Calendar.MILLISECOND);
        String timestamp = String.valueOf(millis);

        //network observable source
        Observable<CharacterData> api = apiService
                .getMarvelCharacter(name, BuildConfig.PUBLIC_API_KEY_MARVEL, timestamp, createHash(timestamp, BuildConfig.PUBLIC_API_KEY_MARVEL, BuildConfig.PRIVATE_API_KEY_MARVEL))
                .flatMap(characterDataWrapper -> {
                    CharacterDataContainer data = characterDataWrapper.data();

                    List<CharacterData> results = data.results();
                    if (results != null && results.size() != 0) {
                        return Observable.just(results.get(0));
                    } else {
                        return Observable.error(new EmptyResultsException("No Character information contained"));
                    }

                })
                .doOnNext(data -> {

                    if (data != null) {
                        Log.i(TAG, "getCharacter: Writing to db....");
                        databaseDataSource.insertCharacter(data);
                    }
                });


        //disk (SQLite DB) observable source
        Observable<CharacterData> disk = databaseDataSource.getCharacter(name);

        //RX design pattern to implement caching. Data is fetched first from the disk if available before the Api.
        return Observable.concat(disk, api).first(characterData -> {
            return characterData != null;
        }).flatMap(characterData -> {
            return Observable.just(DataConverter.toMarvelCharacter(characterData));
        });
    }

    @Override
    public Observable<MarvelCharacter> getCharacterById(String id) {
        return databaseDataSource.getCharacterById(id)
                .doOnNext(characterData -> {
                    //once we reach this point a search has successfully executed, therefore, store it in the DB
                    databaseDataSource.insertRecentSearches(RecentSearches.builder().name(characterData.name())
                            .characterId(characterData.id())
                            .build());
                }).flatMap(characterData -> Observable.just(DataConverter.toMarvelCharacter(characterData)));
    }

    public Observable<List<RecentSearches>> getRecentSearches() {
        return databaseDataSource.getRecentSearches();
    }

    //Helper method to generate an MD5 hash necessary for communicating with the marvel api
    private String createHash(String timestamp, String apikey, String privateKey) {
        return new String(Hex.encodeHex(DigestUtils.md5(timestamp + privateKey + apikey)));
    }


    /**
     * An exception representing an empty result from the api. This will help subscribers in the presenter layer
     * understand what happened.
     */
    public static class EmptyResultsException extends Exception {
        public EmptyResultsException(String message) {
            super(message);

        }
    }
}
