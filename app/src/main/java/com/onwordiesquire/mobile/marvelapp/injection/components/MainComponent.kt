package com.onwordiesquire.mobile.marvelapp.injection.components

import com.onwordiesquire.mobile.marvelapp.data.DataManager
import com.onwordiesquire.mobile.marvelapp.data.sources.local.DatabaseDataSource
import com.onwordiesquire.mobile.marvelapp.data.sources.remote.MarvelApiService
import com.onwordiesquire.mobile.marvelapp.injection.module.ApiModule
import com.onwordiesquire.mobile.marvelapp.injection.module.AppModule
import com.onwordiesquire.mobile.marvelapp.presentation.SearchCharacter.MainActivity
import com.onwordiesquire.mobile.marvelapp.presentation.characterDetails.DetailsActivity

import javax.inject.Singleton

import dagger.Component

/**
 * Created by michelonwordi on 10/23/16.
 */
@Component(modules = arrayOf(AppModule::class, ApiModule::class))
@Singleton
interface MainComponent {
    fun inject(activity: MainActivity)

    fun inject(detailsActivity: DetailsActivity)

    //expose data manager to downstream components
    fun dataManager(): DataManager

    fun databaseDataSource(): DatabaseDataSource

    fun marvelApiService(): MarvelApiService
}
