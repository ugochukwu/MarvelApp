package com.onwordiesquire.mobile.marvelapp.presentation.characterSearch

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.onwordiesquire.mobile.marvelapp.data.model.RecentSearch
import com.onwordiesquire.mobile.marvelapp.presentation.characterSearch.domain.GetRecentSearchList
import com.onwordiesquire.mobile.marvelapp.presentation.characterSearch.domain.RequestRecentSearches
import javax.inject.Inject

/**
 * Created by wkda on 07.10.17.
 */
class SearchViewModel @Inject constructor(private val getRecentSearchList: GetRecentSearchList) : ViewModel() {

    private val recentSearchList: MutableLiveData<List<RecentSearch>> = MutableLiveData()

    fun recentSearches(): LiveData<List<RecentSearch>> = recentSearchList

    fun loadData() {
        loadRecentSearchList()
    }

    private fun loadRecentSearchList() = getRecentSearchList.execute(RequestRecentSearches())
}