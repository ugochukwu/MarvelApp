package com.onwordiesquire.mobile.marvelapp.data.sources.local;

import android.content.ContentValues;
import android.database.Cursor;

import com.onwordiesquire.mobile.marvelapp.data.model.CharacterData;
import com.onwordiesquire.mobile.marvelapp.data.model.RecentSearches;
import com.onwordiesquire.mobile.marvelapp.data.model.Thumbnail;
import com.onwordiesquire.mobile.marvelapp.data.model.Url;

/**
 * Created by michelonwordi on 10/23/16.
 */

public class Db {

    public static abstract class CharacterDataTable {
        public static final String TABLE_NAME = "character_data_table";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DESC = "description";
        public static final String COLUMN_MODIFIED = "modified";
        public static final String COLUMN_RESOURCE_URI = "resourceURI";
        public static final String COLUMN_THUMBNAIL = "thumbnail";
        private static final String _ROWID = "rowid"; //created by SQLlite automatically and therefore will not be part of the create statement

        public static final String CREATE = "CREATE TABLE " + TABLE_NAME + "( " +
                COLUMN_ID + " TEXT PRIMARY KEY ," +
                COLUMN_NAME + " TEXT, " +
                COLUMN_DESC + " TEXT, " +
                COLUMN_MODIFIED + " TEXT, " +
                COLUMN_RESOURCE_URI + " TEXT, " +
                COLUMN_THUMBNAIL + " INTEGER NOT NULL, " +
                " FOREIGN KEY(" + COLUMN_THUMBNAIL + ") REFERENCES " + ThumbnailTable.TABLE_NAME + "(" + ThumbnailTable.COLUMN_ID + ")" +
                " ); ";
        public static final String DROP = "DROP TABLE IF EXISTS " + TABLE_NAME;

        public static final String JOIN_QUERY_BY_NAME = "SELECT * FROM " + CharacterDataTable.TABLE_NAME +
                " JOIN " + ThumbnailTable.TABLE_NAME + " ON " + CharacterDataTable.TABLE_NAME + "." + COLUMN_THUMBNAIL +
                " = " + ThumbnailTable.TABLE_NAME + "." + ThumbnailTable.COLUMN_ID + " WHERE " + CharacterDataTable.COLUMN_NAME +
                " = ?";
        public static final String JOIN_QUERY_BY_ID = "SELECT * FROM " + CharacterDataTable.TABLE_NAME +
                " JOIN " + ThumbnailTable.TABLE_NAME + " ON " + CharacterDataTable.TABLE_NAME + "." + COLUMN_THUMBNAIL +
                " = " + ThumbnailTable.TABLE_NAME + "." + ThumbnailTable.COLUMN_ID + " WHERE " + CharacterDataTable.TABLE_NAME + "." + CharacterDataTable.COLUMN_ID +
                " = ?";
        public static final String JOIN_QUERY = "SELECT * FROM " + CharacterDataTable.TABLE_NAME +
                " JOIN " + ThumbnailTable.TABLE_NAME + " ON " + CharacterDataTable.TABLE_NAME + "." + COLUMN_THUMBNAIL +
                " = " + ThumbnailTable.TABLE_NAME + "." + ThumbnailTable.COLUMN_ID + " ORDER BY " + CharacterDataTable.TABLE_NAME + "." + CharacterDataTable._ROWID +
                " DESC ";

        public static ContentValues toContentValues(CharacterData characterData) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID, characterData.id());
            values.put(COLUMN_DESC, characterData.description());
            values.put(COLUMN_MODIFIED, characterData.modified());
            values.put(COLUMN_NAME, characterData.name());
            values.put(COLUMN_RESOURCE_URI, characterData.resourceURI());
            values.put(COLUMN_THUMBNAIL, characterData.thumbnail() != null ? characterData.thumbnail().getId() : "");
            return values;
        }

        public static CharacterData fromJoinedCursorResult(Cursor cursor) {
            //first parse thumbnail
            String path = cursor.getString(cursor.getColumnIndex(ThumbnailTable.COLUMN_PATH));
            String ext = cursor.getString(cursor.getColumnIndex(ThumbnailTable.COLUMN_EXT));
            Thumbnail thumbnail = Thumbnail.builder().path(path)
                    .extension(ext)
                    .build();

            //parse character data
            return CharacterData.builder()
                    .id(cursor.getString(cursor.getColumnIndex(COLUMN_ID)))
                    .description(cursor.getString(cursor.getColumnIndex(COLUMN_DESC)))
                    .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                    .modified(cursor.getString(cursor.getColumnIndex(COLUMN_MODIFIED)))
                    .resourceURI(cursor.getString(cursor.getColumnIndex(COLUMN_RESOURCE_URI)))
                    .thumbnail(thumbnail)
                    .build();
        }

    }

    public static abstract class ThumbnailTable {
        public static final String TABLE_NAME = "thumbnail_table";
        public static final String COLUMN_PATH = "path";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_EXT = "extension";
        public static final String CREATE = "CREATE TABLE " + TABLE_NAME + "( " +
                COLUMN_PATH + " TEXT, " +
                COLUMN_EXT + " TEXT, " +
                COLUMN_ID + " TEXT PRIMARY KEY " +
                " ); ";

        public static final String DROP = "DROP TABLE IF EXISTS " + TABLE_NAME;

        public static ContentValues toContentValues(Thumbnail thumbnail) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID, thumbnail.getId());
            values.put(COLUMN_PATH, thumbnail.path());
            values.put(COLUMN_EXT, thumbnail.extension());
            return values;
        }
    }

    public static abstract class RecentSearchesTable {
        public static final String TABLE_NAME = "recent_searches_table";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_CHARACTER_ID = "character_id";
        private static final String _ROWID = "rowid"; //created by SQLlite automatically and therefore will not be part of the create statement

        public static final String CREATE = "CREATE TABLE " + TABLE_NAME + " ( " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_CHARACTER_ID + " TEXT, " +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT " +
                " );";
        public static final String DROP = "DROP TABLE IF EXISTS " + TABLE_NAME;
        public static final String QUERY_ALL="SELECT * FROM "+TABLE_NAME+ " ORDER BY "
                + _ROWID+" DESC ";

        public static ContentValues toContentValues(RecentSearches searches) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_CHARACTER_ID, searches.characterId());
            contentValues.put(COLUMN_NAME, searches.name());
            return contentValues;

        }


        public static RecentSearches fromCursor(Cursor cursor) {
            return RecentSearches.builder()
                    .characterId(cursor.getString(cursor.getColumnIndex(COLUMN_CHARACTER_ID)))
                    .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                    .build();
        }


    }
}
