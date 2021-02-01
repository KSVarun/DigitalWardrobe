package com.hci.digitalwardrobe.Classes;

import android.os.Parcel;
import android.os.Parcelable;

public class Clothes_temp implements Parcelable {

    private String title;
    private String description;
    private String imageURL;

    public Clothes_temp(String title, String description, String imageURL){
        this.title = title;
        this.description = description;
        this.imageURL = imageURL;
    }

    protected Clothes_temp(Parcel in) {
        title = in.readString();
        description = in.readString();
        imageURL = in.readString();
    }

    public static final Creator<Clothes_temp> CREATOR = new Creator<Clothes_temp>() {
        @Override
        public Clothes_temp createFromParcel(Parcel in) {
            return new Clothes_temp(in);
        }

        @Override
        public Clothes_temp[] newArray(int size) {
            return new Clothes_temp[size];
        }
    };

    public String getTitle(){
        return title;
    }

    public String getDescription(){
        return description;
    }

    public String getImageURL(){
        return imageURL;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(imageURL);
    }
}
