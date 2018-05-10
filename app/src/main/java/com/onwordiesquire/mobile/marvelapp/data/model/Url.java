package com.onwordiesquire.mobile.marvelapp.data.model;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

/**
 * Created by michelonwordi on 10/23/16.
 */
@AutoValue
public abstract class Url {

    @Nullable
    public abstract String type();

    @Nullable
    public abstract String url();

    public static Builder builder() {
        return new AutoValue_Url.Builder();
    }

    public static TypeAdapter<Url> typeAdapter(Gson gson) {
        return new AutoValue_Url.GsonTypeAdapter(gson);
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder type(String type);

        public abstract Builder url(String url);

        public abstract Url builder();
    }
}
