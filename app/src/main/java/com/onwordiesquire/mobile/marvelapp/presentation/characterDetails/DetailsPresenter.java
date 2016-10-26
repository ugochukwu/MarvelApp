package com.onwordiesquire.mobile.marvelapp.presentation.characterDetails;

import com.onwordiesquire.mobile.marvelapp.data.DataManager;
import com.onwordiesquire.mobile.marvelapp.presentation.BasePresenter;
import com.onwordiesquire.mobile.marvelapp.presentation.MvpView;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

/**
 * Created by michelonwordi on 10/25/16.
 */
@Singleton
public class DetailsPresenter extends BasePresenter<DetailsView> {

    private final DataManager dataManager;
    private CompositeSubscription compositeSubscription;

    @Inject
    public DetailsPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(DetailsView view) {
        super.attachView(view);
        compositeSubscription = new CompositeSubscription();
    }

    @Override
    public void detachView() {
        super.detachView();
        compositeSubscription.clear();
    }

    public void loadCharacter(String characterId)
    {
        isViewAttached();
        dataManager.getCharacterById(characterId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(characterData -> {
                   getMvpView().displayCharacterDetails(characterData);

                },e->{
                    getMvpView().showError("An Error Occurred");
                    Timber.e(e.getMessage(),e);
                });
    }
}
