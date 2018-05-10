package com.onwordiesquire.mobile.marvelapp.data.sources.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.onwordiesquire.mobile.marvelapp.data.responseModel.RecentSearchEntity

/**
 * Created by michel.onwordi on 31.10.17.
 */
@Database(entities = arrayOf(RecentSearchEntity::class), version = 1)
abstract class MarvelAppDatabase : RoomDatabase() {

    abstract fun recentSearchDao(): RecentSearchDao
}