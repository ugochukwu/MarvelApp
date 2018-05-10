package com.onwordiesquire.mobile.marvelapp.presentation

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection

/**
 * Created by michel.onwordi on 13/07/2017.
 */
abstract class BaseActivity : AppCompatActivity() {

    protected lateinit var presenter: BasePresenter<out MvpView>

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        AndroidInjection.inject(this)
        initializePresenter()
    }

    abstract fun initializePresenter()
}