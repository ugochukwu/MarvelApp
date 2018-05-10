package com.onwordiesquire.mobile.marvelapp.injection.modules

import android.arch.persistence.room.Room
import com.onwordiesquire.mobile.marvelapp.MarvelApp
import com.onwordiesquire.mobile.marvelapp.data.MarvelDataManager
import com.onwordiesquire.mobile.marvelapp.data.sources.local.MarvelAppDatabase
import com.onwordiesquire.mobile.marvelapp.data.sources.local.RecentSearchDao
import com.onwordiesquire.mobile.marvelapp.data.sources.remote.MarvelApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

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
    fun providesDataManager(remoteDataSource: MarvelApi, recentSearchDao: RecentSearchDao) = MarvelDataManager(remoteDataSource, recentSearchDao)

    @Provides
    @Singleton
    fun providesDatabase(app: MarvelApp) = Room.databaseBuilder(app.applicationContext, MarvelAppDatabase::class.java, "Marvel.db")

    @Provides
    @Singleton
    fun providesLocalDataSource(database: MarvelAppDatabase) = database.recentSearchDao()
}
