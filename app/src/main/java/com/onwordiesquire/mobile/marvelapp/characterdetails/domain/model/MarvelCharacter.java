package com.onwordiesquire.mobile.marvelapp.characterdetails.domain.model;

import java.net.URL;
import java.util.List;


/**
 * Business logic model representing the data concerned with a character in our code.
 * Created by michelonwordi on 11/17/16.
 */

public class MarvelCharacter {

    private String id;
    private String name;
    private String description;
    private String modified;
    private String resourceURI;
    private List<String> Urls;
    private String thumbnail;

    public MarvelCharacter(String id, String name, String description, String modified, String resourceURI, List<String> urls, String thumbnail) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.modified = modified;
        this.resourceURI = resourceURI;
        Urls = urls;
        this.thumbnail = thumbnail;
    }
}
