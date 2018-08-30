package com.onwordiesquire.mobile.marvelapp.presentation.characterSearch.domain

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import com.onwordiesquire.mobile.marvelapp.common.domain.SuccessResponse
import com.onwordiesquire.mobile.marvelapp.common.domain.UseCase
import com.onwordiesquire.mobile.marvelapp.common.domain.UseCaseRequest
import com.onwordiesquire.mobile.marvelapp.common.domain.UseCaseResponse
import com.onwordiesquire.mobile.marvelapp.data.MarvelDataManager
import com.onwordiesquire.mobile.marvelapp.data.model.RecentSearch
import com.onwordiesquire.mobile.marvelapp.data.responseModel.RecentSearchEntity
import javax.inject.Inject

/**
 * Created by michel.onwordi on 01.11.17.
 */
class GetRecentSearchList @Inject constructor(private val dataManager: MarvelDataManager)
    : UseCase<RequestRecentSearches, UseCaseResponse<LiveData<List<RecentSearch>>>> {

    override fun execute(request: RequestRecentSearches): UseCaseResponse<LiveData<List<RecentSearch>>> =
            SuccessResponse(Transformations.map(dataManager.getRecentSearchList(),
                    { rawRecentSearchList -> recentSearchesMapper(rawRecentSearchList) }))

    private fun recentSearchesMapper(rawRecentSearchList: List<RecentSearchEntity>) =
            rawRecentSearchList.map { RecentSearch(it.name, it.characterId) }.toList()
}

class RequestRecentSearches : UseCaseRequest()