package com.onwordiesquire.mobile.marvelapp;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;
import com.onwordiesquire.mobile.marvelapp.injection.components.AppComponent;
import com.onwordiesquire.mobile.marvelapp.injection.components.DaggerAppComponent;
import com.onwordiesquire.mobile.marvelapp.injection.module.AppModule;

import timber.log.Timber;

/**
 * Created by michelonwordi on 10/23/16.
 */

public class MarvelApp extends Application {

    public AppComponent component;
    private static MarvelApp app;

    @Override
    public void onCreate() {
        super.onCreate();
        this.app = this;
        component = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        Stetho.initializeWithDefaults(this);

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    public static MarvelApp get() {
        return app;
    }

    public static MarvelApp get(Context context) {
        return (MarvelApp) context.getApplicationContext();
    }

    public AppComponent getComponent() {
        return component;
    }

    public void setComponent(AppComponent component) {
        this.component = component;
    }
}
