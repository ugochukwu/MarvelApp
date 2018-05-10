package com.onwordiesquire.mobile.marvelapp.injection.components

import com.onwordiesquire.mobile.marvelapp.injection.modules.ApiModule
import com.onwordiesquire.mobile.marvelapp.injection.modules.AppModule
import com.onwordiesquire.mobile.marvelapp.presentation.characterSearch.SearchActivity

import javax.inject.Singleton

import dagger.Component

/**
 * Created by michelonwordi on 10/23/16.
 */
@Component(modules = arrayOf(AppModule::class, ApiModule::class))
@Singleton
interface MainComponent {
    fun inject(searchActivity: SearchActivity)
}
