/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlesites;

import java.io.File;
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

    public WebDriver getCurrentDriverInstance() {
        if (driver == null) {
            startDriver();
        }
        return driver;

    }

    public void startDriver() {
        driver = new FirefoxDriver(new FirefoxBinary(new File("D:\\firefox 24\\firefox.exe")), profile);
        driver.manage().window().maximize();
    }
}
