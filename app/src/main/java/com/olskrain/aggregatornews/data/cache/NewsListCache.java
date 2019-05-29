package com.olskrain.aggregatornews.data.cache;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.olskrain.aggregatornews.Common.App;
import com.olskrain.aggregatornews.data.cache.interfaceCache.INewsListCache;
import com.olskrain.aggregatornews.domain.entities.ItemNew;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by Andrey Ievlev on 21,Май,2019
 */

public class NewsListCache implements INewsListCache {

    private static final String TABLE_ITEM_NEWS = "itemNew";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_PUB_DATE = "pubDate";
    private static final String COLUMN_LINK = "link";
    private static final String COLUMN_GUID = "guid";
    private static final String COLUMN_AUTHOR = "author";
    private static final String COLUMN_THUMBNAIL = "thumbnail";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_CONTENT = "content";
    private static final String COLUMN_URL_FEED = "url_feed";
    private static final String ERROR_CACHE = "В базе нет данных";

    @Override
    public Single<List<ItemNew>> getNewsList(final String urlChannel) {
        return Single.fromCallable(() -> {
            SQLiteDatabase connectDB = App.getInstance().getDbHelper().getWritableDatabase();
            connectDB.execSQL("PRAGMA foreign_keys=ON");

            List<ItemNew> newsList = buildNewsList(connectDB, urlChannel);
            Timber.d("rty количество новостей " + newsList.size());
            App.getInstance().getDbHelper().close();
            return newsList;
        }).subscribeOn(Schedulers.io());
    }

    private List<ItemNew> buildNewsList(final SQLiteDatabase connectDB, final String urlChannel) {
        List<ItemNew> newsList = new ArrayList<>();
        Stack<ItemNew> stack = new Stack<>();
        Cursor cursor;

        String selection = COLUMN_URL_FEED + " = ?";
        String[] selectionArgs = new String[]{urlChannel};

        cursor = connectDB.query(TABLE_ITEM_NEWS, null, selection, selectionArgs, null, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                String title = null, pubDate = null, link = null, guid = null, author = null, thumbnail = null, description = null, content = null;

                do {
                    for (String cn : cursor.getColumnNames()) {
                        switch (cn) {
                            case COLUMN_TITLE:
                                title = cursor.getString(cursor.getColumnIndex(cn));
                                break;
                            case COLUMN_PUB_DATE:
                                pubDate = cursor.getString(cursor.getColumnIndex(cn));
                                break;
                            case COLUMN_LINK:
                                link = cursor.getString(cursor.getColumnIndex(cn));
                                break;
                            case COLUMN_GUID:
                                guid = cursor.getString(cursor.getColumnIndex(cn));
                                break;
                            case COLUMN_AUTHOR:
                                author = cursor.getString(cursor.getColumnIndex(cn));
                                break;
                            case COLUMN_THUMBNAIL:
                                thumbnail = cursor.getString(cursor.getColumnIndex(cn));
                                break;
                            case COLUMN_DESCRIPTION:
                                description = cursor.getString(cursor.getColumnIndex(cn));
                                break;
                            case COLUMN_CONTENT:
                                content = cursor.getString(cursor.getColumnIndex(cn));
                                break;
                            default:
                                break;
                        }
                    }

                    ItemNew itemNews = new ItemNew(title, pubDate, link, guid, author, thumbnail, description, content, null, null);
                    stack.push(itemNews);
                } while (cursor.moveToNext());
            }
        } else {
            //TODO: обработать ситуацию
            Timber.d("rty КУРСОР НУЛ");
        }
        cursor.close();

        for (int i = 0; i < stack.size(); i++) {
            newsList.add(stack.pop());
        }
        return newsList;
    }
}
