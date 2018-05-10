package com.onwordiesquire.mobile.marvelapp.injection.components

import com.onwordiesquire.mobile.marvelapp.data.sources.local.DatabaseDataSource
import com.onwordiesquire.mobile.marvelapp.data.sources.remote.MarvelApiService
import com.onwordiesquire.mobile.marvelapp.injection.modules.ApiModule
import com.onwordiesquire.mobile.marvelapp.injection.modules.AppModule
import com.onwordiesquire.mobile.marvelapp.presentation.characterSearch.MainActivity
import com.onwordiesquire.mobile.marvelapp.presentation.characterSearch.refactor.SearchActivity
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

    fun databaseDataSource(): DatabaseDataSource

    fun marvelApiService(): MarvelApiService
    fun inject(searchActivity: SearchActivity)
}
