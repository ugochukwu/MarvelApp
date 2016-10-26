package com.onwordiesquire.mobile.marvelapp.data.model;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

/**
 * Created by michelonwordi on 10/23/16.
 */
@AutoValue
public abstract class SummaryItem {

    @Nullable
    public abstract String resourceURI();

    @Nullable
    public abstract String name();

    @Nullable
    public abstract String type();

    public static Builder builder()
    {
        return new AutoValue_SummaryItem.Builder();
    }

    @AutoValue.Builder
    public static abstract class Builder{
        public abstract Builder resourceURI(String resourceURI);
        public abstract Builder name(String name);
        public abstract Builder type(String type);
        public abstract SummaryItem build();
    }

    public static TypeAdapter<SummaryItem> typeAdapter(Gson gson)
    {
        return new AutoValue_SummaryItem.GsonTypeAdapter(gson);
    }
}
