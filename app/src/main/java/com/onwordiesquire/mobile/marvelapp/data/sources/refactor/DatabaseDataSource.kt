package com.onwordiesquire.mobile.marvelapp.data.sources.refactor

import com.onwordiesquire.mobile.marvelapp.data.sources.local.DbOpenHelper
import com.squareup.sqlbrite.BriteDatabase
import com.squareup.sqlbrite.SqlBrite
import rx.schedulers.Schedulers
import javax.inject.Singleton

/**
 * Created by michel.onwordi on 09/07/2017.
 */
@Singleton
class DatabaseDataSource(dbOpenHelper: DbOpenHelper) {
    val dB: BriteDatabase = SqlBrite.create().wrapDatabaseHelper(dbOpenHelper, Schedulers.io())

}