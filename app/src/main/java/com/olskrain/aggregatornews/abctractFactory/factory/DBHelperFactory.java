package com.olskrain.aggregatornews.abctractFactory.factory;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;

import com.olskrain.aggregatornews.abctractFactory.interfaceFactory.IDBHelperFactory;
import com.olskrain.aggregatornews.data.cache.DBHelper;

/**
 * Created by Andrey Ievlev on 29,Май,2019
 */

public class DBHelperFactory implements IDBHelperFactory {

    @Override
    public DBHelper createDBHelper(@Nullable Context context, @Nullable String name, SQLiteDatabase.CursorFactory factory, int version) {
        return new DBHelper(context, name, factory, version);
    }
}
