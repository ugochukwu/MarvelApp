package com.onwordiesquire.mobile.marvelapp.runner;

import android.os.Bundle;
import android.support.test.espresso.Espresso;
import android.support.test.runner.AndroidJUnitRunner;

import com.onwordiesquire.mobile.marvelapp.util.RxIdlingExecutionHook;
import com.onwordiesquire.mobile.marvelapp.util.RxIdlingResource;

import rx.plugins.RxJavaPlugins;


/**
 * Runner that registers a Espresso Indling resource that handles waiting for
 * RxJava Observables to finish.
 * WARNING - Using this runner will block the tests if the application uses long-lived hot
 * Observables such us event buses, etc.
 */
public class RxAndroidJUnitRunner extends AndroidJUnitRunner {

    @Override
    public void onCreate(Bundle arguments) {
        super.onCreate(arguments);
        RxIdlingResource rxIdlingResource = new RxIdlingResource();
        RxJavaPlugins.getInstance()
                .registerObservableExecutionHook(new RxIdlingExecutionHook(rxIdlingResource));
        Espresso.registerIdlingResources(rxIdlingResource);
    }
}
