package com.onwordiesquire.mobile.marvelapp.injection.modules

import android.app.Application
import android.content.Context

import com.onwordiesquire.mobile.marvelapp.MarvelApp
import com.onwordiesquire.mobile.marvelapp.data.MarvelDataManager
import com.onwordiesquire.mobile.marvelapp.data.sources.remote.MarvelApi

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

/**
 * Created by michelonwordi on 10/23/16.
 */
@Module
class AppModule {

    @Provides
    @Singleton
    fun providesContext(app: MarvelApp) = app.applicationContext

    @Provides
    @Singleton
    fun providesDataManager(remoteDataSource: MarvelApi) = MarvelDataManager(remoteDataSource)
}
