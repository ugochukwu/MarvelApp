package com.onwordiesquire.mobile.marvelapp.presentation.SearchCharacter.refactor

import com.onwordiesquire.mobile.marvelapp.data.refactor.MarvelDataManager
import com.onwordiesquire.mobile.marvelapp.presentation.BasePresenter
import com.onwordiesquire.mobile.marvelapp.util.EmptyResultsException
import com.onwordiesquire.mobile.marvelapp.util.VISIBLE
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by michel.onwordi on 10/07/2017.
 */
class SearchPresenter @Inject constructor(val dataManager: MarvelDataManager) : BasePresenter<SearchView>() {
    lateinit var compositeSubscription: CompositeSubscription

    override fun attachView(view: SearchView?) {
        super.attachView(view)
        compositeSubscription = CompositeSubscription()
    }

    override fun detachView() {
        super.detachView()
        compositeSubscription.clear()
    }

    fun findCharacter(name: String, timeStamp: String) {
        mvpView?.let {
            it.showProgress(VISIBLE)
            dataManager.getCharacter(name, timeStamp)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ character ->
                        it.showProgress(!VISIBLE)
                        it.showCharacterDetails(character)
                    }, { error ->
                        it.showProgress(!VISIBLE)
                        if (error is EmptyResultsException) {
                            it.showEmptyState()
                        } else {
                            it.showError("An Error Happened")
                            Timber.e(error.message, error)
                        }
                    })
        }

    }
}