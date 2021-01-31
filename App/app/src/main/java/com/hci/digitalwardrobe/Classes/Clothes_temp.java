package com.hci.digitalwardrobe.Classes;

import android.os.Parcel;
import android.os.Parcelable;
import android.security.identity.IdentityCredentialStore;

public class Clothes_temp implements Parcelable {

    private String title;
    private String description;
    private int image_resource_id;

    public Clothes_temp(String title, String description, int image_resource_id){
        this.title = title;
        this.description = description;
        this.image_resource_id = image_resource_id;
    }

    protected Clothes_temp(Parcel in) {
        title = in.readString();
        description = in.readString();
        image_resource_id = in.readInt();
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

    public int getImage_resource_id(){
        return image_resource_id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeInt(image_resource_id);
    }
}
