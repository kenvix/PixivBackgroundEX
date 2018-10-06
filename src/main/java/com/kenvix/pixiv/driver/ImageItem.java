package com.kenvix.pixiv.driver;

public interface ImageItem {
    String getTitle();
    String getAuthor();
    String getFromURL();
    String getImgRawURL();
    String getImageURL();
    ImageStatus getStatus();

    /**
     * Get relative file path of a image file.
     * Returns file name only if file has not been downloaded
     * @return String
     */
    String getFilePath();
    int getFieldID();
    void setStatus(ImageStatus newStatus);
    void setFilePath(String newFilePath);
}
