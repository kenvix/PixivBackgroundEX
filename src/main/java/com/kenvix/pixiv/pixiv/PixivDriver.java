package com.kenvix.pixiv.pixiv;

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
    public ImageItem[] getItemsFromSite() throws IOException {
        return (new PixivParser(homepageURL).getImageItems());
    }

    @Override
    public ImageItem[] getItemsFromSite(String rawData) {
        return (new PixivParser(homepageURL).getImageItems(rawData));
    }

    @Override
    public int insertIntoDatabase(ImageItem item, ImageStatus status, String filePath) {
        Pixiv table = Pixiv.PIXIV;
        return dsl.insertInto(table, table.AUTHOR, table.FILEPATH, table.FROMURL, table.IMAGEURL, table.IMGRAWURL, table.STATUS, table.TITLE)
                .values(item.getAuthor(), filePath, item.getFromURL(), item.getImageURL(), item.getImgRawURL(), status.toString(), item.getTitle())
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
}
