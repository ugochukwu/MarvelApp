package com.onwordiesquire.mobile.marvelapp.presentation.characterDetails;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.onwordiesquire.mobile.marvelapp.characterlookup.domain.model.MarvelCharacter;
import com.onwordiesquire.mobile.marvelapp.data.model.CharacterData;
import com.onwordiesquire.mobile.marvelapp.characterlookup.presentation.MainActivity;
import com.onwordiesquire.mobile.marvelapp.util.TestAppComponentRule;
import com.onwordiesquire.mobile.marvelapp.util.TestDataFactory;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.mockito.stubbing.OngoingStubbing;

import rx.Observable;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Created by michelonwordi on 10/26/16.
 */
@RunWith(AndroidJUnit4.class)
public class DetailsActivityTest {



    public final TestAppComponentRule componentRule = new TestAppComponentRule(InstrumentationRegistry.getContext());

    public final ActivityTestRule<DetailsActivity> activityTestRule = new ActivityTestRule<>(DetailsActivity.class,false,false);

    @Rule
    public final TestRule chain = RuleChain.outerRule(componentRule).around(activityTestRule);

    @Test
    public void testLoadCharacterSuceeds() throws Exception
    {
        //arrange
        CharacterData data = TestDataFactory.generateFakeCharacterDataObject(1);
        // TODO: 11/22/16 URGENTLY FIX THIS
        stubGetCharacter(Observable.just(new MarvelCharacter()));
        Intent startIntent = new Intent();
        startIntent.putExtra(MainActivity.CHARACTER_DATA_ID,data.id());

        //act
        activityTestRule.launchActivity(startIntent);

        //assert
        onView(withText(data.name())).check(matches(isDisplayed()));
        onView(withText(data.description())).check(matches(isDisplayed()));
    }



    @Test
    public void testShowErrorView() throws Exception{
        //arrange
        CharacterData data = TestDataFactory.generateFakeCharacterDataObject(1);
        stubGetCharacter(Observable.error(new Exception()));
        Intent startIntent = new Intent();
        startIntent.putExtra(MainActivity.CHARACTER_DATA_ID,data.id());

        //act
        activityTestRule.launchActivity(startIntent);

        //assert
        onView(withText("An Error Occurred"))
                .check(matches(isDisplayed()));

    }

    //STUBS

    private OngoingStubbing<Observable<MarvelCharacter>> stubGetCharacter(Observable<MarvelCharacter> just) {
        return when(componentRule.getMockDataManager().getCharacterById(anyString())).thenReturn(just);
    }



}