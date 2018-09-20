package com.kenvix.pixiv;
import com.kenvix.pixiv.pixiv.PixivHTMLParser;
import com.kenvix.pixiv.pixiv.PixivParser;
import org.apache.commons.cli.*;

import java.net.URL;

public class Main {
    public static URL pixivURL;

    public static void main(String[] args) {
        try {
            Options ops = new Options();
            ops.addOption("h", "help",false, "Print help message");
            ops.addOption("i", "interval",true, "Interval(seconds) of check updates on pixiv");
            ops.addOption("n", "limit", true, "Once detected updates on pixiv, the limitation of the number of photos will be downloaded, 0 for infinite");
            ops.addOption("l", "life", true, "Once the number of downloaded photos reach this the number put in this argument, delete the old one, 0 for infinite");
            ops.addOption("u", "url", true, "Pixiv login page url. default https://www.pixiv.net/");
            String header = "Pixiv Background Downloader // Written by Kenvix";
            DefaultParser parser = new DefaultParser();
            CommandLine cmd = parser.parse(ops, args);
            if(cmd.hasOption("h")) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("pixivbg", header, ops, "", true);
                System.exit(0);
            }
            pixivURL = new URL(cmd.hasOption("u") ? cmd.getOptionValue("u") : "https://www.pixiv.net/");
            System.out.println(header);
            while (true)
                startPixiv();
        } catch (Exception ex) {
            System.err.println("ERROR: Unable to initialize!!!");
            System.err.println(ex.toString());
            System.exit(1);
        }
    }

    private static void startPixiv() {
        try {
            Downloader htmlDownloader = new Downloader(pixivURL);
            PixivHTMLParser parser = PixivParser.getPixivHTMLParser(htmlDownloader.catchAsString());

        } catch (Exception ex) {
            System.err.println("WARNING: Unexpected exception thrown. Trying to restart...");
            System.err.println(ex.toString());
        }
    }
}
