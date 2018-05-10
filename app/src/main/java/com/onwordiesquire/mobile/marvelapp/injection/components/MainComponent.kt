package com.onwordiesquire.mobile.marvelapp.injection.components

import com.onwordiesquire.mobile.marvelapp.MarvelApp
import com.onwordiesquire.mobile.marvelapp.injection.modules.ApiModule
import com.onwordiesquire.mobile.marvelapp.injection.modules.AppModule
import com.onwordiesquire.mobile.marvelapp.injection.modules.BuildersModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by michelonwordi on 10/23/16.
 */
@Component(modules = arrayOf(AndroidSupportInjectionModule::class, AppModule::class, ApiModule::class, BuildersModule::class))
@Singleton
interface MainComponent {
    fun inject(app: MarvelApp)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: MarvelApp): Builder

        fun build(): MainComponent
    }
}
