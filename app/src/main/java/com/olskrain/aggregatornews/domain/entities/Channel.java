package com.olskrain.aggregatornews.domain.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey Ievlev on 29,Апрель,2019
 */

public class Channel implements Parcelable {
    private Feed feed;
    private List<Item> items = new ArrayList<>(); //нужна инициализация для десериализации

    public Channel(Feed feed, List<Item> items) {
        this.feed = feed;
        this.items = items;
    }

    private Channel(Parcel parcel) {
        feed = parcel.readParcelable(Feed.class.getClassLoader());
        parcel.readTypedList(items, Item.CREATOR);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(feed, flags);
        parcel.writeTypedList(items);
    }

    public static final Creator<Channel> CREATOR = new Creator<Channel>() {
        @Override
        public Channel createFromParcel(Parcel parcel) {
            return new Channel(parcel);
        }

        @Override
        public Channel[] newArray(int size) {
            return new Channel[size];
        }
    };

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
