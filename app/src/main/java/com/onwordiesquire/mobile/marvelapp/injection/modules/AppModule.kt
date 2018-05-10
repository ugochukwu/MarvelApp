package com.onwordiesquire.mobile.marvelapp.injection.modules

import android.app.Application
import android.content.Context

import com.onwordiesquire.mobile.marvelapp.MarvelApp

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

/**
 * Created by michelonwordi on 10/23/16.
 */
@Module
class AppModule(var app: MarvelApp) {

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
}
