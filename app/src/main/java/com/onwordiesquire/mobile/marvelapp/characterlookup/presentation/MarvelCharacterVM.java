package com.onwordiesquire.mobile.marvelapp.characterlookup.presentation;

import java.util.List;

/**
 * Created by michelonwordi on 11/18/16.
 */

public class MarvelCharacterVM {

    private String id;
    private String name;
    private String description;
    private String modified;
    private String resourceURI;
    private List<String> Urls;
    private String thumbnail;

    public MarvelCharacterVM(String id, String name, String description, String modified, String resourceURI, List<String> urls, String thumbnail) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.modified = modified;
        this.resourceURI = resourceURI;
        Urls = urls;
        this.thumbnail = thumbnail;
    }

}
