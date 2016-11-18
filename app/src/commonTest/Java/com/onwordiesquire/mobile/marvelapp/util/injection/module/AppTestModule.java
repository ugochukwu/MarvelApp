package com.onwordiesquire.mobile.marvelapp.util.injection.module;

import android.app.Application;
import android.content.Context;

import com.onwordiesquire.mobile.marvelapp.MarvelApp;
import com.onwordiesquire.mobile.marvelapp.data.CharacterDataRepositoryImpl;
import com.onwordiesquire.mobile.marvelapp.data.sources.local.DatabaseDataSource;
import com.onwordiesquire.mobile.marvelapp.data.sources.remote.MarvelApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.mock;

/**
 * Created by michelonwordi on 10/26/16.
 */
@Module
public class AppTestModule {

    public MarvelApp app;

    public AppTestModule(MarvelApp app) {
        this.app = app;
    }


    @Provides
    @Singleton
    public Application provideApplication() {
        return app;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return app;
    }

    @Provides
    @Singleton
    public MarvelApiService provideApi() {
        return mock(MarvelApiService.class);
    }

    @Provides
    @Singleton
    public CharacterDataRepositoryImpl providesDataManager() {
        return mock(CharacterDataRepositoryImpl.class);
    }

    @Provides
    @Singleton
    public DatabaseDataSource providesDataSource(){return mock(DatabaseDataSource.class);}
}
