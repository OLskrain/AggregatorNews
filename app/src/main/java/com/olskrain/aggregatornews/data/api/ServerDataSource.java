package com.olskrain.aggregatornews.data.api;

import com.olskrain.aggregatornews.Common.IXmlRssParser;
import com.olskrain.aggregatornews.abctractFactory.FactoryProvider;
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

public class ServerDataSource implements IServerDataSource {
    private static final String ERROR_SERVER = "Ошибкасервера или парсера";
    private static final String GET_REQUEST = "GET";
    private final IXmlRssParser xmlRssParser = FactoryProvider.providerXmlRssParserFactory().createXmlRssParser();
    private final List<Channel> channelsList = new ArrayList<>();
    private String responseServer;

    @Override
    public Single<List<Channel>> getChannelFromApi(final List<String> urlList) {
        return Single.create(emitter -> {
            for (int i = 0; i < urlList.size(); i++) {
                String currentResponseServer = getHTTPData(urlList.get(i));
                if (currentResponseServer.equals(ERROR_SERVER)) {
                    emitter.onError(new RuntimeException());
                } else {
                    Channel channel = xmlRssParser.parseData(urlList.get(i), currentResponseServer);
                    channelsList.add(channel);
                }
            }
            emitter.onSuccess(channelsList);
            channelsList.clear();
        }).subscribeOn(Schedulers.io()).cast((Class<List<Channel>>) (Class) List.class);
    }

    @Override
    public Single<String> getWebPage(final String urlNews) {
        return Single.fromCallable(() -> getHTTPData(urlNews)).subscribeOn(Schedulers.io());
    }

    private String getHTTPData(final String urlString) {
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
            return ERROR_SERVER;
        } catch (IOException e) {
            e.printStackTrace();
            return ERROR_SERVER;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return responseServer;
    }
}
