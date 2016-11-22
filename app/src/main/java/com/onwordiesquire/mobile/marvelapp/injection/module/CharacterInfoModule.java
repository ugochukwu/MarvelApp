package com.onwordiesquire.mobile.marvelapp.injection.module;

import com.onwordiesquire.mobile.marvelapp.characterlookup.domain.usecase.GetCharacter;
import com.onwordiesquire.mobile.marvelapp.data.CharacterDataRepository;
import com.onwordiesquire.mobile.marvelapp.injection.PerActivity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;

/**
 * Created by michelonwordi on 11/19/16.
 */
@Module
public class CharacterInfoModule {

    @PerActivity
    @Provides
    public GetCharacter providesGetCharacterUseCase(CharacterDataRepository repository, @Named("uiThread") Scheduler uiThread
            , @Named("executorThread") Scheduler executorThread) {
        return new GetCharacter(repository, uiThread, executorThread);
    }
}
