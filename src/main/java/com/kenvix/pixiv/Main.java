package com.kenvix.pixiv;
import com.kenvix.pixiv.driver.ImageItem;
import com.kenvix.pixiv.pixiv.PixivDriver;
import org.apache.commons.cli.*;

import java.sql.SQLException;
import java.util.Properties;

public class Main {
    private static String homepageURL;

    public static void main(String[] args) {
        try {
            CommandLine cmd = getCmd(args);
            System.out.println(getHeader());
            if(cmd.hasOption("proxy-type")) {
                Properties sysProperties = System.getProperties();
                sysProperties.setProperty("proxySet", "true");
                switch (cmd.getOptionValue("proxy-type")) {
                    case "socks":
                    case "socks5":
                        System.setProperty("proxySet", "true");
                        System.setProperty("socksProxyHost", cmd.getOptionValue("proxy-host"));
                        System.setProperty("socksProxyPort", cmd.getOptionValue("proxy-port"));
                        System.out.println("With socks proxy " + System.getProperty("socksProxyHost") + ":" + System.getProperty("socksProxyPort"));
                        break;

                    case "http":
                        sysProperties.setProperty("proxySet", "true");
                        sysProperties.setProperty("http.proxyHost", cmd.getOptionValue("proxy-host"));
                        sysProperties.setProperty("http.proxyPort", cmd.getOptionValue("proxy-port"));
                        System.out.println("With http proxy " + System.getProperty("http.proxyHost") + ":" + System.getProperty("http.proxyPort"));
                        break;
                }
            }
            homepageURL = cmd.hasOption("u") ? cmd.getOptionValue("u") : "https://www.pixiv.net/";
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
            PixivDriver driver = new PixivDriver(homepageURL);
            ImageItem[] items = driver.getItemsFromSite();
            for (ImageItem item : items) {
                System.out.println(item.getImageURL());
            }
        } catch (SQLException sqlex) {
            System.err.println("ERROR: Database fault.");
            sqlex.printStackTrace();
            sleep(1500);
        } catch (Exception ex) {
            System.err.println("ERROR: Unexpected exception thrown. Trying to restart...");
            ex.printStackTrace();
            sleep(1500);
        }
    }

    private static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception ex) {}
    }

    private static String getHeader() {
        return "Pixiv/Bing Background Downloader // Written by Kenvix";
    }

    private static CommandLine getCmd(String[] args) throws ParseException {
        Options ops = new Options();
        ops.addOption("p", "path",false, "Path to save downloaded photos");
        ops.addOption("h", "help",false, "Print help message");
        ops.addOption("i", "interval",true, "Interval(seconds) of check updates on pixiv");
        ops.addOption("n", "limit", true, "Once detected updates on pixiv, the limitation of the number of photos will be downloaded, 0 for infinite");
        ops.addOption("l", "life", true, "Once the number of downloaded photos reach this the number put in this argument, delete the old one, 0 for infinite");
        ops.addOption("u", "url", true, "Page url of images. default https://www.pixiv.net/ for pixiv driver");
        ops.addOption("d", "driver", true, "Driver: pixiv / bing");
        ops.addOption(Option.builder().longOpt("proxy-type").desc("socks or http. If you want to set a proxy for Internet connection, use this argument.").hasArg().build());
        ops.addOption(Option.builder().longOpt("proxy-host").desc("host of your proxy if you want to use proxy").hasArg().build());
        ops.addOption(Option.builder().longOpt("proxy-port").desc("port of your proxy if you want to use proxy").hasArg().build());
        DefaultParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(ops, args);
        if(cmd.hasOption("h")) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("pixivbg", getHeader(), ops, "", true);
            System.exit(0);
        }
        return cmd;
    }
}
