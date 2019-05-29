package com.olskrain.aggregatornews.data.cache;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.olskrain.aggregatornews.Common.App;
import com.olskrain.aggregatornews.Common.Command;
import com.olskrain.aggregatornews.data.cache.interfaceCache.IChannelsListCache;
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
    private static final String COLUMN_IMAGE_FEED = "image";
    private static final String COLUMN_LAST_BUILD = "lastbuilddate";
    private static final String COLUMN_PUB_DATE = "pubDate";
    private static final String COLUMN_GUID = "guid";
    private static final String COLUMN_THUMBNAIL = "thumbnail";
    private static final String COLUMN_CONTENT = "content";
    private static final String COLUMN_URL_FEED = "url_feed";

    @Override
    public void updateDatabase(final Command command, final List<Channel> channelsList) {
        SQLiteDatabase connectDB = App.getInstance().getDbHelper().getWritableDatabase();
        connectDB.execSQL("PRAGMA foreign_keys=ON");

        switch (command) {
            case ADD_CHANNEL:
                addChannel(connectDB, channelsList);
                break;
            case REFRESH_CHANNELS:
                refreshChannel(connectDB, channelsList);
                break;
            default:
                break;
        }
        Timber.d("rty количество в БД " + buildChannelsList(connectDB).size());
        App.getInstance().getDbHelper().close();
    }

    @Override
    public Single<List<Feed>> getChannelsList() {
        return Single.fromCallable(() -> {
            SQLiteDatabase connectDB = App.getInstance().getDbHelper().getWritableDatabase();
            connectDB.execSQL("PRAGMA foreign_keys=ON");
            List<Feed> channelsList = buildChannelsList(connectDB);
            App.getInstance().getDbHelper().close();
            return channelsList;
        }).subscribeOn(Schedulers.io());
    }

    @Override
    public Completable deleteAllChannels() {
        return Completable.fromAction(() -> {
            SQLiteDatabase connectDB = App.getInstance().getDbHelper().getWritableDatabase();
            connectDB.execSQL("PRAGMA foreign_keys=ON");
            connectDB.delete(TABLE_FEED, null, null);
            App.getInstance().getDbHelper().close();
        }).subscribeOn(Schedulers.io());
    }

    @Override
    public Completable deleteChannel(final String urlChannel) {
        return Completable.fromAction(() -> {
            SQLiteDatabase connectDB = App.getInstance().getDbHelper().getWritableDatabase();
            connectDB.execSQL("PRAGMA foreign_keys=ON");
            connectDB.delete(TABLE_FEED, COLUMN_URL + " = ?", new String[]{urlChannel});
            App.getInstance().getDbHelper().close();
        }).subscribeOn(Schedulers.io());
    }

    private void refreshChannel(final SQLiteDatabase connectDB, final List<Channel> channelsList) {
        for (int i = 0; i < channelsList.size(); i++) {
            String feedUrl = channelsList.get(i).getFeed().getUrl();
            String feedTitle = channelsList.get(i).getFeed().getTitle();
            String feedImage = channelsList.get(i).getFeed().getImage();
            String feedLastBuildDate = channelsList.get(i).getFeed().getLastBuildDate();
            List<ItemNew> itemNewsList = channelsList.get(i).getItemNew();

            ContentValues cv = new ContentValues();
            cv.put(COLUMN_TITLE_FEED, feedTitle);
            cv.put(COLUMN_IMAGE_FEED, feedImage);
            cv.put(COLUMN_LAST_BUILD, feedLastBuildDate);

            connectDB.update(TABLE_FEED, cv, COLUMN_URL + " = ?", new String[]{feedUrl});

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

                    insertItemNew(connectDB, TABLE_ITEM_NEWS, feedUrl, itemNewTitle, itemNewPubDate, itemNewLink, itemNewGuid, itemNewAuthor, itemNewThumbnail, itemNewDescription, itemNewContent);
                }
            }
        }
    }

    private void addChannel(final SQLiteDatabase connectDB, final List<Channel> channelsList) {
        for (int i = 0; i < channelsList.size(); i++) {
            String feedUrl = channelsList.get(i).getFeed().getUrl();
            String feedTitle = channelsList.get(i).getFeed().getTitle();
            String feedLink = channelsList.get(i).getFeed().getLink();
            String feedAuthor = channelsList.get(i).getFeed().getAuthor();
            String feedDescription = channelsList.get(i).getFeed().getDescription();
            String feedImage = channelsList.get(i).getFeed().getImage();
            String feedLastBuildDate = channelsList.get(i).getFeed().getLastBuildDate();
            List<ItemNew> itemNewsList = channelsList.get(i).getItemNew();

            insertFeed(connectDB, TABLE_FEED, feedUrl, feedTitle, feedLink, feedAuthor, feedDescription, feedImage, feedLastBuildDate);

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

                    insertItemNew(connectDB, TABLE_ITEM_NEWS, feedUrl, itemNewTitle, itemNewPubDate, itemNewLink, itemNewGuid, itemNewAuthor, itemNewThumbnail, itemNewDescription, itemNewContent);
                }
            }
        }
    }

    private long insertFeed(final SQLiteDatabase connectDB, final String table, final String url, final String title, final String link,
                            final String author, final String description, final String image, final String lastBuildDate) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_URL, url);
        contentValues.put(COLUMN_TITLE_FEED, title);
        contentValues.put(COLUMN_LINK_FEED, link);
        contentValues.put(COLUMN_AUTHOR_FEED, author);
        contentValues.put(COLUMN_DESCRIPTION_FEED, description);
        contentValues.put(COLUMN_IMAGE_FEED, image);
        contentValues.put(COLUMN_LAST_BUILD, lastBuildDate);
        return connectDB.insert(table, null, contentValues);
    }

    private long insertItemNew(final SQLiteDatabase connectDB, final String table, final String feedUrl, final String title, final String pubDate, String link,
                               final String guid, final String author, final String thumbnail, final String description, final String content) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TITLE, title);
        contentValues.put(COLUMN_PUB_DATE, pubDate);
        contentValues.put(COLUMN_LINK, link);
        contentValues.put(COLUMN_GUID, guid);
        contentValues.put(COLUMN_AUTHOR, author);
        contentValues.put(COLUMN_THUMBNAIL, thumbnail);
        contentValues.put(COLUMN_DESCRIPTION, description);
        contentValues.put(COLUMN_CONTENT, content);
        contentValues.put(COLUMN_URL_FEED, feedUrl);
        return connectDB.insert(table, null, contentValues);
    }

    private List<Feed> buildChannelsList(final SQLiteDatabase connectDB) {
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
                            case COLUMN_IMAGE_FEED:
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
