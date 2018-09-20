package com.kenvix.pixiv.pixiv;

import com.google.gson.*;
import com.kenvix.pixiv.pixiv.data.PixivIndexImageIllusts;
import com.kenvix.pixiv.pixiv.data.PixivIndexImageItem;
import org.w3c.dom.*;

public class PixivHTMLParser {
    private Document dom;

    PixivHTMLParser(Document dom) {
        this.dom = dom;
    }

    public PixivIndexImageIllusts parseIndexImageIllusts() {
        String fieldValue = dom.getElementById("init-config").getNodeValue();
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(fieldValue, PixivIndexImageIllusts.class);
    }

    public String getOriginalImgURL(PixivIndexImageItem item) {
        StringBuilder str = new StringBuilder(item.url.master);
        int masterFlagIndex = str.indexOf(PixivParser.imgMasterFlag);
        str.replace(masterFlagIndex, masterFlagIndex + PixivParser.imgMasterFlag.length(), PixivParser.imgOriginalFlag);
        int masterFileNameSuffixIndex = str.indexOf(PixivParser.imgMasterFileNameSuffix);
        str.replace(masterFileNameSuffixIndex, masterFileNameSuffixIndex + PixivParser.imgMasterFileNameSuffix.length(), PixivParser.imgOriginalFileNameSuffix);
        return str.toString();
    }
}
