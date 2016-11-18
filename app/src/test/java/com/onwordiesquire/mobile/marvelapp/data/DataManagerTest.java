package com.onwordiesquire.mobile.marvelapp.data;

import com.onwordiesquire.mobile.marvelapp.BuildConfig;
import com.onwordiesquire.mobile.marvelapp.data.model.CharacterData;
import com.onwordiesquire.mobile.marvelapp.data.model.CharacterDataWrapper;
import com.onwordiesquire.mobile.marvelapp.data.sources.local.DatabaseDataSource;
import com.onwordiesquire.mobile.marvelapp.data.sources.remote.MarvelApiService;
import com.onwordiesquire.mobile.marvelapp.util.TestDataFactory;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import rx.Observable;
import rx.observers.TestSubscriber;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by michelonwordi on 10/24/16.
 */
@RunWith(MockitoJUnitRunner.class)
//@PrepareForTest(MarvelApiService.HELPER.class)
public class DataManagerTest {

    @Mock
    MarvelApiService mockApiService;
    @Mock
    DatabaseDataSource mockDBSource;
    CharacterDataRepositoryImpl dataManager;
    private CharacterDataWrapper testDataWrapper;

    @Before
    public void setup() {
        dataManager = new CharacterDataRepositoryImpl(mockApiService, mockDBSource);
        testDataWrapper = TestDataFactory.generateFakeDataWrapper();

    }


    @Test
    public void testGetCharacterSuceeds() throws Exception {

        //arrange
        when(mockApiService.
                getMarvelCharacter(anyString(), anyString(), anyString(), anyString()))
                .thenReturn(Observable.just(testDataWrapper));
        CharacterData characterData = testDataWrapper.data().results().get(0);
        when(mockDBSource.getCharacter(anyString())).thenReturn(Observable.just(characterData));

        TestSubscriber<CharacterData> testSubscriber = new TestSubscriber<>();

        //act
        dataManager.getCharacter("string", BuildConfig.PUBLIC_API_KEY_MARVEL, "1", BuildConfig.PRIVATE_API_KEY_MARVEL).take(1)
                .subscribe(testSubscriber);

        //assert
        testSubscriber.assertNoErrors();
        testSubscriber.assertCompleted();
        testSubscriber.assertValue(characterData);

    }

    @Test
    public void testGetCharacterEmitsException() throws Exception{
        //arrange
        CharacterDataRepositoryImpl.EmptyResultsException exception = new CharacterDataRepositoryImpl.EmptyResultsException("No Results");
        when(mockApiService.getMarvelCharacter(anyString(),anyString(),anyString(),anyString()))
                .thenReturn(Observable.error(exception));
        TestSubscriber<CharacterData> testSubscriber = new TestSubscriber<>();
        when(mockDBSource.getCharacter(anyString())).thenReturn(Observable.error(exception));


        //act
        dataManager.getCharacter("string", BuildConfig.PUBLIC_API_KEY_MARVEL, "1", BuildConfig.PRIVATE_API_KEY_MARVEL).take(1)
                .subscribe(testSubscriber);

        //assert
        testSubscriber.assertError(CharacterDataRepositoryImpl.EmptyResultsException.class);
    }

}