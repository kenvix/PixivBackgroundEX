package com.kenvix.pixiv.pixiv;

import com.kenvix.pixiv.Main;
import com.kenvix.pixiv.driver.CommonDriver;
import com.kenvix.pixiv.driver.ImageItem;
import com.kenvix.pixiv.driver.ImageStatus;
import com.kenvix.pixiv.generated.jooq.tables.Pixiv;
import java.io.IOException;
import java.sql.SQLException;

public class PixivDriver extends CommonDriver {
    private String homepageURL;

    public PixivDriver(String homepageURL) throws SQLException, ClassNotFoundException {
        super();
        this.homepageURL = homepageURL;
    }

    @Override
    public PixivDriver start(int time) {
        logger.info("Starting Pixiv worker");
        while (true) {
            try {
                
            } catch (Exception ex) {
                logger.warning("Pixiv Worker exited with an exception, will restart after " + time + "ms");
                ex.printStackTrace();
            } finally {
                sleep(time);
            }
        }
    }

    @Override
    public ImageItem[] getItemsFromSite() throws IOException {
        return (new PixivParser(homepageURL).getImageItems());
    }

    @Override
    public ImageItem[] getItemsFromSite(String rawData) {
        return (new PixivParser(homepageURL).getImageItems(rawData));
    }

    @Override
    public int insertIntoDatabase(ImageItem item) {
        Pixiv table = Pixiv.PIXIV;
        return dsl.insertInto(table, table.AUTHOR, table.FILEPATH, table.FROMURL, table.IMAGEURL, table.IMGRAWURL, table.STATUS, table.TITLE)
                .values(item.getAuthor(), item.getFilePath(), item.getFromURL(), item.getImageURL(), item.getImgRawURL(), item.getStatus().toString(), item.getTitle())
                .returning(table.ID).fetchOne().getId();
    }

    @Override
    public ImageItem getItemFromDatabaseByID(Integer id) {
        return formatRecordIntoImageItem(dsl.select().from(Pixiv.PIXIV).where(Pixiv.PIXIV.ID.eq(id)).fetchOne());
    }

    @Override
    public ImageItem getItemFromDatabaseByImageURL(String imageURL) {
        return formatRecordIntoImageItem(dsl.select().from(Pixiv.PIXIV).where(Pixiv.PIXIV.IMAGEURL.eq(imageURL)).fetchOne());
    }

    @Override
    public ImageItem getItemFromDatabaseByImgRawURL(String imageRawURL) {
        return formatRecordIntoImageItem(dsl.select().from(Pixiv.PIXIV).where(Pixiv.PIXIV.IMGRAWURL.eq(imageRawURL)).fetchOne());
    }

    @Override
    public ImageItem[] getItemsFromDatabaseByStatus(ImageStatus status) {
        return formatRecordsIntoImageItems((dsl.select().from(Pixiv.PIXIV).where(Pixiv.PIXIV.STATUS.eq(status.toString())).fetchArray()));
    }

    @Override
    public ImageItem updateItemStatus(ImageItem item, ImageStatus status) {
        item.setStatus(status);
        dsl.update(Pixiv.PIXIV).set(Pixiv.PIXIV.STATUS, status.toString()).execute();
        return item;
    }
}
