package com.onwordiesquire.mobile.marvelapp.util;

import android.content.Context;

import com.onwordiesquire.mobile.marvelapp.util.injection.component.TestAppComponent;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * Created by michelonwordi on 10/26/16.
 */

public class TestAppComponentRule implements TestRule {

    private final Context context;
    private final TestAppComponent testAppComponent = null;

    // TODO: 10/07/2017 Fix later
    public TestAppComponentRule(Context context) {
        this.context = context;
//        testAppComponent = DaggerTestAppComponent.builder()
//            .appTestModule(new AppTestModule(MarvelApp.app)).build();
    }

    public Context getContext() {
        return context;
    }

//    public DataManager getMockDataManager() {
//        return testAppComponent.dataManager();
//    }

//    public DatabaseDataSource getMockDatabaseSource() {
//        return testAppComponent.databaseDataSource();
//    }

//    public MarvelApiService getMockApi() {
//        return testAppComponent.marvelApiService();
//    }

    @Override
    public Statement apply(final Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
//                MarvelApp application = MarvelApp.get();
//                application.setComponent(testAppComponent);
                base.evaluate();
//                application.setComponent(null);
            }
        };
    }
}
