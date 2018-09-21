package com.kenvix.pixiv;
import com.kenvix.pixiv.pixiv.PixivHTMLParser;
import com.kenvix.pixiv.pixiv.PixivParser;
import com.kenvix.pixiv.pixiv.data.*;
import org.apache.commons.cli.*;

public class Main {
    private static String homepageURL;

    public static void main(String[] args) {
        try {
            Options ops = new Options();
            ops.addOption("p", "path",false, "Path to save downloaded photos");
            ops.addOption("h", "help",false, "Print help message");
            ops.addOption("i", "interval",true, "Interval(seconds) of check updates on pixiv");
            ops.addOption("n", "limit", true, "Once detected updates on pixiv, the limitation of the number of photos will be downloaded, 0 for infinite");
            ops.addOption("l", "life", true, "Once the number of downloaded photos reach this the number put in this argument, delete the old one, 0 for infinite");
            ops.addOption("u", "url", true, "Page url of images. default https://www.pixiv.net/ for pixiv driver");
            ops.addOption("d", "driver", true, "Driver: pixiv / bing");
            String header = "Pixiv/Bing Background Downloader // Written by Kenvix";
            DefaultParser parser = new DefaultParser();
            CommandLine cmd = parser.parse(ops, args);
            if(cmd.hasOption("h")) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("pixivbg", header, ops, "", true);
                System.exit(0);
            }
            homepageURL = cmd.hasOption("u") ? cmd.getOptionValue("u") : "https://www.pixiv.net/";
            System.out.println(header);
            while (true)
                startPixiv(cmd.hasOption("p") ? cmd.getOptionValue("p") : "downloads");
        } catch (Exception ex) {
            System.err.println("ERROR: Unable to initialize!!!");
            System.err.println(ex.toString());
            System.exit(1);
        }
    }

    private static void startPixiv(String savePath) {
        try {
            Downloader htmlDownloader = new Downloader(homepageURL);
            PixivHTMLParser parser = PixivParser.getPixivHTMLParser(htmlDownloader.catchAsString());
            PixivIndexImageIllusts illusts = parser.parseIndexImageIllusts();
            for (PixivIndexImageItem item: illusts.landscape) {
                String originalUrl = parser.getImgURL(item);

            }
        } catch (Exception ex) {
            System.err.println("WARNING: Unexpected exception thrown. Trying to restart...");
            System.err.println(ex.toString());
        }
    }
}
