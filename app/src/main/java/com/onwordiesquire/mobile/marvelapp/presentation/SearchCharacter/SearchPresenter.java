package com.onwordiesquire.mobile.marvelapp.presentation.SearchCharacter;

import com.onwordiesquire.mobile.marvelapp.data.CharacterDataRepositoryImpl;
import com.onwordiesquire.mobile.marvelapp.presentation.BasePresenter;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

/**
 * Created by michelonwordi on 10/24/16.
 */
@Singleton
public class SearchPresenter extends BasePresenter<SearchView> {

    CharacterDataRepositoryImpl dataManager;
    CompositeSubscription compositeSubscription;

    @Inject
    public SearchPresenter(CharacterDataRepositoryImpl dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(SearchView view) {
        super.attachView(view);
        compositeSubscription = new CompositeSubscription();
    }

    @Override
    public void detachView() {
        super.detachView();
        compositeSubscription.clear();
    }


    public void loadLastSearchedCharacters() {

        compositeSubscription.add(dataManager.getRecentSearches()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(Observable::from)
                .distinct()
                .take(5)
                .toList()
                .subscribe(recentSearches -> {
                    getMvpView().displayLastFiveSearches(recentSearches);
                },e->{
                    getMvpView().showError("An Error Happened");
                    Timber.e(e.getMessage(),e);
                })
        );

    }

    public void loadCharacter(String name, String apikey, String timestamp, String privateKey) {
        isViewAttached();
        getMvpView().showProgressIndicator(true);

        compositeSubscription.add(dataManager.getCharacter(name, apikey, timestamp, privateKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(characterData -> {
                    getMvpView().showProgressIndicator(false);
                    getMvpView().showCharacterDetails(characterData);
                }, e -> {
                    if (e instanceof CharacterDataRepositoryImpl.EmptyResultsException) {
                        getMvpView().showProgressIndicator(false);
                        getMvpView().showEmptyState();
                    } else {
                        getMvpView().showProgressIndicator(false);
                        getMvpView().showError("An Error Happened");
                        Timber.e(e.getMessage(),e);

                    }
                }));
    }
}
