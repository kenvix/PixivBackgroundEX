package com.kenvix.pixiv.pixiv;

import com.kenvix.pixiv.driver.CommonParser;
import com.kenvix.pixiv.driver.ImageItem;
import com.kenvix.pixiv.pixiv.data.PixivIndexImageItem;
import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;

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
        String fieldValue = dom.getElementById("init-config").getNodeValue();
        jsonArray = JSONArray.fromObject(array);
    }

    @Override
    public ImageItem[] getImageItems(String rawData) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return parseIndexImageIllusts(builder.parse(new InputSource(new StringReader(rawData))));
    }

    @Override
    public ImageItem[] getImageItems() throws ParserConfigurationException, IOException, SAXException {
        return getImageItems(catchNetworkData(homepageURL));
    }

    @Override
    public String getImgURL(ImageItem item) {
        StringBuilder str = new StringBuilder(item.imgRawURL);
        int masterFlagIndex = str.indexOf(PixivParser.imgMasterFlag);
        str.replace(masterFlagIndex, masterFlagIndex + PixivParser.imgMasterFlag.length(), PixivParser.imgOriginalFlag);
        int masterFileNameSuffixIndex = str.indexOf(PixivParser.imgMasterFileNameSuffix);
        str.replace(masterFileNameSuffixIndex, masterFileNameSuffixIndex + PixivParser.imgMasterFileNameSuffix.length(), PixivParser.imgOriginalFileNameSuffix);
        return str.toString();
    }
}
