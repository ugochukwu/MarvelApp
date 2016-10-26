package com.onwordiesquire.mobile.marvelapp.injection.module;

import android.app.Application;
import android.content.Context;

import com.onwordiesquire.mobile.marvelapp.MarvelApp;
import com.onwordiesquire.mobile.marvelapp.data.sources.remote.MarvelApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by michelonwordi on 10/23/16.
 */
@Module
public class AppModule {

    public MarvelApp app;

    public AppModule(MarvelApp app)
    {
        this.app = app;
    }


    @Provides
    @Singleton
    public Application provideApplication()
    {
        return app;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return app;
    }

    @Provides
    @Singleton
    public MarvelApiService provideApi()
    {
        return  MarvelApiService.HELPER.newMarvelApiService();
    }
}
