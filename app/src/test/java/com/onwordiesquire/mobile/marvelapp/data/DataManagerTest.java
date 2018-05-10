package com.onwordiesquire.mobile.marvelapp.data;

import com.onwordiesquire.mobile.marvelapp.BuildConfig;
import com.onwordiesquire.mobile.marvelapp.data.sources.remote.MarvelApi;

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
    MarvelApi mockApiService;
    @Mock
//    DatabaseDataSource mockDBSource;
    MarvelDataManager dataManager;
    // TODO: 07.10.17 fix this
    //    private CharacterDataWrapper testDataWrapper;

    @Before
    public void setup() {
//        dataManager = new DataManager(mockApiService, mockDBSource);
//        testDataWrapper = TestDataFactory.INSTANCE.generateFakeDataWrapper();

    }


    @Test
    public void testGetCharacterSuceeds() throws Exception {
// TODO: 07.10.17 fix this test
        //arrange
//        when(mockApiService.
//                getMarvelCharacter(anyString(), anyString(), anyString(), anyString()))
//                .thenReturn(Observable.just(testDataWrapper));
//        CharacterData characterData = testDataWrapper.data().results().get(0);
//        when(mockDBSource.getCharacter(anyString())).thenReturn(Observable.just(characterData));
//
//        TestSubscriber<CharacterData> testSubscriber = new TestSubscriber<>();

        //act
//        dataManager.getCharacter("string", BuildConfig.PUBLIC_API_KEY_MARVEL, "1", BuildConfig.PRIVATE_API_KEY_MARVEL).take(1)
//                .subscribe(testSubscriber);

        //assert
//        testSubscriber.assertNoErrors();
//        testSubscriber.assertCompleted();
//        testSubscriber.assertValue(characterData);

    }

    @Test
    public void testGetCharacterEmitsException() throws Exception{
// TODO: 07.10.17 fix this test
        //arrange
//        DataManager.EmptyResultsException exception = new DataManager.EmptyResultsException("No Results");
//        when(mockApiService.getMarvelCharacter(anyString(),anyString(),anyString(),anyString()))
//                .thenReturn(Observable.error(exception));
//        TestSubscriber<CharacterData> testSubscriber = new TestSubscriber<>();
//        when(mockDBSource.getCharacter(anyString())).thenReturn(Observable.error(exception));


        //act
//        dataManager.getCharacter("string", BuildConfig.PUBLIC_API_KEY_MARVEL, "1", BuildConfig.PRIVATE_API_KEY_MARVEL).take(1)
//                .subscribe(testSubscriber);

        //assert
//        testSubscriber.assertError(DataManager.EmptyResultsException.class);
    }

}