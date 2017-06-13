package com.xkyb.jf.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016/12/15.
 */

public class HomeHotShops implements Parcelable {
    private String area_name;
    private String shops_id;
    private String shopname;
    private String fencheng;
    private String img;
    private String grade;
    private String label;
    private String juli;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.area_name);
        dest.writeString(this.shops_id);
        dest.writeString(this.shopname);
        dest.writeString(this.fencheng);
        dest.writeString(this.img);
        dest.writeString(this.grade);
        dest.writeString(this.label);
        dest.writeString(this.juli);
    }

    public HomeHotShops() {
    }

    protected HomeHotShops(Parcel in) {
        this.area_name = in.readString();
        this.shops_id = in.readString();
        this.shopname = in.readString();
        this.fencheng = in.readString();
        this.img = in.readString();
        this.grade = in.readString();
        this.label = in.readString();
        this.juli = in.readString();
    }

    public static final Parcelable.Creator<HomeHotShops> CREATOR = new Parcelable.Creator<HomeHotShops>() {
        @Override
        public HomeHotShops createFromParcel(Parcel source) {
            return new HomeHotShops(source);
        }

        @Override
        public HomeHotShops[] newArray(int size) {
            return new HomeHotShops[size];
        }
    };
}
