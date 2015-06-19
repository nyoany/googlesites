/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlesites;

import java.io.File;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

/**
 *
 * @author Oana
 */
public class WebDriverInstance {

    static final FirefoxProfile profile = new FirefoxProfile();
    static WebDriver driver;

    public static WebDriver getCurrentDriverInstance() {
        if (driver == null) {
            return startDriver();
        }
        return driver;

    }

    public static WebDriver startDriver() {
        driver = new FirefoxDriver(new FirefoxBinary(new File("D:\\firefox 24\\firefox.exe")), profile);
        return driver;
    }

    public void setCurrentDriver(WebDriver driver) {

        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        new Sikuli().focusOnBrowser();
    }

    public WebDriver getDriver() {

        return driver;
    }
}
