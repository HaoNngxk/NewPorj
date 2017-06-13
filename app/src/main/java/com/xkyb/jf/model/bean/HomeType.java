package com.xkyb.jf.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016/12/15.
 */

public class HomeType implements Parcelable {
    private String type_id;
    private String img;
    private String name;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.type_id);
        dest.writeString(this.img);
        dest.writeString(this.name);
    }

    public HomeType() {
    }

    protected HomeType(Parcel in) {
        this.type_id = in.readString();
        this.img = in.readString();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<HomeType> CREATOR = new Parcelable.Creator<HomeType>() {
        @Override
        public HomeType createFromParcel(Parcel source) {
            return new HomeType(source);
        }

        @Override
        public HomeType[] newArray(int size) {
            return new HomeType[size];
        }
    };
}
