package com.onwordiesquire.mobile.marvelapp.util.injection.module;

import android.app.Application;
import android.content.Context;

import com.onwordiesquire.mobile.marvelapp.MarvelApp;
import com.onwordiesquire.mobile.marvelapp.data.CharacterDataRepository;
import com.onwordiesquire.mobile.marvelapp.data.CharacterDataRepositoryImpl;
import com.onwordiesquire.mobile.marvelapp.data.sources.local.DatabaseDataSource;
import com.onwordiesquire.mobile.marvelapp.data.sources.remote.MarvelApiService;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
    public CharacterDataRepository providesDataManager() {
        return mock(CharacterDataRepositoryImpl.class);
    }

    @Provides
    @Singleton
    public DatabaseDataSource providesDataSource(){return mock(DatabaseDataSource.class);}

    @Provides
    @Singleton
    @Named("uiThread")
    public Scheduler provideUiScheduler()
    {
        return AndroidSchedulers.mainThread();
    }

    @Provides
    @Singleton
    @Named("executorThread")
    public Scheduler provideExecutorScheduler()
    {
        return Schedulers.newThread();
    }
}
