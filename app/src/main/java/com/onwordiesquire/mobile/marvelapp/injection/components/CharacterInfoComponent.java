package com.onwordiesquire.mobile.marvelapp.injection.components;

import com.onwordiesquire.mobile.marvelapp.injection.PerActivity;
import com.onwordiesquire.mobile.marvelapp.injection.module.ActivityModule;
import com.onwordiesquire.mobile.marvelapp.injection.module.CharacterInfoModule;
import com.onwordiesquire.mobile.marvelapp.characterlookup.presentation.MainActivity;

import dagger.Component;

/**
 * Created by michelonwordi on 11/19/16.
 */

@PerActivity
@Component(dependencies = AppComponent.class, modules = {CharacterInfoModule.class, ActivityModule.class})
public interface CharacterInfoComponent extends ActivityComponent{
    void inject(MainActivity activity);

}
