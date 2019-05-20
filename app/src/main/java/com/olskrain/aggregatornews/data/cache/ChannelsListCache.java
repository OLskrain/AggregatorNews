package com.olskrain.aggregatornews.data.cache;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.olskrain.aggregatornews.Common.App;
import com.olskrain.aggregatornews.Common.Command;
import com.olskrain.aggregatornews.domain.entities.Channel;
import com.olskrain.aggregatornews.domain.entities.Feed;
import com.olskrain.aggregatornews.domain.entities.ItemNew;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by Andrey Ievlev on 27,Апрель,2019
 */

public class ChannelsListCache implements IChannelsListCache {

    private static final String TABLE_FEED = "channel";
    private static final String TABLE_ITEM_NEWS = "itemNew";
    private static final String COLUMN_URL = "url";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_TITLE_FEED = "title_feed";
    private static final String COLUMN_LINK = "link";
    private static final String COLUMN_LINK_FEED = "link_feed";
    private static final String COLUMN_AUTHOR = "author";
    private static final String COLUMN_AUTHOR_FEED = "author_feed";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_DESCRIPTION_FEED = "description_feed";
    private static final String COLUMN_IMAGE = "image";
    private static final String COLUMN_LAST_BUILD = "lastbuilddate";
    private static final String COLUMN_PUB_DATE = "pubDate";
    private static final String COLUMN_GUID = "guid";
    private static final String COLUMN_THUMBNAIL = "thumbnail";
    private static final String COLUMN_CONTENT = "content";
    private static final String COLUMN_ID_FEED = "id_feed";
    private static final String STATUS_DB_UPDATE = "Список каналов обновлен";
    private static final String STATUS_DB_TRANSACTION_FAILED = "Транзакция не прошла";
    private static final String ERROR_CACHE = "В базе нет данных";

    @Override
    public void updateDatabase(Command command, List<Channel> channelsList) {
        SQLiteDatabase connectDB = App.getInstance().getDbHelper().getWritableDatabase();
        connectDB.execSQL("PRAGMA foreign_keys=ON");

        switch (command) {
            case ADD_CHANNEL:
                addChannel(connectDB, channelsList);
                break;
            case DELETE_CHANNEL:
                deleteChannel(connectDB, channelsList.get(0).getFeed().getUrl());
                break;
            case DELETE_ALL_CHANNELS:
                deleteAllChannel(connectDB);
                break;
            case REFRESH_CHANNELS:
                refreshChannel(connectDB, channelsList);
                break;
            default:
                break;
        }
        App.getInstance().getDbHelper().close();
    }

    @Override
    public Single<List<Feed>> getChannelsList() {
        return Single.create(emitter -> {
            SQLiteDatabase connectDB = App.getInstance().getDbHelper().getWritableDatabase();
            connectDB.execSQL("PRAGMA foreign_keys=ON");

            List<Feed> channelsList = buildChannelsList(connectDB);
            if (channelsList.isEmpty()) {
                emitter.onError(new RuntimeException(ERROR_CACHE));
            } else {
                emitter.onSuccess(channelsList);
            }

            App.getInstance().getDbHelper().close();
        }).subscribeOn(Schedulers.io()).cast((Class<List<Feed>>) (Class) List.class);
    }

    private void addChannel(SQLiteDatabase connectDB, List<Channel> channelsList) {
        for (int i = 0; i < channelsList.size(); i++) {
            String feedUrl = channelsList.get(i).getFeed().getUrl();
            String feedTitle = channelsList.get(i).getFeed().getTitle();
            String feedLink = channelsList.get(i).getFeed().getLink();
            String feedAuthor = channelsList.get(i).getFeed().getAuthor();
            String feedDescription = channelsList.get(i).getFeed().getDescription();
            String feedImage = channelsList.get(i).getFeed().getImage();
            String feedLastBuildDate = channelsList.get(i).getFeed().getLastBuildDate();
            List<ItemNew> itemNewsList = channelsList.get(i).getItemNew();

            int idFeed = (int) insertFeed(connectDB, TABLE_FEED, feedUrl, feedTitle, feedLink, feedAuthor, feedDescription, feedImage, feedLastBuildDate);

            if (itemNewsList != null) {
                for (int j = 0; j < itemNewsList.size(); j++) {
                    String itemNewTitle = itemNewsList.get(j).getTitle();
                    String itemNewPubDate = itemNewsList.get(j).getPubDate();
                    String itemNewLink = itemNewsList.get(j).getLink();
                    String itemNewGuid = itemNewsList.get(j).getGuid();
                    String itemNewAuthor = itemNewsList.get(j).getAuthor();
                    String itemNewThumbnail = itemNewsList.get(j).getThumbnail();
                    String itemNewDescription = itemNewsList.get(j).getDescription();
                    String itemNewContent = itemNewsList.get(j).getContent();

                    insertItemNew(connectDB, TABLE_ITEM_NEWS, idFeed, itemNewTitle, itemNewPubDate, itemNewLink, itemNewGuid, itemNewAuthor, itemNewThumbnail, itemNewDescription, itemNewContent);
                }
            }
        }
    }

    private long insertFeed(SQLiteDatabase connectDB, String table, String url, String title, String link,
                            String author, String description, String image, String lastBuildDate) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_URL, url);
        contentValues.put(COLUMN_TITLE_FEED, title);
        contentValues.put(COLUMN_LINK_FEED, link);
        contentValues.put(COLUMN_AUTHOR_FEED, author);
        contentValues.put(COLUMN_DESCRIPTION_FEED, description);
        contentValues.put(COLUMN_IMAGE, image);
        contentValues.put(COLUMN_LAST_BUILD, lastBuildDate);
        return connectDB.insert(table, null, contentValues);
    }

    private long insertItemNew(SQLiteDatabase connectDB, String table, int idFeed, String title, String pubDate, String link,
                               String guid, String author, String thumbnail, String description, String content) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TITLE, title);
        contentValues.put(COLUMN_PUB_DATE, pubDate);
        contentValues.put(COLUMN_LINK, link);
        contentValues.put(COLUMN_GUID, guid);
        contentValues.put(COLUMN_AUTHOR, author);
        contentValues.put(COLUMN_THUMBNAIL, thumbnail);
        contentValues.put(COLUMN_DESCRIPTION, description);
        contentValues.put(COLUMN_CONTENT, content);
        contentValues.put(COLUMN_ID_FEED, idFeed);
        return connectDB.insert(table, null, contentValues);
    }

    private void deleteChannel(SQLiteDatabase connectDB, String urlChannel) {
        connectDB.delete(TABLE_FEED, COLUMN_URL + " = ?", new String[]{urlChannel});
    }

    private void deleteAllChannel(SQLiteDatabase connectDB) {
        connectDB.delete(TABLE_FEED, null, null);
    }

    private Completable refreshChannel(SQLiteDatabase connectDB, List<Channel> channelsList) {
        return Completable.create(emitter -> {
            Timber.d("rty Записали данные после рефреш");
            //ToDo: Дописать
        }).subscribeOn(Schedulers.io());
    }

    private List<Feed> buildChannelsList(SQLiteDatabase connectDB) {
        List<Feed> channelsList = new ArrayList<>();
        Cursor cursor;
        cursor = connectDB.query(TABLE_FEED, null, null, null, null, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                String feedUrl = null, feedTitle = null, feedLink = null, feedAuthor = null, feedDescription = null, feedImage = null, feedLastBuildDate = null;

                do {
                    for (String cn : cursor.getColumnNames()) {
                        switch (cn) {
                            case COLUMN_URL:
                                feedUrl = cursor.getString(cursor.getColumnIndex(cn));
                                break;
                            case COLUMN_TITLE_FEED:
                                feedTitle = cursor.getString(cursor.getColumnIndex(cn));
                                break;
                            case COLUMN_LINK_FEED:
                                feedLink = cursor.getString(cursor.getColumnIndex(cn));
                                break;
                            case COLUMN_AUTHOR_FEED:
                                feedAuthor = cursor.getString(cursor.getColumnIndex(cn));
                                break;
                            case COLUMN_DESCRIPTION_FEED:
                                feedDescription = cursor.getString(cursor.getColumnIndex(cn));
                                break;
                            case COLUMN_IMAGE:
                                feedImage = cursor.getString(cursor.getColumnIndex(cn));
                                break;
                            case COLUMN_LAST_BUILD:
                                feedLastBuildDate = cursor.getString(cursor.getColumnIndex(cn));
                                break;
                            default:
                                break;
                        }
                    }

                    Feed feed = new Feed(feedUrl, feedTitle, feedLink, feedAuthor, feedDescription, feedImage, feedLastBuildDate);
                    channelsList.add(feed);
                } while (cursor.moveToNext());
            }
        } else {
            //TODO: обработать ситуацию
            Timber.d("rty КУРСОР НУЛ");
        }
        cursor.close();
        return channelsList;
    }
}
