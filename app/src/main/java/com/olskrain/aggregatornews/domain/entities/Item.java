package com.olskrain.aggregatornews.domain.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Andrey Ievlev on 29,Апрель,2019
 */

public class Item implements Parcelable {
    private String title;
    private String pubDate;
    private String link;
    private String guid;
    private String author;
    private String thumbnail;
    private String description;
    private String content;
    private Object enclosure;
    private List<String> categories;

    public Item(String title, String pubDate, String link, String guid, String author, String thumbnail, String description, String content, Object enclosure, List<String> categories) {
        this.title = title;
        this.pubDate = pubDate;
        this.link = link;
        this.guid = guid;
        this.author = author;
        this.thumbnail = thumbnail;
        this.description = description;
        this.content = content;
        this.enclosure = enclosure;
        this.categories = categories;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(title);
        parcel.writeString(pubDate);
        parcel.writeString(link);
        parcel.writeString(guid);
        parcel.writeString(author);
        parcel.writeString(thumbnail);
        parcel.writeString(description);
        parcel.writeString(content);
        parcel.writeStringList(categories);
    }

    private Item(Parcel parcel) {
        title = parcel.readString();
        pubDate = parcel.readString();
        link = parcel.readString();
        guid = parcel.readString();
        author = parcel.readString();
        thumbnail = parcel.readString();
        description = parcel.readString();
        content = parcel.readString();
        categories = parcel.createStringArrayList();
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel parcel) {
            return new Item(parcel);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Object getEnclosure() {
        return enclosure;
    }

    public void setEnclosure(Object enclosure) {
        this.enclosure = enclosure;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
}
