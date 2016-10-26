package com.onwordiesquire.mobile.marvelapp.data.model;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;

/**
 * Created by michelonwordi on 10/25/16.
 */
@AutoValue
public abstract class RecentSearches {

    @Nullable
    public abstract String name();

    @Nullable
    public abstract String characterId();

    public static Builder builder() {
        return new AutoValue_RecentSearches.Builder();
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder name(String name);

        public abstract Builder characterId(String characterId);

        public abstract RecentSearches build();
    }
}
