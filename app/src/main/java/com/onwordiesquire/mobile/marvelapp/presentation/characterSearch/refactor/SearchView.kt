package com.onwordiesquire.mobile.marvelapp.presentation.characterSearch.refactor

import com.onwordiesquire.mobile.marvelapp.data.refactor.data.MarvelCharacter
import com.onwordiesquire.mobile.marvelapp.data.refactor.data.RecentSearches
import com.onwordiesquire.mobile.marvelapp.presentation.MvpView

/**
 * Created by michel.onwordi on 10/07/2017.
 */
interface SearchView : MvpView {
    fun displayLastFiveSearches(searches: List<RecentSearches>)
    fun showError(message: String)
    fun showEmptyState(message: String)
    fun showCharacterDetails(marvelCharacter: MarvelCharacter)
    fun showProgress(visible: Boolean)
    fun initList()
}