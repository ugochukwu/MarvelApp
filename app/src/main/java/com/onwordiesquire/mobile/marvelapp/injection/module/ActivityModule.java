package com.onwordiesquire.mobile.marvelapp.injection.module;

import android.app.Activity;

import com.onwordiesquire.mobile.marvelapp.injection.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by michelonwordi on 11/22/16.
 */
@Module
public class ActivityModule {

    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @PerActivity
    @Provides
    public Activity provideActivity()
    {
        return this.activity;
    }
}
