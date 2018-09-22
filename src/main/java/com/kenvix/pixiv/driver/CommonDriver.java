package com.kenvix.pixiv.driver;

import java.io.File;
import java.sql.*;

public abstract class CommonDriver {
    private String tableName;
    private Connection conn;
    private Statement stat;

    public CommonDriver() throws SQLException {
        tableName = this.getClass().getSimpleName().substring(0, this.getClass().getSimpleName().indexOf("Driver"));
        conn = DriverManager.getConnection("jdbc:sqlite:" + new File("database.db").getAbsolutePath());
        stat = conn.createStatement();
        createEmptyTable(tableName);
    }

    public boolean createEmptyTable(String tableName) throws SQLException {
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

    /**
     * 插入到数据库，返回id即自增值
     * @param item
     * @param status
     * @param filePath
     * @return
     * @throws SQLException
     */
    public int insert(ImageItem item, ImageStatus status, String filePath) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("INSERT INTO \"main\".\""+tableName+"\"(\"Title\", \"Author\", \"FromURL\", \"ImgRawURL\", \"ImageURL\", \"FilePath\", \"Status\") VALUES ('"+item.getTitle()+"', '"+item.getAuthor()+"', '"+item.getFromURL()+"', '"+item.getImgRawURL()+"', '"+item.getImageURL()+"', '"+filePath+"', '"+status.toString()+"')", Statement.RETURN_GENERATED_KEYS);
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys(); //获取
        rs.next();
        return rs.getInt(1);
    }

    public int insert(ImageItem item, ImageStatus status) throws SQLException{
        return insert(item, status, "");
    }

    public int insert(ImageItem item) throws SQLException{
        return insert(item, ImageStatus.New, "");
    }


    public abstract ImageItem[] getItems() throws Exception;
    public abstract ImageItem[] getItems(String rawData) throws Exception;
}
