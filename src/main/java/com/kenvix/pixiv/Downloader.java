package com.kenvix.pixiv;

import org.jetbrains.annotations.NotNull;

import java.net.URL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

public class Downloader {
    private URL url;

    public Downloader(@NotNull String url) throws MalformedURLException {
        this.url = new URL(url);
    }

    public Downloader(@NotNull URL url) {
        this.url = url;
    }

    public String catchAsString() throws IOException {
        StringBuilder data = new StringBuilder();
        String temp;
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        while ((temp = in.readLine()) != null) {
            data.append(temp);
        }
        in.close();
        return data.toString();
    }
}
