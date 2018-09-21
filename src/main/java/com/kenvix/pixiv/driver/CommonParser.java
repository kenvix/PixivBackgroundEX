package com.kenvix.pixiv.driver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public abstract class CommonParser {

    public final String catchNetworkData(URL url) throws IOException {
        StringBuilder data = new StringBuilder();
        String temp;
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        while ((temp = in.readLine()) != null) {
            data.append(temp);
        }
        in.close();
        return data.toString();
    }

    public String catchNetworkData(String url) throws IOException {
        return catchNetworkData(new URL(url));
    }

    public abstract ImageItem[] getImageItems(String rawData) throws Exception;
    public abstract ImageItem[] getImageItems() throws Exception;
    public abstract String getImgURL(ImageItem item) throws Exception;
}
