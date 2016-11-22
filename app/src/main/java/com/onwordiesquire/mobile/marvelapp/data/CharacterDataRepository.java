package com.onwordiesquire.mobile.marvelapp.data;

import com.onwordiesquire.mobile.marvelapp.characterlookup.domain.model.MarvelCharacter;
import com.onwordiesquire.mobile.marvelapp.data.model.RecentSearches;

import java.util.List;

import rx.Observable;

/**
 * Created by michelonwordi on 11/18/16.
 */

public interface CharacterDataRepository {

    Observable<MarvelCharacter> getCharacterByName(String name);

    Observable<MarvelCharacter> getCharacterById(String id);

    Observable<List<RecentSearches>> getRecentSearches();

    Observable<MarvelCharacter> getAllCharacters();

    /**
     * An exception representing an empty result from the api. This will help subscribers in the presenter layer
     * understand what happened.
     */
    class EmptyResultsException extends Exception {
        public EmptyResultsException(String message) {
            super(message);

        }
    }
}
