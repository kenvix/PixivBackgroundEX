package com.kenvix.pixiv.pixiv;

import com.kenvix.pixiv.driver.CommonDriver;
import com.kenvix.pixiv.driver.ImageItem;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class PixivDriver extends CommonDriver {
    private String homepageURL;

    public PixivDriver(String homepageURL) {
        this.homepageURL = homepageURL;
    }

    @Override
    public ImageItem[] getItems() throws ParserConfigurationException, IOException, SAXException {
        return (new PixivParser(homepageURL).getImageItems());
    }

    @Override
    public ImageItem[] getItems(String rawData) throws ParserConfigurationException, IOException, SAXException {
        return (new PixivParser(homepageURL).getImageItems(rawData));
    }
}
