package com.onwordiesquire.mobile.marvelapp;

import rx.Observable;

/**
 * Use cases are equivalent to interactors in the clean architecture. They operate in the domain layer
 * and are responsible for implementing business logic.
 * <p>
 * Created by michelonwordi on 11/17/16.
 */

public abstract class UseCase<Q extends UseCase.RequestValues, P extends UseCase.ResponseValues> {

    private Q requestValue;
    private P responseValue;


    public abstract Observable<P> buildObservable(Q requestValue);

    public Observable<P> execute(Q requestValue) {
        return buildObservable(requestValue);
    }



    /**
     * Data entry port
     */
    public interface RequestValues {

    }


    /**
     * Data output port
     */
    public interface ResponseValues {

    }
}
