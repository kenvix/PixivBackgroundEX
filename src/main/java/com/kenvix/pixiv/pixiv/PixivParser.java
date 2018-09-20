package com.kenvix.pixiv.pixiv;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;

public class PixivParser {
    static final String imgMasterFlag = "img-master";
    static final String imgOriginalFlag = "img-original";
    static final String imgMasterFileNameSuffix = "_master1200.jpg";
    static final String imgOriginalFileNameSuffix = ".jpg";

    public static PixivHTMLParser getPixivHTMLParser(String html) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return new PixivHTMLParser(builder.parse(new InputSource(new StringReader(html))));
    }

    public static PixivHTMLParser getPixivHTMLParser(Document dom) {
        return new PixivHTMLParser(dom);
    }
}
