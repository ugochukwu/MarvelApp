package com.onwordiesquire.mobile.marvelapp

import android.app.Activity
import android.app.Application
import android.content.Context

import com.crashlytics.android.Crashlytics
import com.facebook.stetho.Stetho
import com.onwordiesquire.mobile.marvelapp.injection.components.DaggerMainComponent
import com.onwordiesquire.mobile.marvelapp.injection.components.MainComponent
import com.onwordiesquire.mobile.marvelapp.injection.modules.AppModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector

import io.fabric.sdk.android.Fabric
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by michelonwordi on 10/23/16.
 */

class MarvelApp : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)

        DaggerMainComponent.builder()
                .application(this)
                .build()
                .inject(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Fabric.with(this, Crashlytics())
        }
    }

    operator fun get(context: Context): MarvelApp {
        return context.applicationContext as MarvelApp
    }

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector
}
