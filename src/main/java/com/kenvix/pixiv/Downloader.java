package com.kenvix.pixiv;

import com.zhan_dui.download.DownloadManager;
import com.zhan_dui.download.DownloadMission;
import org.jetbrains.annotations.NotNull;

import java.net.URL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

public class Downloader {
    private String url;
    private DownloadManager superDownloaderManager;
    private DownloadMission superDownloaderMission;

    public Downloader(@NotNull String url) {
        this.url = url;
    }

    public void inititalzeSuperDownloader() {
        superDownloaderManager = DownloadManager.getInstance();
    }

    public void downloadFile(String newName) throws IOException {
        downloadFile(newName, "");
    }

    public void downloadFile(String newName, String saveDirectory) throws IOException {
        superDownloaderMission =  new DownloadMission(url, saveDirectory, newName);
        superDownloaderManager.addMission(superDownloaderMission);
        superDownloaderManager.start();
    }

    public DownloadMission getDownloadMission() {
        return superDownloaderMission;
    }

    public DownloadManager getDownloadManager() {
        return superDownloaderManager;
    }
}
