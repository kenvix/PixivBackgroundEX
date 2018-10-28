package com.kenvix.pixiv;

import com.kenvix.pixiv.driver.CommonDriver;
import com.kenvix.pixiv.driver.ImageItem;
import com.kenvix.pixiv.driver.ImageStatus;
import com.kenvix.pixiv.driver.Taskable;
import com.zhan_dui.download.DownloadMission;

import java.util.HashMap;
import java.util.logging.Logger;

public class ImageItemDownloader<T extends CommonDriver> implements Taskable {
    private HashMap<String, ImageItem> tasks = new HashMap<>();
    private Downloader downloader = new Downloader();
    private Logger logger = Logger.getLogger("Downloader");
    private String savePath;
    private T target;

    public ImageItemDownloader(T target, String savePath) {
        this.target = target;
        this.savePath = savePath;
        importTasks(ImageStatus.New);
    }

    private void importTasks(ImageStatus status) {
        ImageItem[] items = target.getItemsFromDatabaseByStatus(status);
        for(ImageItem item: items)
            addTask(item);
    }

    private synchronized ImageItem[] scanTasks(ImageStatus status) {

    }

    protected synchronized ImageItemDownloader addTask(ImageItem item) {
        tasks.put(item.getImgRawURL(), item);
        return this;
    }

    protected synchronized ImageItemDownloader removeTask(ImageItem item) {
        tasks.remove(item.getImgRawURL());
        return this;
    }

    protected void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (Exception ex) {}
    }

    private void doTask() {
        for (HashMap.Entry<String, ImageItem> entry: this.tasks.entrySet()) {
            try {
                removeTask(entry.getValue());
                downloader.setURL(entry.getKey());
                downloader.downloadFile(entry.getValue().getFilePath());
                DownloadMission mission = downloader.getDownloadMission();
                while(!mission.isFinished())
                    sleep(100);
            } catch (Exception ex) {
                logger.warning("Failed to download " + entry.getKey());
                ex.printStackTrace();
                addTask(entry.getValue());
            }
        }
    }

    @Override
    public ImageItemDownloader start(int time) {
        while (true) {
            if(!tasks.isEmpty())
                doTask();
            else
                sleep(time);
        }
    }
}
