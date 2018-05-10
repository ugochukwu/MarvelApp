package com.onwordiesquire.mobile.marvelapp.util.injection.module

import android.app.Application
import android.content.Context

import com.onwordiesquire.mobile.marvelapp.MarvelApp
import com.onwordiesquire.mobile.marvelapp.data.DataManager
import com.onwordiesquire.mobile.marvelapp.data.sources.local.DatabaseDataSource
import com.onwordiesquire.mobile.marvelapp.data.sources.remote.MarvelApiService

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

import org.mockito.Mockito.mock

/**
 * Created by michelonwordi on 10/26/16.
 */
@Module
class AppTestModule(var app: MarvelApp) {


    @Provides
    @Singleton
    fun provideApplication(): Application {
        return app
    }

    @Provides
    @Singleton
    fun provideContext(): Context {
        return app
    }

    @Provides
    @Singleton
    fun provideApi(): MarvelApiService {
        return mock(MarvelApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesDataManager(): DataManager {
        return mock(DataManager::class.java)
    }

    @Provides
    @Singleton
    fun providesDataSource(): DatabaseDataSource {
        return mock(DatabaseDataSource::class.java)
    }
}
