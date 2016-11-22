package com.onwordiesquire.mobile.marvelapp.presentation.SearchCharacter;

import com.onwordiesquire.mobile.marvelapp.BuildConfig;
import com.onwordiesquire.mobile.marvelapp.characterlookup.domain.model.MarvelCharacter;
import com.onwordiesquire.mobile.marvelapp.characterlookup.domain.usecase.GetCharacter;
import com.onwordiesquire.mobile.marvelapp.characterlookup.presentation.CharacterLookUpContract;
import com.onwordiesquire.mobile.marvelapp.characterlookup.presentation.SearchPresenter;
import com.onwordiesquire.mobile.marvelapp.data.CharacterDataRepository;
import com.onwordiesquire.mobile.marvelapp.data.CharacterDataRepositoryImpl;
import com.onwordiesquire.mobile.marvelapp.data.model.CharacterData;
import com.onwordiesquire.mobile.marvelapp.data.model.RecentSearches;
import com.onwordiesquire.mobile.marvelapp.util.RxSchedulersOverrideRule;
import com.onwordiesquire.mobile.marvelapp.util.TestDataFactory;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import rx.Observable;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by michelonwordi on 10/25/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class SearchPresenterTest {
    @Mock
    CharacterDataRepository mockDataManager;
    @Mock
    CharacterLookUpContract.View mockSearchView;
    @Mock
    GetCharacter mockGetCharacterUseCase;

    SearchPresenter presenter;
    private List<RecentSearches> recentSearchesList = null;


    @Rule
    public RxSchedulersOverrideRule rule = new RxSchedulersOverrideRule();

    @Before
    public void setup() {
        presenter = new SearchPresenter(mockGetCharacterUseCase);
        presenter.attachView(mockSearchView);
        recentSearchesList = TestDataFactory.generateRecentSearchResults(5);

    }

    @Test
    public void testLoadLastSearchedResultsSucceed() throws Exception {
        //arrange
        stubGetRecentSearchesSucceed(recentSearchesList);
// TODO: 11/22/16 re-implement this test when working on recent searches feature
        //act
//        presenter.loadLastSearchedCharacters();

        //assert
//        verify(mockSearchView).displayLastFiveSearches(recentSearchesList);
//        verify(mockSearchView, never()).showError(anyString());
        fail();
    }


    @Test
    public void testLoadLastSearchedResultsCallsError() throws Exception {

        //arrange
        stubGetRecentSearchesFail();
// TODO: 11/22/16 re-implement this test when working on recent searches feature

        //act
//        presenter.loadLastSearchedCharacters();

        //arrange
//        verify(mockSearchView).showError(anyString());
        fail();

    }

    @Test
    public void testLoadCharacterSuceeds() throws Exception {
        //arrange
        MarvelCharacter character = TestDataFactory.generateFakeMarvelCharacter(1);
        stubGetCharacterSuceeds(character);

        //act
        presenter.loadCharacter(character.getName());

        //assert
        verify(mockSearchView).showProgressIndicator(false);
        ArgumentCaptor<MarvelCharacter> argumentCaptor = new ArgumentCaptor<>();
        verify(mockSearchView).showCharacterDetails(argumentCaptor.capture());
        assertEquals(argumentCaptor.getValue(), character);

    }

    @Test
    public void testLoadCharacterDoesNotShowErrorIfEmpty() throws Exception {
        //arrange
        stubGetCharacterReturnEmpty();

        //act
        presenter.loadCharacter(anyString());

        //assert
        verify(mockSearchView).showProgressIndicator(false);
        verify(mockSearchView).showEmptyState("An incorrect name was specified");
        verify(mockSearchView, never()).showError("");
    }

    @Test
    public void testGetCharacterEmitsMessageOnError() throws Exception {
        //arrange
        String message = "An error has occurred";
        stubGetCharacterFails(message);

        //act
        presenter.loadCharacter(anyString());

        //assert
        ArgumentCaptor<String> argumentCaptor = new ArgumentCaptor<>();
        verify(mockSearchView).showProgressIndicator(false);
        verify(mockSearchView).showError(argumentCaptor.capture());
        verify(mockSearchView, never()).showEmptyState(anyString());
        assertEquals(argumentCaptor.getValue(), message);
    }

    private void stubGetCharacterFails(String message) {
        when(mockGetCharacterUseCase.buildObservable(any()))
                .thenReturn(Observable.error(new Exception(message)));
    }


    //STUBS//


    private void stubGetCharacterReturnEmpty() {
        when(mockGetCharacterUseCase.buildObservable(any()))
                .thenReturn(Observable.error(new CharacterDataRepository.EmptyResultsException("An error has occurred")));

    }

    private void stubGetCharacterSuceeds(MarvelCharacter character) {
        when(mockGetCharacterUseCase.buildObservable(any()))
                .thenReturn(Observable.just(new GetCharacter.ResponseValues(character)));
    }


    private void stubGetRecentSearchesSucceed(List<RecentSearches> recentSearchesList) {
        when(mockDataManager.getRecentSearches()).thenReturn(Observable.just(recentSearchesList));
    }

    private void stubGetRecentSearchesFail() {
        when(mockDataManager.getRecentSearches()).thenReturn(Observable.error(new Exception()));

    }

}