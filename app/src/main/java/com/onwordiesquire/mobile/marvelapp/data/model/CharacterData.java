package com.onwordiesquire.mobile.marvelapp.data.model;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by michelonwordi on 10/23/16.
 */
@AutoValue
public abstract class CharacterData {


    @Nullable
    public abstract String id();

    @Nullable
    public abstract String name();

    @Nullable
    public abstract String description();

    @Nullable
    public abstract String modified();

    @Nullable
    public abstract String resourceURI();

    @Nullable
    public abstract List<Url> urls();

    @Nullable
    public abstract Thumbnail thumbnail();


    public static Builder builder() {
        return new AutoValue_CharacterData.Builder();
    }

    public static TypeAdapter<CharacterData> typeAdapter(Gson gson) {
        return new AutoValue_CharacterData.GsonTypeAdapter(gson);
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder id(String id);

        public abstract Builder name(String name);

        public abstract Builder description(String desc);

        public abstract Builder modified(String modified);

        public abstract Builder resourceURI(String uri);

        public abstract Builder urls(List<Url> urls);

        public abstract Builder thumbnail(Thumbnail thumbnail);

        public abstract CharacterData build();

    }
}
