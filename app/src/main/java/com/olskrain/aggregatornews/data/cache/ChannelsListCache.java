package com.olskrain.aggregatornews.data.cache;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.ArrayMap;

import com.olskrain.aggregatornews.Common.App;
import com.olskrain.aggregatornews.Common.Command;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey Ievlev on 27,Апрель,2019
 */

public class ChannelsListCache implements IChannelsListCache {

    public interface IResponseDBCallback {
        void sendMessageStatusCallingBack(String message);
        void sendChannelsListCallingBack(List<String> channelsList);
    }

    private static final String TABLE_CHANNELS_LIST = "channelsList";
    private static final String COLUMN_CHANNEL = "channel";
    private static final String STATUS_UPDATE_DB = "Список каналов обновлен";

    private ArrayMap<Command, String> requestParameters;
    private IResponseDBCallback callback;

    public ChannelsListCache() {
        this.requestParameters = new ArrayMap<>();
    }

    public void registerCallBack(IResponseDBCallback callback) {
        this.callback = callback;
    }

    @Override
    public void updateDatabase(Command command, String urlChannel) {
        @SuppressLint("StaticFieldLeak") AsyncTask<ArrayMap<Command, String>, String, String> loadRSSAsync = new AsyncTask<ArrayMap<Command, String>, String, String>() {

            @Override
            protected String doInBackground(ArrayMap<Command, String>... requestParameters) {
                ContentValues contentValues = new ContentValues();
                SQLiteDatabase connectDB = App.getDbHelper().getWritableDatabase();

                Command command = requestParameters[0].keyAt(0);
                String urlChannel = requestParameters[0].get(command);
                switch (command) {
                    case ADD_CHANNEL:
                        contentValues.put(COLUMN_CHANNEL, urlChannel);
                        connectDB.insert(TABLE_CHANNELS_LIST, null, contentValues);
                        break;
                    case DELETE_CHANNEL:
                        if (urlChannel.equalsIgnoreCase("")) {
                            break;
                        }
                        int delCount = connectDB.delete(TABLE_CHANNELS_LIST, COLUMN_CHANNEL + " = ?", new String[]{urlChannel});
                        break;
                    case DELETE_ALL_CHANNELS:
                        connectDB.delete(TABLE_CHANNELS_LIST, null, null);
                        break;
                }
                App.getDbHelper().close();
                return STATUS_UPDATE_DB;
            }

            @Override
            protected void onPostExecute(String statusUpdateDB) {
                callback.sendMessageStatusCallingBack(statusUpdateDB);
                getData();
                requestParameters.clear();
            }
        };
        requestParameters.put(command, urlChannel);
        loadRSSAsync.execute(requestParameters);
    }

    @Override
    public void getData() {
        @SuppressLint("StaticFieldLeak") AsyncTask<ArrayMap<Command, String>, String, List<String>> loadRSSAsync = new AsyncTask<ArrayMap<Command, String>, String, List<String>>() {

            @Override
            protected List<String> doInBackground(ArrayMap<Command, String>... requestParameters) {
                SQLiteDatabase connectDB = App.getDbHelper().getWritableDatabase();
                List<String> channelsList = new ArrayList<>();

                Cursor cursor = connectDB.query(TABLE_CHANNELS_LIST, null, null, null, null, null, null);
                if (cursor.moveToFirst()) {
                    //int idIndex = cursor.getColumnIndex(COLUMN_ID);
                    int channelIndex = cursor.getColumnIndex(COLUMN_CHANNEL);
                    do {
                        channelsList.add(cursor.getString(channelIndex));
                    } while (cursor.moveToNext());
                } else cursor.close();

                App.getDbHelper().close();
                return channelsList;
            }

            @Override
            protected void onPostExecute(List<String> channelsList) {
                //Todo:вернуть колбек
                callback.sendChannelsListCallingBack(channelsList);
            }
        };

        loadRSSAsync.execute();
    }
}
