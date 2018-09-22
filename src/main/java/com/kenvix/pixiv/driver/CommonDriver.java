package com.kenvix.pixiv.driver;

public abstract class CommonDriver {

    public abstract ImageItem[] getItems() throws Exception;
    public abstract ImageItem[] getItems(String rawData) throws Exception;
}
