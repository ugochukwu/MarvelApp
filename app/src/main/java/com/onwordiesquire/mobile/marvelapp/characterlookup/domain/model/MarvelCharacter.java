package com.onwordiesquire.mobile.marvelapp.characterlookup.domain.model;

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

    public MarvelCharacter() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getResourceURI() {
        return resourceURI;
    }

    public void setResourceURI(String resourceURI) {
        this.resourceURI = resourceURI;
    }

    public List<String> getUrls() {
        return Urls;
    }

    public void setUrls(List<String> urls) {
        Urls = urls;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
