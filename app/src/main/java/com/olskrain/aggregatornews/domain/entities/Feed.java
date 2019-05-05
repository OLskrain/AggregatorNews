package com.olskrain.aggregatornews.domain.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Andrey Ievlev on 29,Апрель,2019
 */

public class Feed implements Parcelable {
    private String url;
    private String title;
    private String link;
    private String author;
    private String description;
    private String image;
    private String lastBuildDate;

    public Feed(String url, String title, String link, String author, String description, String image, String lastBuildDate) {
        this.url = url;
        this.title = title;
        this.link = link;
        this.author = author;
        this.description = description;
        this.image = image;
        this.lastBuildDate = lastBuildDate;
    }

    private Feed(Parcel parcel) {
        url = parcel.readString();
        title = parcel.readString();
        link = parcel.readString();
        author = parcel.readString();
        description = parcel.readString();
        image = parcel.readString();
        lastBuildDate = parcel.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(url);
        parcel.writeString(title);
        parcel.writeString(link);
        parcel.writeString(author);
        parcel.writeString(description);
        parcel.writeString(image);
        parcel.writeString(lastBuildDate);
    }

    public static final Creator<Feed> CREATOR = new Creator<Feed>() {
        @Override
        public Feed createFromParcel(Parcel parcel) {
            return new Feed(parcel);
        }

        @Override
        public Feed[] newArray(int size) {
            return new Feed[size];
        }
    };

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLastBuildDate() {
        return lastBuildDate;
    }

    public void setLastBuildDate(String lastBuildDate) {
        this.lastBuildDate = lastBuildDate;
    }
}