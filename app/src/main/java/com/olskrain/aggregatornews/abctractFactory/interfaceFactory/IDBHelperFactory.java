package com.olskrain.aggregatornews.abctractFactory.interfaceFactory;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;

import com.olskrain.aggregatornews.data.cache.DBHelper;

/**
 * Created by Andrey Ievlev on 29,Май,2019
 */

public interface IDBHelperFactory {
    DBHelper createDBHelper(@Nullable Context context, @Nullable String name, SQLiteDatabase.CursorFactory factory, int version);
}
