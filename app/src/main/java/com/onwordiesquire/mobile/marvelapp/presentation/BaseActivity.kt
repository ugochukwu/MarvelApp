package com.onwordiesquire.mobile.marvelapp.presentation

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import butterknife.ButterKnife
import butterknife.Unbinder
import dagger.android.AndroidInjection

/**
 * Created by michel.onwordi on 13/07/2017.
 */
abstract class BaseActivity : AppCompatActivity() {

    lateinit var unbinder: Unbinder
    protected lateinit var presenter: BasePresenter<out MvpView>

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        unbinder = ButterKnife.bind(getView())
        AndroidInjection.inject(this)
        initializePresenter()
    }

    override fun onDestroy() {
        super.onDestroy()
        unbinder.unbind()
    }

    abstract fun getView(): AppCompatActivity

    abstract fun initializePresenter()
}