package com.onwordiesquire.mobile.marvelapp.injection.components;

import android.app.Activity;

import com.onwordiesquire.mobile.marvelapp.injection.PerActivity;
import com.onwordiesquire.mobile.marvelapp.injection.module.ActivityModule;

import dagger.Component;

/**
 * Created by michelonwordi on 11/22/16.
 */
@PerActivity
@Component(dependencies = AppComponent.class,modules = {ActivityModule.class})
public interface ActivityComponent {

}
