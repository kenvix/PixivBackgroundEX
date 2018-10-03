package com.kenvix.pixiv.driver;

public interface ImageItem {
    String getTitle();
    String getAuthor();
    String getFromURL();
    String getImgRawURL();
    String getImageURL();
    ImageStatus getStatus();
    String getFilePath();
    int getFieldID();
}
