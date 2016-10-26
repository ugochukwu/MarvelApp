package com.onwordiesquire.mobile.marvelapp.presentation.SearchCharacter;

import com.onwordiesquire.mobile.marvelapp.data.model.CharacterData;
import com.onwordiesquire.mobile.marvelapp.data.model.RecentSearches;
import com.onwordiesquire.mobile.marvelapp.presentation.MvpView;

import java.util.List;

/**
 * Created by michelonwordi on 10/24/16.
 */

public interface SearchView extends MvpView {

    public void displayLastFiveSearches(List<RecentSearches> searches);

    void showError(String message);

    void showEmptyState();

    void showCharacterDetails(CharacterData characterData);

    void showProgressIndicator(boolean value);
}
