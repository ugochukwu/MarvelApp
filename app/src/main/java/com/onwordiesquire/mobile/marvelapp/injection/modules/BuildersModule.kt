package com.onwordiesquire.mobile.marvelapp.injection.modules

import com.onwordiesquire.mobile.marvelapp.presentation.characterSearch.SearchActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by wkda on 07.10.17.
 */
@Module
abstract class BuildersModule {

    @ContributesAndroidInjector
    abstract fun bindSearchActivity(): SearchActivity
}