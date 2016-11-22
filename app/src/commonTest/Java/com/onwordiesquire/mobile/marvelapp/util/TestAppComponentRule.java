package com.onwordiesquire.mobile.marvelapp.util;

import android.content.Context;

import com.onwordiesquire.mobile.marvelapp.MarvelApp;
import com.onwordiesquire.mobile.marvelapp.data.CharacterDataRepository;
import com.onwordiesquire.mobile.marvelapp.data.CharacterDataRepositoryImpl;
import com.onwordiesquire.mobile.marvelapp.data.sources.local.DatabaseDataSource;
import com.onwordiesquire.mobile.marvelapp.data.sources.remote.MarvelApiService;
import com.onwordiesquire.mobile.marvelapp.util.injection.component.TestAppComponent;
import com.onwordiesquire.mobile.marvelapp.util.injection.component.DaggerTestAppComponent;
import com.onwordiesquire.mobile.marvelapp.util.injection.module.AppTestModule;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * Created by michelonwordi on 10/26/16.
 */

public class TestAppComponentRule implements TestRule {

    private final Context context;
    private final TestAppComponent testAppComponent;

    public TestAppComponentRule(Context context) {
        this.context = context;
        testAppComponent = DaggerTestAppComponent.builder()
                .appTestModule(new AppTestModule(MarvelApp.get())).build();
    }

    public Context getContext() {
        return context;
    }

    public CharacterDataRepository getMockDataManager() {
        return testAppComponent.dataManager();
    }

    public DatabaseDataSource getMockDatabaseSource() {
        return testAppComponent.databaseDataSource();
    }

    public MarvelApiService getMockApi() {
        return testAppComponent.marvelApiService();
    }

    @Override
    public Statement apply(final Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                MarvelApp application = MarvelApp.get();
                application.setComponent(testAppComponent);
                base.evaluate();
                application.setComponent(null);
            }
        };
    }
}
