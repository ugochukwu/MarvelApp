package com.onwordiesquire.mobile.marvelapp.presentation.characterSearch

import com.onwordiesquire.mobile.marvelapp.data.MarvelDataManager
import com.onwordiesquire.mobile.marvelapp.presentation.BasePresenter
import com.onwordiesquire.mobile.marvelapp.util.EmptyResultsException
import com.onwordiesquire.mobile.marvelapp.util.VISIBLE
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by michel.onwordi on 10/07/2017.
 */
class SearchPresenter @Inject constructor(val dataManager: MarvelDataManager) : BasePresenter<SearchView>() {

    override fun attachView(view: SearchView) {
        super.attachView(view)
        compositeSubscription = CompositeDisposable()
    }

    lateinit var compositeSubscription: CompositeDisposable

    override fun detachView() {
        super.detachView()
        compositeSubscription.dispose()
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
                            it.showEmptyState("Sorry can't find that name")
                        } else {
                            it.showError("An Error Happened")
                            Timber.e(error.message, error)
                        }
                    })
        }

    }

    fun onSearchClick(textValue: String) =
            if (textValue.isEmpty()) {
                Timber.i("Search field Not empty")

            } else {

                Timber.i("Search field  empty")
            }
}