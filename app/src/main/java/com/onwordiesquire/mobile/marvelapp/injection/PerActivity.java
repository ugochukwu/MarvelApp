package com.onwordiesquire.mobile.marvelapp.injection;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by michelonwordi on 11/22/16.
 */
@Scope
@Retention(RUNTIME)
public @interface PerActivity {
}
