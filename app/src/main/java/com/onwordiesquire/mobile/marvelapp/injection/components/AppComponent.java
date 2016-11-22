package com.onwordiesquire.mobile.marvelapp.injection.components;

import android.app.Application;

import com.onwordiesquire.mobile.marvelapp.data.CharacterDataRepository;
import com.onwordiesquire.mobile.marvelapp.data.sources.local.DatabaseDataSource;
import com.onwordiesquire.mobile.marvelapp.data.sources.remote.MarvelApiService;
import com.onwordiesquire.mobile.marvelapp.injection.module.AppModule;
import com.onwordiesquire.mobile.marvelapp.characterlookup.presentation.MainActivity;
import com.onwordiesquire.mobile.marvelapp.presentation.characterDetails.DetailsActivity;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;
import rx.Scheduler;

/**
 * Created by michelonwordi on 10/23/16.
 */
@Component(modules = AppModule.class)
@Singleton
public interface AppComponent {

    void inject(DetailsActivity detailsActivity);

    //expose data manager to downstream components
    CharacterDataRepository dataManager();

    DatabaseDataSource databaseDataSource();

    MarvelApiService marvelApiService();
    Application app();

    @Named("uiThread")
    Scheduler uiScheduler();

    @Named("executorThread")
    Scheduler executorThread();

}
