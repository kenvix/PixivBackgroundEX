package com.kenvix.pixiv.pixiv.data;
import com.google.gson.annotations.SerializedName;

/**
 * PixivIndexImageItem
 * 单个PIXIV项目
 */
public class PixivIndexImageItem {
    @SerializedName("illust_id")
    public int illustID;

    @SerializedName("illust_title")
    public String illustTitle;

    @SerializedName("userName")
    public String user_name;

    @SerializedName("www_member_illust_medium_url")
    public String memberIllustMediumURL;

    @SerializedName("www_user_url")
    public String userURL;

    public PixivIndexImageItemURL url;

    @SerializedName("profile_img")
    public PixivIndexImageItemProfileImg profileImg;
}