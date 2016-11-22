package com.onwordiesquire.mobile.marvelapp.characterlookup.domain.model;

/**
 * Created by michelonwordi on 11/21/16.
 */

public class SearchTerm {

    private String name;
    private String characterId;

    public SearchTerm(String name, String characterId) {
        this.name = name;
        this.characterId = characterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharacterId() {
        return characterId;
    }

    public void setCharacterId(String characterId) {
        this.characterId = characterId;
    }
}
