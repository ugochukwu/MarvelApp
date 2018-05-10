package com.onwordiesquire.mobile.marvelapp.presentation.characterSearch

import android.arch.lifecycle.ViewModel
import com.onwordiesquire.mobile.marvelapp.data.MarvelDataManager
import javax.inject.Inject

/**
 * Created by wkda on 07.10.17.
 */
class SearchViewModel @Inject constructor(val dataManager: MarvelDataManager) : ViewModel() {
    init {
        //todo observe recent searches and react
    }
}