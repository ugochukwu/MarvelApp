package com.onwordiesquire.mobile.marvelapp.presentation.characterSearch;

import com.onwordiesquire.mobile.marvelapp.BuildConfig;
import com.onwordiesquire.mobile.marvelapp.data.DataManager;
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
    DataManager mockDataManager;
    @Mock
    SearchView mockSearchView;

    SearchPresenter presenter;
    private List<RecentSearches> recentSearchesList = null;


    @Rule
    public RxSchedulersOverrideRule rule = new RxSchedulersOverrideRule();

    @Before
    public void setup() {
        presenter = new SearchPresenter(mockDataManager);
        presenter.attachView(mockSearchView);
        recentSearchesList = TestDataFactory.generateRecentSearchResults(5);

    }

    @Test
    public void testLoadLastSearchedResultsSucceed() throws Exception {
        //arrange
        stubGetRecentSearchesSucceed(recentSearchesList);

        //act
        presenter.loadLastSearchedCharacters();

        //assert
        verify(mockSearchView).displayLastFiveSearches(recentSearchesList);
        verify(mockSearchView, never()).showError(anyString());
    }


    @Test
    public void testLoadLastSearchedResultsCallsError() throws Exception {

        //arrange
        stubGetRecentSearchesFail();

        //act
        presenter.loadLastSearchedCharacters();

        //arrange
        verify(mockSearchView).showError(anyString());

    }

    @Test
    public void testLoadCharacterSuceeds() throws Exception {
        //arrange
        CharacterData characterData = TestDataFactory.generateFakeMarvelCharacter(1);
        stubGetCharacterSuceeds(characterData);

        //act
        presenter.loadCharacter(characterData.name(), BuildConfig.PUBLIC_API_KEY_MARVEL, "1", BuildConfig.PRIVATE_API_KEY_MARVEL);

        //assert
        verify(mockSearchView).showProgressIndicator(false);
        ArgumentCaptor<CharacterData> argumentCaptor = new ArgumentCaptor<>();
        verify(mockSearchView).showCharacterDetails(argumentCaptor.capture());
        assertEquals(argumentCaptor.getValue(),characterData);

    }

    @Test
    public void testLoadCharacterDoesNotShowErrorIfEmpty() throws Exception{
        //arrange
        stubGetCharacterReturnEmpty();

        //act
        presenter.loadCharacter("1", BuildConfig.PUBLIC_API_KEY_MARVEL, "1", BuildConfig.PRIVATE_API_KEY_MARVEL);

        //assert
        verify(mockSearchView).showProgressIndicator(false);
        verify(mockSearchView).showEmptyState();
        verify(mockSearchView,never()).showError("");
    }

    @Test
    public void testGetCharacterEmitsMessageOnError() throws Exception{
        //arrange
        String message = "An Error Happened";
        stubGetCharacterFails(message);

        //act
        presenter.loadCharacter("1", BuildConfig.PUBLIC_API_KEY_MARVEL, "1", BuildConfig.PRIVATE_API_KEY_MARVEL);

        //assert
        ArgumentCaptor<String> argumentCaptor = new ArgumentCaptor<>();
        verify(mockSearchView).showProgressIndicator(false);
        verify(mockSearchView).showError(argumentCaptor.capture());
        verify(mockSearchView,never()).showEmptyState();
        assertEquals(argumentCaptor.getValue(),message);
    }

    private void stubGetCharacterFails(String message) {
        when(mockDataManager.getCharacter(anyString(),anyString(),anyString(),anyString()))
                .thenReturn(Observable.error(new Exception(message)));
    }


    //STUBS//



    private void stubGetCharacterReturnEmpty()
    {
        when(mockDataManager.getCharacter(anyString(),anyString(),anyString(),anyString()))
                .thenReturn(Observable.error(new DataManager.EmptyResultsException("")));

    }

    private void stubGetCharacterSuceeds(CharacterData characterData) {
        when(mockDataManager.getCharacter(anyString(), anyString(), anyString(), anyString()))
                .thenReturn(Observable.just(characterData));
    }


    private void stubGetRecentSearchesSucceed(List<RecentSearches> recentSearchesList) {
        when(mockDataManager.getRecentSearches()).thenReturn(Observable.just(recentSearchesList));
    }

    private void stubGetRecentSearchesFail() {
        when(mockDataManager.getRecentSearches()).thenReturn(Observable.error(new Exception()));

    }

}