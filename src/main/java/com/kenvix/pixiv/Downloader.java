package com.kenvix.pixiv;

import com.zhan_dui.download.DownloadManager;
import com.zhan_dui.download.DownloadMission;

import java.io.IOException;

public class Downloader {
    private String url;
    private DownloadManager superDownloaderManager;
    private DownloadMission superDownloaderMission;

    public Downloader(String url) {
        this.url = url;
    }

    public Downloader() { }

    public Downloader setURL(String url) {
        this.url = url;
        return this;
    }

    public void inititalzeSuperDownloader() {
        superDownloaderManager = DownloadManager.getInstance();
    }

    public void downloadFile(String newName) throws IOException {
        downloadFile(newName, "");
    }

    public DownloadMission downloadFile(String newName, String saveDirectory) throws IOException {
        superDownloaderMission =  new DownloadMission(url, saveDirectory, newName);
        superDownloaderManager.addMission(superDownloaderMission);
        superDownloaderManager.start();
        return this.getDownloadMission();
    }

    public DownloadMission getDownloadMission() {
        return superDownloaderMission;
    }
    public DownloadManager getDownloadManager() {
        return superDownloaderManager;
    }
}
