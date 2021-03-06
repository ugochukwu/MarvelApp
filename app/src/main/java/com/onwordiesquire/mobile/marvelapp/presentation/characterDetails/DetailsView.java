package com.onwordiesquire.mobile.marvelapp.presentation.characterDetails;

import com.onwordiesquire.mobile.marvelapp.data.model.CharacterData;
import com.onwordiesquire.mobile.marvelapp.presentation.MvpView;

/**
 * Created by michelonwordi on 10/25/16.
 */

public interface DetailsView extends MvpView {
    void displayCharacterDetails(CharacterData characterData);

    void showError(String message);
}
