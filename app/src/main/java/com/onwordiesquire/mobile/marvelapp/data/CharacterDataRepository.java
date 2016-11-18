package com.onwordiesquire.mobile.marvelapp.data;

import com.onwordiesquire.mobile.marvelapp.characterdetails.domain.model.MarvelCharacter;
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

}
