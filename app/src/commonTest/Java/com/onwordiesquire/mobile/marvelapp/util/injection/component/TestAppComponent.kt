package com.onwordiesquire.mobile.marvelapp.util.injection.component

import com.onwordiesquire.mobile.marvelapp.injection.components.MainComponent
import com.onwordiesquire.mobile.marvelapp.util.injection.module.AppTestModule

import javax.inject.Singleton

import dagger.Component

/**
 * Created by michelonwordi on 10/26/16.
 */
@Singleton
@Component(modules = arrayOf(AppTestModule::class))
interface TestAppComponent : MainComponent
