package com.kenvix.pixiv.pixiv;

import com.kenvix.pixiv.driver.CommonDriver;
import com.kenvix.pixiv.driver.ImageItem;

import java.io.IOException;
import java.sql.SQLException;

public class PixivDriver extends CommonDriver {
    private String homepageURL;

    public PixivDriver(String homepageURL) throws SQLException, ClassNotFoundException {
        super();
        this.homepageURL = homepageURL;
    }

    @Override
    public ImageItem[] getItems() throws IOException {
        return (new PixivParser(homepageURL).getImageItems());
    }

    @Override
    public ImageItem[] getItems(String rawData) {
        return (new PixivParser(homepageURL).getImageItems(rawData));
    }
}
