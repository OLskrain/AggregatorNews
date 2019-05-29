package com.olskrain.aggregatornews.data.cache;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

/**
 * Created by Andrey Ievlev on 02,Май,2019
 */

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(@Nullable Context context, @Nullable String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(final SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE channel("
                + "id integer,"
                + "url text PRIMARY KEY,"
                + "title_feed text,"
                + "link_feed text,"
                + "author_feed text,"
                + "description_feed text,"
                + "image text,"
                + "lastbuilddate text" + ");");

        sqLiteDatabase.execSQL("CREATE TABLE itemNew("
                + "id integer,"
                + "title text,"
                + "pubDate text,"
                + "link text PRIMARY KEY,"
                + "guid text,"
                + "author text,"
                + "thumbnail text,"
                + "description text,"
                + "content text,"
                + "url_feed text NOT NULL,"
                + " FOREIGN KEY (url_feed) REFERENCES channel(url) ON DELETE CASCADE);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}
