package com.onwordiesquire.mobile.marvelapp.presentation;

/**
 * Created by michelonwordi on 10/23/16.
 */

public class BasePresenter<T extends MvpView> implements Presenter<T>{

    private T mvpView;

    @Override
    public void attachView(T view) {
        mvpView = view;
    }

    @Override
    public void detachView() {
        mvpView = null;
    }

    public T getMvpView()
    {
        return mvpView;
    }

    public boolean isViewAttached()
    {
        return mvpView != null;
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call attachView(MvpView) on the presenter before requesting data");
        }
    }
}
