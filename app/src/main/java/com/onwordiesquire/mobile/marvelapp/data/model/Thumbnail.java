package com.onwordiesquire.mobile.marvelapp.data.model;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

/**
 * Created by michelonwordi on 10/23/16.
 */
@AutoValue
public abstract class Thumbnail {

    @Nullable
    public abstract String path();

    @Nullable
    public abstract String extension();

    @Nullable
    private String id;


    public static Builder builder() {
        return new AutoValue_Thumbnail.Builder();
    }


    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder path(String path);

        public abstract Builder extension(String extension);

        public abstract Thumbnail build();
    }

    public static TypeAdapter<Thumbnail> typeAdapter(Gson gson) {
        return new AutoValue_Thumbnail.GsonTypeAdapter(gson);
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
