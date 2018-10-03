package com.kenvix.pixiv.driver;

import com.kenvix.pixiv.driver.ImageItem;

public class CommonImageItem implements ImageItem {
    public int fieldID = 0;

    //illust_title
    public String title;

    //userName
    public String author;

    //www_member_illust_medium_url
    public String fromURL;

    public String imgRawURL;

    /**
     * Original Image URL
     */
    public String imageURL;

    public ImageStatus status = ImageStatus.Unknown;

    public String filePath = "";

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getFromURL() { return fromURL;}
    public String getImgRawURL() { return imgRawURL; }
    public String getImageURL() { return imageURL; }
    public ImageStatus getStatus() { return status; }
    public String getFilePath() { return filePath; }
    public int getFieldID() { return fieldID; }
    public void setStatus(ImageStatus newStatus) { status = newStatus; }
    public void setFilePath(String newFilePath) { filePath = newFilePath; }
}
