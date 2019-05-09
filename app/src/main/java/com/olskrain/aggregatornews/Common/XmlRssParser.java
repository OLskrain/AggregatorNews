package com.olskrain.aggregatornews.Common;

import com.olskrain.aggregatornews.domain.entities.Channel;
import com.olskrain.aggregatornews.domain.entities.Feed;
import com.olskrain.aggregatornews.domain.entities.ItemNew;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey Ievlev on 04,Май,2019
 */

public class XmlRssParser {
    private static final String CHANNEL = "channel";
    private static final String ITEM = "item";
    private static final String TITLE = "title";
    private static final String LINK = "link";
    private static final String AUTHOR = "author";
    private static final String DESCRIPTION = "description";
    private static final String URL_IMAGE = "url";
    private static final String LAST_BUILD_DATA = "lastBuildDate";
    private static final String PUB_DATA = "pubDate";
    private static final String GUID = "guid";
    private static final String THUMBNAIL = "thumbnail";
    private static final String CONTENT = "content";

    private Channel channel;
    private Feed feed;
    private ItemNew item;
    private List<ItemNew> currentItemList = new ArrayList<>();
    private String current;

    public Channel parseData(String urlChannel, String responseServer) {
        try {
            XmlPullParser xpp = prepareXpp(responseServer);
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {

                if (xpp.getEventType() == XmlPullParser.START_TAG) {
                    if (xpp.getName().equalsIgnoreCase(CHANNEL)) {
                        parseFeed(urlChannel, xpp);
                    }
                    if (xpp.getName().equalsIgnoreCase(ITEM)) {
                        parseItem(xpp);
                    }
                }
                xpp.next();
            }
            channel = new Channel(feed, currentItemList);
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return channel;
    }

    private void parseFeed(String urlChannel, XmlPullParser xpp) {
        try {
            String title = null, link = null, author = null, description = null, image = null, lastBuildDate = null;
            xpp.next();

            while (true) {
                if (xpp.getEventType() == XmlPullParser.START_TAG) {
                    current = xpp.getName();
                }
                if (xpp.getEventType() == XmlPullParser.TEXT) {
                    switch (current) {
                        case TITLE:
                            title = xpp.getText();
                            break;
                        case LINK:
                            link = xpp.getText();
                            break;
                        case AUTHOR:
                            author = xpp.getText();
                            break;
                        case DESCRIPTION:
                            description = xpp.getText();
                            break;
                        case URL_IMAGE:
                            image = xpp.getText();
                            break;
                        case LAST_BUILD_DATA:
                            lastBuildDate = xpp.getText();
                            break;
                    }
                }
                if (xpp.getEventType() == XmlPullParser.END_TAG) {
                    if (xpp.getName().equalsIgnoreCase(LAST_BUILD_DATA)) {
                        break;
                    }
                }
                xpp.next();
            }
            feed = new Feed(urlChannel, title, link, author, description, image, lastBuildDate);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
    }

    private void parseItem(XmlPullParser xpp) {
        try {
            String title = null, link = null, author = null, description = null, image = null, lastBuildDate = null;
            String pubDate = null, guid = null, thumbnail = null, content = null;
            xpp.next();

            while (true) {
                if (xpp.getEventType() == XmlPullParser.START_TAG) {
                    current = xpp.getName();
                }
                if (xpp.getEventType() == XmlPullParser.TEXT) {
                    switch (current) {
                        case TITLE:
                            title = xpp.getText();
                            break;
                        case LINK:
                            link = xpp.getText();
                            break;
                        case AUTHOR:
                            author = xpp.getText();
                            break;
                        case DESCRIPTION:
                            description = xpp.getText();
                            break;
                        case URL_IMAGE:
                            image = xpp.getText();
                            break;
                        case LAST_BUILD_DATA:
                            lastBuildDate = xpp.getText();
                            break;
                        case PUB_DATA:
                            pubDate = xpp.getText();
                            break;
                        case GUID:
                            guid = xpp.getText();
                            break;
                        case THUMBNAIL:
                            thumbnail = xpp.getText();
                            break;
                        case CONTENT:
                            content = xpp.getText();
                            break;
                    }
                }
                if (xpp.getEventType() == XmlPullParser.END_TAG) {
                    if (xpp.getName().equalsIgnoreCase(ITEM)) {
                        break;
                    }
                }
                xpp.next();
            }
            item = new ItemNew(title, pubDate, link, guid, author, thumbnail, description, content, null, null);
            currentItemList.add(item);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
    }

    private XmlPullParser prepareXpp(String responseServer) throws XmlPullParserException {
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);
        XmlPullParser xpp = factory.newPullParser();
        xpp.setInput(new StringReader(responseServer));
        return xpp;
    }
}

