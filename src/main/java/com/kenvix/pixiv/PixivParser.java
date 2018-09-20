package com.kenvix.pixiv;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.*;
import org.xml.sax.InputSource;
import com.google.gson.Gson;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;

public class PixivParser {

    public static PixivHTMLParser getPixivHTMLParser(String html) throws ParserConfigurationException, IOException, SAXException {
        return new PixivHTMLParser(html);
    }

    public static PixivHTMLParser getPixivHTMLParser(Document dom) {
        return new PixivHTMLParser(dom);
    }
}
