/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlesites;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 *
 * @author Oana
 */
public class Config {

    public static Map<String, String> properties = new HashMap<String, String>();

    private static void loadProperties() {

        if (properties.isEmpty()) {
            Properties prop = new Properties();
            InputStream input = null;

            try {

                input = new FileInputStream("config.properties");
		prop.load(input);

                properties.put("firefoxLocation", prop.getProperty("firefoxLocation"));
                properties.put("screenshotsLocation", prop.getProperty("screenshotsLocation"));
                properties.put("photosLocation", prop.getProperty("photosLocation"));
                properties.put("failedTestsScreenshotsLocation", prop.getProperty("failedTestsScreenshotsLocation"));

            } catch (IOException ex) {
            }
        }
    }

    public static String getPropertyValue(String propertyName) {

        if (properties.isEmpty()) {
            loadProperties();
        }
        return properties.get(propertyName);
    }
}
