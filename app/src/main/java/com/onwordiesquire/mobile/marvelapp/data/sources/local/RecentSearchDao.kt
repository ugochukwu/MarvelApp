package com.onwordiesquire.mobile.marvelapp.data.sources.local

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.onwordiesquire.mobile.marvelapp.data.responseModel.RecentSearchEntity

/**
 * Created by michel.onwordi on 31.10.17.
 */
@Dao
interface RecentSearchDao {

    @Query("Select * from RecentSearch")
    fun loadRecentSearches(): LiveData<List<RecentSearchEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecentSearch(recentSearchEntity: RecentSearchEntity)
}