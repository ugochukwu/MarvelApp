package com.onwordiesquire.mobile.marvelapp.presentation

/**
 * Created by michelonwordi on 10/23/16.
 */

interface Presenter<in V : MvpView?> {

    fun attachView(view: V)

    fun detachView()

}
