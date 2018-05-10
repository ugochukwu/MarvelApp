package com.onwordiesquire.mobile.marvelapp.presentation.characterSearch.module

import com.onwordiesquire.mobile.marvelapp.data.MarvelDataManager
import com.onwordiesquire.mobile.marvelapp.presentation.characterSearch.SearchPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by wkda on 07.10.17.
 */
@Module
class SearchActivityModule {

    @Provides
    fun providePresenter(dataManager: MarvelDataManager) = SearchPresenter(dataManager)
}