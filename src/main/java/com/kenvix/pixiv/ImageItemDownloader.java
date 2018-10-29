package com.kenvix.pixiv;

import com.kenvix.pixiv.driver.CommonDriver;
import com.kenvix.pixiv.driver.ImageItem;
import com.kenvix.pixiv.driver.ImageStatus;
import com.kenvix.pixiv.driver.Taskable;
import com.zhan_dui.download.DownloadMission;

import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

public class ImageItemDownloader<T extends CommonDriver> implements Taskable {
    private ConcurrentHashMap<String, ImageItem> tasks = new ConcurrentHashMap<>(); //synchronized
    private Downloader downloader = new Downloader();
    private Logger logger = Logger.getLogger("Downloader");
    private String savePath;
    private T target;

    public ImageItemDownloader(T target, String savePath) {
        this.target = target;
        this.savePath = savePath;
        importTasks(ImageStatus.New);
    }

    /**
     * import tasks from database by status
     * @param status
     */
    private void importTasks(ImageStatus status) {
        ImageItem[] items = target.getItemsFromDatabaseByStatus(status);
        for(ImageItem item: items)
            addTask(item);
    }

    /**
     * Call driver to scan items
     * @param status
     * @return
     */
    private ImageItem[] scanTasks(ImageStatus status) {

    }

    protected ImageItemDownloader addTask(ImageItem item) {
        tasks.put(item.getImgRawURL(), item);
        return this;
    }

    protected ImageItemDownloader removeTask(ImageItem item) {
        tasks.remove(item.getImgRawURL());
        return this;
    }

    protected void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (Exception ex) {}
    }

    private void doTask() {
        for (ConcurrentHashMap.Entry<String, ImageItem> entry: this.tasks.entrySet()) {
            ImageItem item = entry.getValue();
            try {
                removeTask(item);
                downloader.setURL(entry.getKey());
                downloader.downloadFile(item.getFilePath());
                DownloadMission mission = downloader.getDownloadMission();
                while(!mission.isFinished())
                    sleep(100);
                target.updateItemStatus(item, ImageStatus.OK);
            } catch (Exception ex) {
                logger.warning("Failed to download " + entry.getKey());
                ex.printStackTrace();
                item.addTriedNum();
                //TODO: DEFINE MAX FAILED NUM
                if(item.getTriedNum() < 10) {
                    target.updateItemStatus(item, ImageStatus.Failed);
                    target.updateItemTriedNum(item, item.getTriedNum());
                    addTask(item);
                } else {
                    target.updateItemStatus(item, ImageStatus.TriedButFailed);
                }
            }
        }
    }

    @Override
    public ImageItemDownloader start(int time) {
        while (true) {
            if(!tasks.isEmpty())
                doTask(); //should i support multi threading?
            else
                sleep(time);
        }
    }
}
