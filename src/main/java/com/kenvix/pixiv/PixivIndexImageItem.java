package com.kenvix.pixiv;
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

    public String www_member_illust_medium_url;
    public String www_user_url;

    public class url {
        public String medium;

        @SerializedName("1200x1200")
        public String better;
    }

    public class profile_img {
        public String main_s;
    }
}
