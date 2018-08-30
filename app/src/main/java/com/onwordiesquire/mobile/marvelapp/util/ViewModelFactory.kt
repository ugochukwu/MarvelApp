package com.onwordiesquire.mobile.marvelapp.util

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.onwordiesquire.mobile.marvelapp.presentation.characterSearch.SearchViewModel
import com.onwordiesquire.mobile.marvelapp.presentation.characterSearch.domain.GetRecentSearchList
import javax.inject.Inject

/**
 * Created by michel.onwordi on 04.11.17.
 */
class ViewModelFactory @Inject constructor()
    : ViewModelProvider.Factory {

    @Inject
    lateinit var getRecentSearchList: GetRecentSearchList


    override fun <T : ViewModel?> create(modelClass: Class<T>) = when {
        modelClass.isAssignableFrom(SearchViewModel::class.java) -> SearchViewModel(getRecentSearchList) as T
        else -> throw IllegalArgumentException("Unsupported viewmodel")
    }

}