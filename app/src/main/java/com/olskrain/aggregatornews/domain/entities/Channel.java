package com.olskrain.aggregatornews.domain.entities;

import java.util.List;

/**
 * Created by Andrey Ievlev on 29,Апрель,2019
 */

public class Channel {
    private String status;
    private Feed feed;
    private List<Item> items;

    public Channel(String status, Feed feed, List<Item> items) {
        this.status = status;
        this.feed = feed;
        this.items = items;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Feed getFeed() {
        return feed;
    }

    public void setFeed(Feed feed) {
        this.feed = feed;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
