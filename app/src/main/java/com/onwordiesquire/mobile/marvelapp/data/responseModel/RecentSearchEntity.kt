package com.onwordiesquire.mobile.marvelapp.data.responseModel

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by michel.onwordi on 13/06/2017.
 */
@Entity(tableName = "RecentSearch")
class RecentSearchEntity(@PrimaryKey(autoGenerate = true) val id: Int, val name: String, val characterId: String)