package com.olskrain.aggregatornews.data.cache;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.olskrain.aggregatornews.Common.App;
import com.olskrain.aggregatornews.domain.entities.ItemNew;

import java.util.ArrayList;
import java.util.List;

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
    private static final String COLUMN_ID_FEED = "id_feed";
    private static final String ERROR_CACHE = "В базе нет данных";

    @Override
    public Single<List<ItemNew>> getNewsList(String urlChannel) {
        return Single.create(emitter -> {
            SQLiteDatabase connectDB = App.getInstance().getDbHelper().getWritableDatabase();
            connectDB.execSQL("PRAGMA foreign_keys=ON");

            List<ItemNew> newsList = buildNewsList(connectDB, urlChannel);
            Timber.d("rty количество новостей " +newsList.size() );
            if (newsList.isEmpty()) {
                emitter.onError(new RuntimeException(ERROR_CACHE));
            } else {
                emitter.onSuccess(newsList);
            }
            App.getInstance().getDbHelper().close();
        }).subscribeOn(Schedulers.io()).cast((Class<List<ItemNew>>) (Class) List.class);
    }

    private List<ItemNew> buildNewsList(SQLiteDatabase connectDB, String urlChannel) {
        List<ItemNew> newsList = new ArrayList<>();
        Cursor cursor;

        String selection = "id_feed = ?";
        String[] selectionArgs = new String[] { urlChannel };

        cursor = connectDB.query(TABLE_ITEM_NEWS, null, selection, selectionArgs, null, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                String title = null, pubDate = null, link = null, guid = null, author = null, thumbnail = null, description = null, content = null;

                String idFeed = null;
                do {
                    for (String cn : cursor.getColumnNames()) {
                        switch (cn) {
                            case COLUMN_ID_FEED:
                                idFeed = cursor.getString(cursor.getColumnIndex(cn));
                                break;
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
                    newsList.add(itemNews);
                } while (cursor.moveToNext());
            }
        } else {
            //TODO: обработать ситуацию
            Timber.d("rty КУРСОР НУЛ");
        }
        cursor.close();
        return newsList;
    }
}
