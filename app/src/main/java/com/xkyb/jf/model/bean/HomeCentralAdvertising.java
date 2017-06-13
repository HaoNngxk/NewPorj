package com.xkyb.jf.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016/12/15.
 */

public class HomeCentralAdvertising implements Parcelable {
    private String aid;
    private String img;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.aid);
        dest.writeString(this.img);
    }

    public HomeCentralAdvertising() {
    }

    protected HomeCentralAdvertising(Parcel in) {
        this.aid = in.readString();
        this.img = in.readString();
    }

    public static final Parcelable.Creator<HomeCentralAdvertising> CREATOR = new Parcelable.Creator<HomeCentralAdvertising>() {
        @Override
        public HomeCentralAdvertising createFromParcel(Parcel source) {
            return new HomeCentralAdvertising(source);
        }

        @Override
        public HomeCentralAdvertising[] newArray(int size) {
            return new HomeCentralAdvertising[size];
        }
    };
}
