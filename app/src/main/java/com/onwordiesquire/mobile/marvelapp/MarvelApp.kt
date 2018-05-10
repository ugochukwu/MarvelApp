package com.onwordiesquire.mobile.marvelapp

import android.app.Application
import android.content.Context

import com.crashlytics.android.Crashlytics
import com.facebook.stetho.Stetho
import com.onwordiesquire.mobile.marvelapp.injection.components.DaggerMainComponent
import com.onwordiesquire.mobile.marvelapp.injection.components.MainComponent
import com.onwordiesquire.mobile.marvelapp.injection.modules.AppModule

import io.fabric.sdk.android.Fabric
import timber.log.Timber

/**
 * Created by michelonwordi on 10/23/16.
 */

class MarvelApp : Application() {

    companion object {
        lateinit var component: MainComponent
        lateinit var app: MarvelApp
    }

    override fun onCreate() {
        super.onCreate()
        Fabric.with(this, Crashlytics())
        app = this
        component = DaggerMainComponent.builder().appModule(AppModule(this)).build()
        Stetho.initializeWithDefaults(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    operator fun get(context: Context): MarvelApp {
        return context.applicationContext as MarvelApp
    }
}
