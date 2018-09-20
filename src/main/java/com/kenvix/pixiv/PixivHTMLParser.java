package com.kenvix.pixiv;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;

class PixivHTMLParser {
    private Document dom;

    PixivHTMLParser(String html) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        dom = builder.parse(new InputSource(new StringReader(html)));
    }

    PixivHTMLParser(Document dom) {
        this.dom = dom;
    }
}
