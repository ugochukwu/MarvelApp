package com.onwordiesquire.mobile.marvelapp.presentation.characterDetails;

import com.onwordiesquire.mobile.marvelapp.data.CharacterDataRepositoryImpl;
import com.onwordiesquire.mobile.marvelapp.data.model.CharacterData;
import com.onwordiesquire.mobile.marvelapp.util.RxSchedulersOverrideRule;
import com.onwordiesquire.mobile.marvelapp.util.TestDataFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import rx.Observable;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by michelonwordi on 10/25/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class DetailsPresenterTest {

    @Mock
    DetailsView mockDetailsView;
    @Mock
    CharacterDataRepositoryImpl mockDataManager;

    DetailsPresenter presenter;
    @Rule
    public RxSchedulersOverrideRule rxSchedulersOverrideRule = new RxSchedulersOverrideRule();
    private CharacterData characterData;

    @Before
    public void setup() {
        presenter = new DetailsPresenter(mockDataManager);
        presenter.attachView(mockDetailsView);
        characterData = TestDataFactory.generateFakeMarvelCharacter(1);

    }

    @After
    public void tearDown() {
        presenter.detachView();

    }

    @Test
    public void testLoadCharacterSucceeds() throws Exception {

        //arrange
        stubGetCharacterByIdSuceeds(characterData);

        //act
        presenter.loadCharacter(characterData.id());

        //assert
        ArgumentCaptor<CharacterData> argumentCaptor = new ArgumentCaptor<>();
        verify(mockDetailsView).displayCharacterDetails(argumentCaptor.capture());
        verify(mockDetailsView, never()).showError(anyString());
        assertEquals(argumentCaptor.getValue(), characterData);
    }

    @Test
    public void testLoadCharacterFails() throws Exception {
        //arrange
        stubGetCharacterByIdFail(characterData);
        String message = "An Error Occurred";

        //act
        presenter.loadCharacter(characterData.id());

        //assert
        ArgumentCaptor<String> argumentCaptor = new ArgumentCaptor<>();
        verify(mockDetailsView).showError(argumentCaptor.capture());
        verify(mockDetailsView, never()).displayCharacterDetails(any(CharacterData.class));
        assertEquals(argumentCaptor.getValue(), message);
    }

    private void stubGetCharacterByIdFail(CharacterData characterData) {
        when(mockDataManager.getCharacterById(characterData.id()))
                .thenReturn(Observable.error(new Exception()));
    }

    private void stubGetCharacterByIdSuceeds(CharacterData characterData) {
        when(mockDataManager.getCharacterById(characterData.id()))
                .thenReturn(Observable.just(characterData));
    }

}