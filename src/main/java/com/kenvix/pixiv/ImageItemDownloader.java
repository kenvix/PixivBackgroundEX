package com.kenvix.pixiv;

import com.kenvix.pixiv.driver.ImageItem;
import com.kenvix.pixiv.driver.Taskable;
import com.zhan_dui.download.DownloadMission;

import java.util.HashMap;
import java.util.logging.Logger;

public class ImageItemDownloader implements Taskable {
    private HashMap<String, ImageItem> tasks = new HashMap<>();
    private Downloader downloader = new Downloader();
    private Logger logger = Logger.getLogger("Downloader");
    private String savePath;

    public ImageItemDownloader(String savePath) {
        this.savePath = savePath;
    }

    public synchronized ImageItemDownloader addTask(String imgRawURL, ImageItem item) {
        tasks.put(imgRawURL, item);
        return this;
    }

    public synchronized ImageItemDownloader removeTask(String imgRawURL) {
        tasks.remove(imgRawURL);
        return this;
    }

    public void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (Exception ex) {}
    }

    @Override
    public void start(int time) {
        while (true) {
            if(!tasks.isEmpty()) {
                for (HashMap.Entry<String, ImageItem> entry: this.tasks.entrySet()) {
                    try {
                        removeTask(entry.getKey());
                        downloader.setURL(entry.getKey());
                        downloader.downloadFile(entry.getValue().getFilePath());
                        DownloadMission mission = downloader.getDownloadMission();
                        while(!mission.isFinished())
                            sleep(100);
                    } catch (Exception ex) {
                        logger.warning("Failed to download " + entry.getKey());
                        ex.printStackTrace();
                        addTask(entry.getKey(), entry.getValue());
                    }
                }
            } else {
                sleep(time);
            }
        }
    }
}
