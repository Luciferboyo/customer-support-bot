package com.bot;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
    public static String getProperty(String propertyKey){

        Properties properties = new Properties();

        try (InputStream inputStream = PropertiesReader.class.getClassLoader().getResourceAsStream("application.properties")){
            if (inputStream != null){
                properties.load(inputStream);
                return properties.getProperty(propertyKey);
            }else {
                return null;
            }

        }catch (IOException ioException){
            ioException.printStackTrace();
            return null;
        }
    }
}
