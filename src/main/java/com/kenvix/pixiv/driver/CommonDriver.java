package com.kenvix.pixiv.driver;

import com.kenvix.pixiv.Downloader;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.logging.Logger;

public abstract class CommonDriver implements Taskable {
    protected String tableName;
    protected Connection conn;
    protected Statement stat;
    protected DSLContext dsl;
    protected Logger logger;

    public CommonDriver() throws SQLException {
        tableName = this.getClass().getSimpleName().substring(0, this.getClass().getSimpleName().indexOf("Driver"));
        conn = DriverManager.getConnection("jdbc:sqlite:" + new File("database.db").getAbsolutePath());
        stat = conn.createStatement();
        createEmptyTable(tableName);
        dsl = DSL.using(conn, SQLDialect.SQLITE);
        logger = Logger.getLogger(tableName);
    }

    protected int insert(ImageItem item) {
        return insertIntoDatabase(item);
    }

    public void insertNewItems(ImageItem[] items) {
        for (ImageItem item : items) {
            if (getItemFromDatabaseByImgRawURL(item.getImgRawURL()) == null) {
                item.setStatus(ImageStatus.New);
                insert(item);
            }
        }
    }

    protected final ImageItem formatRecordIntoImageItem(Record record) {
        if(record.size() < 1) return null;
        CommonImageItem item = new CommonImageItem();
        item.author = record.getValue("Author").toString();
        item.title = record.getValue("Title").toString();
        item.fromURL = record.getValue("FromURL").toString();
        item.imageURL = record.getValue("ImageURL").toString();
        item.imgRawURL = record.getValue("ImgRawURL").toString();
        item.filePath = record.getValue("FilePath").toString();
        item.status = (ImageStatus) record.getValue("Status");
        item.fieldID = (int) record.getValue("ID");
        return item;
    }

    protected final ImageItem[] formatRecordsIntoImageItems(Record[] records) {
        LinkedList<ImageItem> items = new LinkedList<>();
        for (Record record: records)
            items.add(formatRecordIntoImageItem(record));
        return (ImageItem[]) items.toArray();
    }

    /**
     * Pause
     * @param time time in ms
     */
    protected final void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (Exception ex) {}
    }

    protected boolean createEmptyTable(String tableName) throws SQLException {
        return stat.execute("CREATE TABLE IF NOT EXISTS \"main\".\""+tableName+"\" (\n" +
                "  \"ID\" integer NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "  \"Title\" TEXT NOT NULL DEFAULT '',\n" +
                "  \"Author\" TEXT NOT NULL DEFAULT '',\n" +
                "  \"FromURL\" TEXT NOT NULL DEFAULT '',\n" +
                "  \"ImgRawURL\" TEXT NOT NULL DEFAULT '' UNIQUE,\n" +
                "  \"ImageURL\" TEXT NOT NULL DEFAULT '' UNIQUE,\n" +
                "  \"FilePath\" TEXT NOT NULL DEFAULT '',\n" +
                "  \"Status\" TEXT NOT NULL DEFAULT 0\n" +
                ");");
    }

    public abstract int insertIntoDatabase(ImageItem item);
    public abstract ImageItem getItemFromDatabaseByID(Integer id);
    public abstract ImageItem getItemFromDatabaseByImageURL(String imageURL);
    public abstract ImageItem getItemFromDatabaseByImgRawURL(String imageRawURL);
    public abstract ImageItem[] getItemsFromDatabaseByStatus(ImageStatus status);
    public abstract ImageItem[] getItemsFromSite() throws IOException;
    public abstract ImageItem[] getItemsFromSite(String rawData);

    /**
     * Update status of a ImageItem (will apply it to database)
     * @param item ImageItem
     * @param status ImageStatus
     * @return updated ImageItem
     */
    public abstract ImageItem updateItemStatus(ImageItem item, ImageStatus status);
}
