package com.onwordiesquire.mobile.marvelapp.data.model;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

/**
 * Created by michelonwordi on 10/21/16.
 */
@AutoValue
public abstract class CharacterDataWrapper {

    public abstract int code();

    @Nullable
    public abstract String status();

    @Nullable
    public abstract String copyright();

    @Nullable
    public abstract String attributionText();

    @Nullable
    public abstract String attributionHtml();

    @Nullable
    public abstract CharacterDataContainer data();

    @Nullable
    public abstract String etag();

    public static Builder builder() {
        return new AutoValue_CharacterDataWrapper.Builder();
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder code(int code);

        public abstract Builder status(String status);

        public abstract Builder copyright(String copyright);

        public abstract Builder attributionText(String attributionText);

        public abstract Builder attributionHtml(String html);

        public abstract Builder data(CharacterDataContainer data);

        public abstract Builder etag(String etag);

        public abstract CharacterDataWrapper build();
    }

    public static TypeAdapter<CharacterDataWrapper> typeAdapter(Gson gson) {
        return new AutoValue_CharacterDataWrapper.GsonTypeAdapter(gson);
    }
}
