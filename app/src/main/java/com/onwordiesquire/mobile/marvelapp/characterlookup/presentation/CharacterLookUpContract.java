package com.onwordiesquire.mobile.marvelapp.characterlookup.presentation;

import com.onwordiesquire.mobile.marvelapp.characterlookup.domain.model.MarvelCharacter;
import com.onwordiesquire.mobile.marvelapp.characterlookup.domain.model.SearchTerm;
import com.onwordiesquire.mobile.marvelapp.presentation.MvpView;

import java.util.List;

/**
 * Created by michelonwordi on 11/18/16.
 */

public interface CharacterLookUpContract {

    interface View extends MvpView{
        void showError(String message);
        void displayPreviousSearchTerms(List<SearchTerm> searchTerm);
        void showEmptyState(String message);
        void showCharacterDetails(MarvelCharacter character);
        void showProgressIndicator(boolean value);
    }

    interface Presenter extends com.onwordiesquire.mobile.marvelapp.presentation.Presenter<CharacterLookUpContract.View>{
        void loadCharacter(String name);
        void loadPreviousSearchTerms();
    }
}
