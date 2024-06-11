package com.bot;

public class Bot {

    public static String BOT_TOKEN;

    public static String BOT_USERNAME;

    static{
        BOT_TOKEN = PropertiesReader.getProperty("bot.token");
        BOT_USERNAME = PropertiesReader.getProperty("bot.username");

        if (BOT_TOKEN == null || BOT_USERNAME == null){
            System.exit(1);
        }
    }
}
