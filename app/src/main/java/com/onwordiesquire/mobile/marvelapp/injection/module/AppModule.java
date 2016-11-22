package com.onwordiesquire.mobile.marvelapp.injection.module;

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

    @Provides
    @Singleton
    public CharacterDataRepository characterDataRepository(MarvelApiService service, DatabaseDataSource databaseDataSource)
    {
        return new CharacterDataRepositoryImpl(service,databaseDataSource);
    }
}
