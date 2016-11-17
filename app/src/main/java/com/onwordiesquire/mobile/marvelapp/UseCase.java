package com.onwordiesquire.mobile.marvelapp;

/**
 * Use cases are equivalent to interactors in the clean architecture. They operate in the domain layer
 * and are responsible for implementing business logic.
 *
 * Created by michelonwordi on 11/17/16.
 */

public abstract class UseCase<Q extends UseCase.RequestValues, P extends UseCase.ResponseValues> {

    private Q requestValue;
    private P responseValue;

    public Q getRequestValue() {
        return requestValue;
    }

    public void setRequestValue(Q requestValue) {
        this.requestValue = requestValue;
    }

    public P getResponseValue() {
        return responseValue;
    }

    public void setResponseValue(P responseValue) {
        this.responseValue = responseValue;
    }

    /**
     * Data entry port
     */
    interface RequestValues{

    }


    /**
     * Data output port
     */
    interface ResponseValues{

    }
}
