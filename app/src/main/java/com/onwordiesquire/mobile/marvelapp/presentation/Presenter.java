package com.onwordiesquire.mobile.marvelapp.presentation;

/**
 * Created by michelonwordi on 10/23/16.
 */

public interface Presenter<V extends MvpView> {

    public void attachView(V view);

    public void detachView();
}
