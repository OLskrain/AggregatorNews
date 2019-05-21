package com.olskrain.aggregatornews.data.api;

import com.olskrain.aggregatornews.Common.XmlRssParser;
import com.olskrain.aggregatornews.domain.entities.Channel;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class ServerDataSource {
    private static final String ERROR_URL = "error url";
    private static final String ERROR_SERVER = "Ошибка на сервере или парсера";
    private static final String GET_REQUEST = "GET";
    private XmlRssParser xmlRssParser;
    private String responseServer;
    private List<Channel> channelsList;

    public ServerDataSource() {
        this.xmlRssParser = new XmlRssParser();
        this.channelsList = new ArrayList<>();
    }

    public Single<List<Channel>> getChannelFromApi(List<String> urlList) {
        return Single.create(emitter -> {
            for (int i = 0; i < urlList.size(); i++) {
                getHTTPData(urlList.get(i));
                Channel channel = xmlRssParser.parseData(urlList.get(i), responseServer);
                channelsList.add(channel);
            }

            if (channelsList.isEmpty()) {
                emitter.onError(new RuntimeException(ERROR_SERVER));
            } else {
                emitter.onSuccess(channelsList);
                channelsList.clear();
            }
        }).subscribeOn(Schedulers.io()).cast((Class<List<Channel>>) (Class) List.class);
    }

    private String getHTTPData(String urlString) {
        HttpURLConnection urlConnection = null;

        try {
            URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod(GET_REQUEST);
            urlConnection.connect();
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
                StringBuilder currentString = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null)
                    currentString.append(line);
                responseServer = currentString.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            responseServer = ERROR_URL;
        } catch (IOException e) {
            e.printStackTrace();
            responseServer = ERROR_SERVER;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return responseServer;
    }
}
