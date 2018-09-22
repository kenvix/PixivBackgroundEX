package com.kenvix.pixiv.pixiv.data;
import com.kenvix.pixiv.driver.ImageItem;

/**
 * PixivIndexImageItem
 * 单个PIXIV项目
 */
public class PixivIndexImageItem implements ImageItem {
    //illust_id
    public int ID;

    //illust_title
    public String title;

    //userName
    public String author;

    //www_member_illust_medium_url
    public String fromURL;

    //www_user_url
    public String userURL;

    public String imgRawURL;

    //url
    public PixivIndexImageItemURL url;

    /**
     * Original Image URL
     */
    public String imageURL;

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getFromURL() { return fromURL;}
    public String getImgRawURL() { return imgRawURL; }
    public String getImageURL() { return imageURL; }
}