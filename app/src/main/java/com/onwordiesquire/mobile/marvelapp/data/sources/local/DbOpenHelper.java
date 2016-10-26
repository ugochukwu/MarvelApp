package com.onwordiesquire.mobile.marvelapp.data.sources.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by michelonwordi on 10/24/16.
 */
@Singleton
public class DbOpenHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="marvel.db";
    public static final int DATABASE_VERSION=5;

    @Inject
    public DbOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.execSQL("PRAGMA foreign_keys=ON;");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.beginTransaction();
        try{
            db.execSQL(Db.ThumbnailTable.CREATE);
            db.execSQL(Db.CharacterDataTable.CREATE);
            db.execSQL(Db.RecentSearchesTable.CREATE);

            db.setTransactionSuccessful();
        }finally
        {
            db.endTransaction();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.beginTransaction();
        try{
            db.execSQL(Db.CharacterDataTable.DROP);
            db.execSQL(Db.ThumbnailTable.DROP);
            db.execSQL(Db.RecentSearchesTable.DROP);

        }finally {
            db.endTransaction();
        }
    }
}
