package com.kenvix.pixiv.pixiv.data;

import com.google.gson.annotations.SerializedName;

public class PixivIndexImageItemURL {
    public String medium;

    /**
     * example:https://i.pximg.net/img-master/img/2013/07/08/01/50/25/36919122_p0_master1200.jpg
     */
    @SerializedName("1200x1200")
    public String master;
}