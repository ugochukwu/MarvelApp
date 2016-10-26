package com.onwordiesquire.mobile.marvelapp.data.model;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by michelonwordi on 10/23/16.
 */
@AutoValue
public abstract class Comics {
    public abstract String available();

    public abstract String returned();

    public abstract String collectionURI();

    @SerializedName("items")
    public abstract List<SummaryItem> commicSummaries();

    public static Builder builder() {
        return new AutoValue_Comics.Builder();
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder available(String available);

        public abstract Builder returned(String returned);

        public abstract Builder collectionURI(String collectionURI);

        public abstract Builder commicSummaries(List<SummaryItem> items);

        public abstract Comics build();
    }

    public static TypeAdapter<Comics> typeAdapter(Gson gson)
    {
        return new AutoValue_Comics.GsonTypeAdapter(gson);
    }

}
