package com.onwordiesquire.mobile.marvelapp;

import com.onwordiesquire.mobile.marvelapp.characterdetails.domain.model.MarvelCharacter;
import com.onwordiesquire.mobile.marvelapp.data.model.CharacterData;
import com.onwordiesquire.mobile.marvelapp.data.model.Url;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by michelonwordi on 11/17/16.
 */

public class DataConverter {

    public static MarvelCharacter toMarvelCharacter(CharacterData characterData) {
        List<String> urls = new ArrayList<>();
        for (Url url : characterData.urls()) {
            urls.add(url.url());
        }

        String thumbnail = characterData.thumbnail() != null ? characterData.thumbnail().path() : null;

        return new MarvelCharacter(characterData.id(), characterData.name(), characterData.description(), characterData.modified()
                , characterData.resourceURI(), urls, thumbnail);
    }
}
