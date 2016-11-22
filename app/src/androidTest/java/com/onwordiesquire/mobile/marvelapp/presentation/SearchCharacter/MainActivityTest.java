package com.onwordiesquire.mobile.marvelapp.presentation.SearchCharacter;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.onwordiesquire.mobile.marvelapp.R;
import com.onwordiesquire.mobile.marvelapp.characterlookup.domain.model.MarvelCharacter;
import com.onwordiesquire.mobile.marvelapp.characterlookup.presentation.MainActivity;
import com.onwordiesquire.mobile.marvelapp.data.model.CharacterData;
import com.onwordiesquire.mobile.marvelapp.data.model.CharacterDataWrapper;
import com.onwordiesquire.mobile.marvelapp.data.model.RecentSearches;
import com.onwordiesquire.mobile.marvelapp.util.TestAppComponentRule;
import com.onwordiesquire.mobile.marvelapp.util.TestDataFactory;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.mockito.stubbing.OngoingStubbing;

import java.util.List;

import rx.Observable;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.assertion.ViewAssertions.matches;

import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Created by michelonwordi on 10/26/16.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    public final TestAppComponentRule componentRule = new TestAppComponentRule(InstrumentationRegistry.getContext());

    public final ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class, false, false);

    @Rule
    public final TestRule chain = RuleChain.outerRule(componentRule).around(activityTestRule);
    private List<RecentSearches> recentSearches;

    @Before
    public void setup() {
        recentSearches = TestDataFactory.generateRecentSearchResults(4);
    }

    @Test
    public void testlistOfRecentSearchesEmpty() {
        //arrange
        String message = "No searches executed yet";
        stubGetRecentSearches(Observable.empty());

        //act
        activityTestRule.launchActivity(null);

        //assert
        onView(withText(message))
                .check(matches(isDisplayed()));

    }

    private OngoingStubbing<Observable<List<RecentSearches>>> stubGetRecentSearches(Observable value) {
        return when(componentRule.getMockDataManager().getRecentSearches()).thenReturn(value);
    }

    @Test
    public void testListOfRecentSearchesSucceed() throws Exception {
        //arrange
        stubGetRecentSearches(Observable.just(recentSearches));

        //act
        activityTestRule.launchActivity(null);

        //assert
        int position = 0;
        for (RecentSearches recentItem : recentSearches) {

            onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.scrollToPosition(position++));
            onView(withText(recentItem.name())).check(matches(isDisplayed()));

        }
    }

    @Test
    public void testGetCharacterSearchSucceed() throws Exception{
        //arrange
        CharacterDataWrapper data = TestDataFactory.generateFakeDataWrapper();
        stubGetCharacterByNameFromAPI( Observable.just(data));
        stubGetCharacterByName(Observable.just(data));
        stubGetRecentSearches(Observable.just(recentSearches));
        CharacterData characterData = data.data().results().get(0);

        //act
        activityTestRule.launchActivity(null);

        //assert
        onView(withId(R.id.search_field_edt_txt)).perform(typeText(characterData.name()));
        onView(withId(R.id.button)).perform(click());
        onView(withText(characterData.name())).check(matches(isDisplayed()));



    }

    @Test
    public void testGetCharacterSearchShowErrorView() throws Exception {
        //arrange
        String error = "An Error Happened";
        CharacterDataWrapper data = TestDataFactory.generateFakeDataWrapper();
        stubGetCharacterByNameFromAPI(Observable.error(new Exception()));
        stubGetCharacterByName(Observable.error(new Exception()));
        stubGetRecentSearches(Observable.just(recentSearches));
        CharacterData characterData = data.data().results().get(0);

        //act
        activityTestRule.launchActivity(null);

        //assert
        onView(withId(R.id.search_field_edt_txt)).perform(typeText(characterData.name()));
        onView(withId(R.id.button)).perform(click());
        onView(withText(error)).check(matches(isDisplayed()));


    }

    private OngoingStubbing<Observable<CharacterDataWrapper>> stubGetCharacterByNameFromAPI(Observable value) {
        return when(componentRule.getMockApi().getMarvelCharacter(anyString(),anyString(),anyString(),anyString()))
                .thenReturn(value);
    }

    private OngoingStubbing<Observable<MarvelCharacter>> stubGetCharacterByName(Observable value)
    {
        return when(componentRule.getMockDataManager().getCharacterByName(anyString()))
                .thenReturn(value);
    }


}