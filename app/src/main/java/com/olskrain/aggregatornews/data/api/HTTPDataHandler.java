package com.olskrain.aggregatornews.data.api;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HTTPDataHandler {
    private static final String ERROR_URL = "error url";
    private static final String ERROR_SERVER = "error server";
    private static final String GET_REQUEST = "GET";
    private String responseServer;

    public HTTPDataHandler() {
    }

    public String getHTTPData(String urlString) {
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
