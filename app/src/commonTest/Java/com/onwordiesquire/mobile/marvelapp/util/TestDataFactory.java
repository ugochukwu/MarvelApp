package com.onwordiesquire.mobile.marvelapp.util;

import com.onwordiesquire.mobile.marvelapp.characterlookup.domain.model.MarvelCharacter;
import com.onwordiesquire.mobile.marvelapp.data.model.CharacterData;
import com.onwordiesquire.mobile.marvelapp.data.model.CharacterDataContainer;
import com.onwordiesquire.mobile.marvelapp.data.model.CharacterDataWrapper;
import com.onwordiesquire.mobile.marvelapp.data.model.RecentSearches;
import com.onwordiesquire.mobile.marvelapp.data.model.Thumbnail;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by michelonwordi on 10/24/16.
 */

public class TestDataFactory {

    public static List<CharacterData> generateFakeMarvelCharacters(int count) {
        List<CharacterData> characterDataList = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            characterDataList.add(generateFakeCharacterDataObject(i));

        }

        return characterDataList;
    }

    public static CharacterData generateFakeCharacterDataObject(int i) {
        return CharacterData.builder().id(String.format("character%d", i))
                .name(String.format("Iron %d Heroe", i))
                .description("Marvels mighty mutants go worldwide and beyond in this series following Cyclops, Wolverine, Beast, Emma Frost and more in their astonishing adventures. Amazing Spider-Man is the cornerstone of the Marvel Universe.")
                .modified(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(new Date()))
                .thumbnail(Thumbnail.builder().extension("jpg").path(String.format("pic%d", i)).build())
                .resourceURI(String.format("http://gateway.marvel.com/v1/public/characters/%d", i))
                .build();
    }


    public static MarvelCharacter generateFakeMarvelCharacter(int i) {

        return new MarvelCharacter(String.format("character%d", i), String.format("Iron %d Heroe", i)
                , "Marvels mighty mutants go worldwide and beyond in this series following Cyclops, Wolverine, Beast, Emma Frost and more in their astonishing adventures. Amazing Spider-Man is the cornerstone of the Marvel Universe."
                , new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(new Date())
                , String.format("http://gateway.marvel.com/v1/public/characters/%d", i)
                , Arrays.asList("Url1", "Url2")
                , String.format("http://thumbnail/pic%d", i));
    }

    public static CharacterDataWrapper generateFakeDataWrapper() {
        //arrange
        CharacterDataContainer dataContainer = CharacterDataContainer.builder()
                .results(generateFakeMarvelCharacters(3))
                .build();
        CharacterDataWrapper dataWrapper = CharacterDataWrapper.builder()
                .data(dataContainer)
                .code(200)
                .build();
        return dataWrapper;
    }

    public static List<RecentSearches> generateRecentSearchResults(int count) {
        List<RecentSearches> recentSearchesList = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            recentSearchesList.add(RecentSearches.builder().characterId(String.format("Heroexxx_%d", i))
                    .name(String.format("Heroe %d", i)).build());
        }

        return recentSearchesList;
    }
}
