package com.onwordiesquire.mobile.marvelapp.characterlookup.domain.usecase;

import com.onwordiesquire.mobile.marvelapp.UseCase;
import com.onwordiesquire.mobile.marvelapp.characterlookup.domain.model.MarvelCharacter;
import com.onwordiesquire.mobile.marvelapp.data.CharacterDataRepository;


import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;

/**
 * Created by michelonwordi on 11/17/16.
 */

public class GetCharacter extends UseCase<GetCharacter.RequestValues, GetCharacter.ResponseValues> {

    private final CharacterDataRepository repository;
    private final Scheduler executorThread;
    private final Scheduler uiThread;


    public GetCharacter(CharacterDataRepository repository, @Named("uiThread") Scheduler uiThread,
                        @Named("executorThread") Scheduler executorThread
    ) {
        this.repository = repository;
        this.executorThread = executorThread;
        this.uiThread = uiThread;
    }

    @Override
    public Observable<ResponseValues> buildObservable(RequestValues requestValue) {
        return repository.getCharacterByName(requestValue.getName())
                .subscribeOn(executorThread)
                .observeOn(uiThread)
                .flatMap(character -> {
                   return Observable.just(new ResponseValues(character));
                });
    }


    public static final class ResponseValues implements UseCase.ResponseValues {

        private final MarvelCharacter character;

        public ResponseValues(MarvelCharacter character) {
            this.character = character;
        }

        public MarvelCharacter getCharacter() {
            return character;
        }
    }

    public static final class RequestValues implements UseCase.RequestValues {

        private String id;
        private String name;

        public RequestValues(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }
}
