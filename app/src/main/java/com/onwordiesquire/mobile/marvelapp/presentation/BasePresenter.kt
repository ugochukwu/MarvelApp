package com.onwordiesquire.mobile.marvelapp.presentation

/**
 * Created by michelonwordi on 10/23/16.
 */

open class BasePresenter<T : MvpView?> : Presenter<T> {

    var mvpView: T? = null
        private set

    override fun attachView(view: T) {
        mvpView = view
    }

    override fun detachView() {
        mvpView = null
    }

    val isViewAttached: Boolean
        get() = mvpView != null

    class MvpViewNotAttachedException : RuntimeException("Please call attachView(MvpView) on the presenter before requesting data")
}
