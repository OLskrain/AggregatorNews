package com.olskrain.aggregatornews.data.cache;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.ArrayMap;

import com.olskrain.aggregatornews.Common.App;
import com.olskrain.aggregatornews.Common.Command;
import com.olskrain.aggregatornews.domain.entities.Channel;
import com.olskrain.aggregatornews.domain.entities.Feed;
import com.olskrain.aggregatornews.domain.entities.Item;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * Created by Andrey Ievlev on 27,Апрель,2019
 */

public class ChannelsListCache implements IChannelsListCache {

    public interface IResponseDBCallback {
        void sendMessageStatusCallingBack(String message);

        void sendChannelsListCallingBack(List<Channel> channelsList);
    }

    private static final String TABLE_FEED = "channaldf";
    private static final String TABLE_ITEM = "itemdfg";
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
    private static final String STATUS_UPDATE_DB = "Список каналов обновлен";

    private ArrayMap<Command, List<Channel>> requestParameters;
    private IResponseDBCallback callback;

    public ChannelsListCache() {
        this.requestParameters = new ArrayMap<>();
    }

    public void registerCallBack(IResponseDBCallback callback) {
        this.callback = callback;
    }

    @Override
    public void updateDatabase(Command command, List<Channel> channelsList) {
        @SuppressLint("StaticFieldLeak") AsyncTask<ArrayMap<Command, List<Channel>>, String, String> loadRSSAsync = new AsyncTask<ArrayMap<Command, List<Channel>>, String, String>() {

            @Override
            protected String doInBackground(ArrayMap<Command, List<Channel>>... requestParameters) {
                Command command = requestParameters[0].keyAt(0);
                List<Channel> channelsList = requestParameters[0].get(command);

                SQLiteDatabase connectDB = App.getDbHelper().getWritableDatabase();
                connectDB.execSQL("PRAGMA foreign_keys=ON");

                switch (command) {
                    case ADD_CHANNEL:
                    case REFRESH_CHANNELS:
                        updateChannelsList(channelsList, command, connectDB);
                        break;
                    case DELETE_CHANNEL:
                        //TODO:
                        //String feedUrl = channelsList.get(0).getFeed().getUrl();
                        String feedUrl = "https://news.yandex.ru/auto.rss";
                        connectDB.delete(TABLE_FEED, COLUMN_URL + " = ?", new String[]{feedUrl});
                        break;
                    case DELETE_ALL_CHANNELS:
                        connectDB.delete(TABLE_FEED, null, null);
                        break;
                    default:
                        break;
                }
                return STATUS_UPDATE_DB;
            }

            @Override
            protected void onPostExecute(String statusUpdateDB) {
                App.getDbHelper().close();
                callback.sendMessageStatusCallingBack(statusUpdateDB);
                requestParameters.clear();
                getData();
            }
        };
        requestParameters.put(command, channelsList);
        loadRSSAsync.execute(requestParameters);
    }

    @Override
    public void getData() {
        @SuppressLint("StaticFieldLeak") AsyncTask<String, String, List<Channel>> loadRSSAsync = new AsyncTask<String, String, List<Channel>>() {

            @Override
            protected List<Channel> doInBackground(String... requestParameters) {
                SQLiteDatabase connectDB = App.getDbHelper().getWritableDatabase();
                return buildChannelsList(connectDB);
            }

            @Override
            protected void onPostExecute(List<Channel> channelsList) {
                App.getDbHelper().close();
                callback.sendChannelsListCallingBack(channelsList);
            }
        };
        loadRSSAsync.execute();
    }

    private void updateChannelsList(List<Channel> channelsList, Command command, SQLiteDatabase connectDB) {
        for (int i = 0; i < channelsList.size(); i++) {
            String feedUrl = channelsList.get(i).getFeed().getUrl();
            String feedTitle = channelsList.get(i).getFeed().getTitle();
            String feedLink = channelsList.get(i).getFeed().getLink();
            String feedAuthor = channelsList.get(i).getFeed().getAuthor();
            String feedDescription = channelsList.get(i).getFeed().getDescription();
            String feedImage = channelsList.get(i).getFeed().getImage();
            String feedLastBuildDate = channelsList.get(i).getFeed().getLastBuildDate();
            List<Item> itemsList = channelsList.get(i).getItems();
            int idFeed = 0;
            switch (command) {
                case ADD_CHANNEL:
                    idFeed = (int) insertFeed(connectDB, TABLE_FEED, feedUrl, feedTitle, feedLink, feedAuthor, feedDescription, feedImage, feedLastBuildDate);
                    break;
                case REFRESH_CHANNELS:
                    //TODO: метод на обновление
                    break;
                default:
                    break;
            }
            for (int j = 0; j < itemsList.size(); j++) {
                String itemTitle = itemsList.get(j).getTitle();
                String itemPubDate = itemsList.get(j).getPubDate();
                String itemLink = itemsList.get(j).getLink();
                String itemGuid = itemsList.get(j).getGuid();
                String itemAuthor = itemsList.get(j).getAuthor();
                String itemThumbnail = itemsList.get(j).getThumbnail();
                String itemDescription = itemsList.get(j).getDescription();
                String itemContent = itemsList.get(j).getContent();
                switch (command) {
                    case ADD_CHANNEL:
                        insertItem(connectDB, TABLE_ITEM, idFeed, itemTitle, itemPubDate, itemLink, itemGuid, itemAuthor, itemThumbnail, itemDescription, itemContent);
                        break;
                    case REFRESH_CHANNELS:
                        //TODO: метод на обновление
                        break;
                    default:
                        break;
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

    private void insertItem(SQLiteDatabase connectDB, String table, int idFeed, String title, String pubDate, String link,
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
        connectDB.insert(table, null, contentValues);
    }

    private List<Channel> buildChannelsList(SQLiteDatabase connectDB) {
        List<Channel> channelsList = new ArrayList<>();
        Cursor cursor;

        String relatedTables = TABLE_ITEM + " as IM inner join " + TABLE_FEED + " as FD on IM.id_feed = FD.id";
        cursor = connectDB.query(relatedTables, null, null, null, null, null, null);
        List<Item> itemList = new ArrayList<>();
        Feed feed = null;
        Channel channel = null;

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                String feedUrl = null, feedTitle = null, feedLink = null, feedAuthor = null, feedDescription = null, feedImage = null, feedLastBuildDate = null;
                String itemTitle = null, itemPubDate = null, itemLink = null, itemGuid = null, itemAuthor = null, itemThumbnail = null, itemDescription = null, itemContent = null;
                int currentId = 2;
                int id = 0;
                do {
                    for (String cn : cursor.getColumnNames()) {
                        switch (cn) {
                            case COLUMN_ID_FEED:
                                id = cursor.getInt(cursor.getColumnIndex(cn));
                                break;
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
                            case COLUMN_TITLE:
                                itemTitle = cursor.getString(cursor.getColumnIndex(cn));
                                break;
                            case COLUMN_PUB_DATE:
                                itemPubDate = cursor.getString(cursor.getColumnIndex(cn));
                                break;
                            case COLUMN_LINK:
                                itemLink = cursor.getString(cursor.getColumnIndex(cn));
                                break;
                            case COLUMN_GUID:
                                itemGuid = cursor.getString(cursor.getColumnIndex(cn));
                                break;
                            case COLUMN_AUTHOR:
                                itemAuthor = cursor.getString(cursor.getColumnIndex(cn));
                                break;
                            case COLUMN_THUMBNAIL:
                                itemThumbnail = cursor.getString(cursor.getColumnIndex(cn));
                                break;
                            case COLUMN_DESCRIPTION:
                                itemDescription = cursor.getString(cursor.getColumnIndex(cn));
                                break;
                            case COLUMN_CONTENT:
                                itemContent = cursor.getString(cursor.getColumnIndex(cn));
                                break;
                            default:
                                break;
                        }

                    }

                    if (currentId > id) {
                        feed = new Feed(feedUrl, feedTitle, feedLink, feedAuthor, feedDescription, feedImage, feedLastBuildDate);
                        Item item = new Item(itemTitle, itemPubDate, itemLink, itemGuid, itemAuthor, itemThumbnail, itemDescription, itemContent, null, null);
                        itemList.add(item);
                    }
                    if (currentId == id) {
                        channel = new Channel(feed, itemList);
                        channelsList.add(channel);
                        itemList.clear();
                        currentId++;
                    }

                } while (cursor.moveToNext());
            }
        } else {
            Timber.d("rty КУРСОР НУЛ");
        }

        cursor.close();
        return channelsList;
    }
}
