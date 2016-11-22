package com.onwordiesquire.mobile.marvelapp.presentation;

/**
 * Created by michelonwordi on 10/23/16.
 */

public interface Presenter<V extends MvpView> {

     void attachView(V view);

     void detachView();
}
