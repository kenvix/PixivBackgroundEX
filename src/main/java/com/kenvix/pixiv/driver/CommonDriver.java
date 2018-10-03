package com.kenvix.pixiv.driver;

import com.kenvix.pixiv.generated.jooq.tables.Example;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SQLDialect;
import org.jooq.TableField;
import org.jooq.impl.DSL;
import com.kenvix.pixiv.generated.jooq.tables.Example.*;

import java.awt.*;
import java.io.File;
import java.sql.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class CommonDriver {
    protected String tableName;
    protected Connection conn;
    protected Statement stat;
    protected DSLContext dsl;

    public CommonDriver() throws SQLException {
        tableName = this.getClass().getSimpleName().substring(0, this.getClass().getSimpleName().indexOf("Driver"));
        conn = DriverManager.getConnection("jdbc:sqlite:" + new File("database.db").getAbsolutePath());
        stat = conn.createStatement();
        createEmptyTable(tableName);
        dsl = DSL.using(conn, SQLDialect.SQLITE);
    }

    protected int insert(ImageItem item, ImageStatus status, String filePath) throws SQLException {
        return insertIntoDatabase(item, status, filePath);
    }

    protected int insert(ImageItem item, ImageStatus status) throws SQLException{
        return insert(item, status, "");
    }

    protected int insert(ImageItem item) throws SQLException{
        return insert(item, ImageStatus.New, "");
    }


    public void insertNewItems(ImageItem[] items) {
        for (ImageItem item :items) {

        }
    }

    private boolean createEmptyTable(String tableName) throws SQLException {
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

    protected ImageItem formatRecordIntoImageItem(Record record) {
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

    public abstract int insertIntoDatabase(ImageItem item, ImageStatus status, String filePath);
    public abstract ImageItem getItemFromDatabaseByID(Integer id);
    public abstract ImageItem getItemFromDatabaseByImageURL(String imageURL);
    public abstract ImageItem getItemFromDatabaseByImgRawURL(String imageRawURL);
    public abstract ImageItem[] getItemsFromSite() throws Exception;
    public abstract ImageItem[] getItemsFromSite(String rawData) throws Exception;
}
