package com.onwordiesquire.mobile.marvelapp.data.sources.local;

import android.database.sqlite.SQLiteDatabase;

import com.onwordiesquire.mobile.marvelapp.data.model.CharacterData;
import com.onwordiesquire.mobile.marvelapp.data.model.RecentSearches;
import com.onwordiesquire.mobile.marvelapp.data.model.Thumbnail;
import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by michelonwordi on 10/24/16.
 */
@Singleton
public class DatabaseDataSource {

    private final BriteDatabase dB;

    @Inject
    public DatabaseDataSource(DbOpenHelper dbOpenHelper) {
        dB = SqlBrite.create().wrapDatabaseHelper(dbOpenHelper, Schedulers.io());
    }


    public void insertCharacter(CharacterData data) {
        BriteDatabase.Transaction transaction = dB.newTransaction();
        try {

            Thumbnail thumbnail = data.thumbnail();
            thumbnail.setId(data.id());
            dB.insert(Db.ThumbnailTable.TABLE_NAME, Db.ThumbnailTable.toContentValues(thumbnail), SQLiteDatabase.CONFLICT_REPLACE);
            dB.insert(Db.CharacterDataTable.TABLE_NAME, Db.CharacterDataTable.toContentValues(data), SQLiteDatabase.CONFLICT_REPLACE);

            transaction.markSuccessful();
        } finally {
            transaction.end();
        }

    }

    public Observable<CharacterData> getCharacter(String name) {

        return dB.createQuery(Db.CharacterDataTable.TABLE_NAME, Db.CharacterDataTable.JOIN_QUERY_BY_NAME, name)
                .mapToOneOrDefault(Db.CharacterDataTable::fromJoinedCursorResult, null).take(1);
    }

    public Observable<CharacterData> getCharacterById(String characterId) {
        return dB.createQuery(Db.CharacterDataTable.TABLE_NAME, Db.CharacterDataTable.JOIN_QUERY_BY_ID, characterId)
                .mapToOne(Db.CharacterDataTable::fromJoinedCursorResult).take(1);
    }

    public Observable<List<CharacterData>> getAllCharacters() {
        return dB.createQuery(Db.CharacterDataTable.TABLE_NAME, Db.CharacterDataTable.JOIN_QUERY)
                .mapToList(Db.CharacterDataTable::fromJoinedCursorResult).take(1);
    }

    public Observable<List<RecentSearches>> getRecentSearches() {
        return dB.createQuery(Db.RecentSearchesTable.TABLE_NAME, Db.RecentSearchesTable.QUERY_ALL)
                .mapToList(Db.RecentSearchesTable::fromCursor).take(1);
    }

    public void insertRecentSearches(RecentSearches rc) {
        BriteDatabase.Transaction transaction = dB.newTransaction();
        try {
            dB.insert(Db.RecentSearchesTable.TABLE_NAME, Db.RecentSearchesTable.toContentValues(rc));
            transaction.markSuccessful();
        } finally {
            transaction.end();
        }
    }
}
