package com.onwordiesquire.mobile.marvelapp.characterlookup.presentation;

import com.onwordiesquire.mobile.marvelapp.characterlookup.domain.usecase.GetCharacter;
import com.onwordiesquire.mobile.marvelapp.data.CharacterDataRepository;

import javax.inject.Inject;

import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

/**
 * Created by michelonwordi on 10/24/16.
 */
public class SearchPresenter implements CharacterLookUpContract.Presenter{

    GetCharacter getCharacter;
    CompositeSubscription compositeSubscription;
    private CharacterLookUpContract.View mvpView;

    @Inject
    public SearchPresenter(GetCharacter getCharacter) {
        this.getCharacter = getCharacter;
    }

    @Override
    public void attachView(CharacterLookUpContract.View view) {
        this.mvpView = view;
        compositeSubscription = new CompositeSubscription();
    }

    @Override
    public void detachView() {
        this.mvpView = null;
        compositeSubscription.clear();
    }

    public boolean isViewAttached()
    {
        return mvpView != null;
    }

    @Override
    public void loadCharacter(String name) {
        if(isViewAttached())
        {
            compositeSubscription.add(
                    getCharacter.buildObservable(new GetCharacter.RequestValues(null,name))
                    .subscribe(responseValues -> {
                        mvpView.showProgressIndicator(false);
                        mvpView.showCharacterDetails(responseValues.getCharacter());
                    },e->{
                        if(e instanceof CharacterDataRepository.EmptyResultsException)
                        {
                            mvpView.showProgressIndicator(false);
                            mvpView.showEmptyState("An incorrect name was specified");

                        }else{
                            mvpView.showProgressIndicator(false);
                            mvpView.showError("An error has occurred");
                            Timber.e(e.getMessage(),e);
                        }
                    })
            );
        }


    }

    @Override
    public void loadPreviousSearchTerms() {

    }


//    public void loadLastSearchedCharacters() {
//
//        compositeSubscription.add(dataManager.getRecentSearches()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .flatMap(Observable::from)
//                .distinct()
//                .take(5)
//                .toList()
//                .subscribe(recentSearches -> {
//                    getMvpView().displayLastFiveSearches(recentSearches);
//                },e->{
//                    getMvpView().showError("An Error Happened");
//                    Timber.e(e.getMessage(),e);
//                })
//        );
//
//    }

//    public void loadCharacter(String name, String apikey, String timestamp, String privateKey) {
//        isViewAttached();
//        getMvpView().showProgressIndicator(true);
//
//        compositeSubscription.add(dataManager.getCharacter(name, apikey, timestamp, privateKey)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(characterData -> {
//                    getMvpView().showProgressIndicator(false);
//                    getMvpView().showCharacterDetails(characterData);
//                }, e -> {
//                    if (e instanceof CharacterDataRepositoryImpl.EmptyResultsException) {
//                        getMvpView().showProgressIndicator(false);
//                        getMvpView().showEmptyState();
//                    } else {
//                        getMvpView().showProgressIndicator(false);
//                        getMvpView().showError("An Error Happened");
//                        Timber.e(e.getMessage(),e);
//
//                    }
//                }));
//    }
}
