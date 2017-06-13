package com.xkyb.jf.model.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wangzheng on 16/3/2.
 */
public class MsgData<T> {

    @SerializedName("message")
    public String message;

    @SerializedName("data")
    public T data;
}
