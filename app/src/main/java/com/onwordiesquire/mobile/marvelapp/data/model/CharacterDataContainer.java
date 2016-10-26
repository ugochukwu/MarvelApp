package com.onwordiesquire.mobile.marvelapp.data.model;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by michelonwordi on 10/21/16.
 */
@AutoValue
public abstract class CharacterDataContainer {

    @Nullable
    public abstract String offset();

    @Nullable
    public abstract String limit();

    @Nullable
    public abstract String total();

    @Nullable
    public abstract String count();

    @Nullable
    public abstract List<CharacterData> results();

    public static Builder builder()
    {
        return new AutoValue_CharacterDataContainer.Builder();
    }

    public static TypeAdapter<CharacterDataContainer> typeAdapter(Gson gson)
    {
        return new AutoValue_CharacterDataContainer.GsonTypeAdapter(gson);
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder offset(String offset);

        public abstract Builder limit(String limit);

        public abstract Builder total(String total);

        public abstract Builder count(String count);

        public abstract Builder results(List<CharacterData> results);

        public abstract CharacterDataContainer build();

    }

}
