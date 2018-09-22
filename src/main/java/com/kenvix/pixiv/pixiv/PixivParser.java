package com.kenvix.pixiv.pixiv;

import com.kenvix.pixiv.driver.CommonParser;
import com.kenvix.pixiv.driver.ImageItem;
import com.kenvix.pixiv.pixiv.data.PixivIndexImageItem;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;

class PixivParser extends CommonParser {
    static final String imgMasterFlag = "img-master";
    static final String imgOriginalFlag = "img-original";
    static final String imgMasterFileNameSuffix = "_master1200.jpg";
    static final String imgOriginalFileNameSuffix = ".jpg";
    private String homepageURL;

    public PixivParser(String homepageURL) {
        this.homepageURL = homepageURL;
    }

    private PixivIndexImageItem[] parseIndexImageIllusts(Document dom) {
        String fieldValue = dom.getElementById("init-config").val();
        JSONObject jsonObject = new JSONObject(fieldValue);
        JSONArray pixivItems = jsonObject.getJSONObject("pixivBackgroundSlideshow.illusts").getJSONArray("landscape");
        ArrayList<PixivIndexImageItem> result = new ArrayList<>();
        for (Object obj: pixivItems) {
            if(obj instanceof JSONObject) {
                JSONObject rawItem = (JSONObject) obj;
                PixivIndexImageItem newItem = new PixivIndexImageItem();
                newItem.author = rawItem.getString("user_name");
                newItem.fromURL = rawItem.getString("www_member_illust_medium_url");
                newItem.imgRawURL = rawItem.getJSONObject("url").getString("1200x1200");
                newItem.title = rawItem.getString("illust_title");
                newItem.userURL = rawItem.getString("www_user_url");
                newItem.imageURL = getImgURL(newItem);
                result.add(newItem);
            }
        }
        return result.toArray(new PixivIndexImageItem[0]);
    }

    @Override
    public ImageItem[] getImageItems(String rawData) {
        return parseIndexImageIllusts(Jsoup.parse(rawData));
    }

    @Override
    public ImageItem[] getImageItems() throws IOException {
        return getImageItems(catchNetworkData(homepageURL));
    }

    @Override
    public String getImgURL(ImageItem item) {
        StringBuilder str = new StringBuilder(item.getImgRawURL());
        int masterFlagIndex = str.indexOf(PixivParser.imgMasterFlag);
        str.replace(masterFlagIndex, masterFlagIndex + PixivParser.imgMasterFlag.length(), PixivParser.imgOriginalFlag);
        int masterFileNameSuffixIndex = str.indexOf(PixivParser.imgMasterFileNameSuffix);
        str.replace(masterFileNameSuffixIndex, masterFileNameSuffixIndex + PixivParser.imgMasterFileNameSuffix.length(), PixivParser.imgOriginalFileNameSuffix);
        return str.toString();
    }
}
