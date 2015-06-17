/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlesites;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 *
 * @author Oana
 */
@Listeners({ScreenShotOnFailure.class})
public class MapsTests {
 
    Overview overview;
    LoginPage loginPage;
    Sites sites;
    MapsPage maps;
    Sikuli sikuli = new Sikuli();
    WebDriver driver;
    static final FirefoxProfile profile = new FirefoxProfile();
    
    
    @Test(priority = 1, groups = "sikuli")
    public void navigateToMaps(){
        driver = new FirefoxDriver(new FirefoxBinary(new File("D:\\firefox 24\\firefox.exe")), profile);
        driver.manage().window().maximize();
        WebDriverInstance wdi = new WebDriverInstance();
        wdi.setCurrentDriver(driver);
        overview = new Overview(driver);
        overview.navigateToOverviewPage();
        loginPage = overview.navigateToLogin();
        sites = loginPage.logIn("johnjjones02@gmail.com", "MyPasswordIsC0@l");
        maps = sites.navigateToMaps();
        
      
    }
    @Test(dependsOnMethods = {"navigateToMaps"}, groups = "sikuli")
    public void verifySearch(){
      
     sikuli.takeScreenshotTo("Iasi");
     sikuli.compareScreenWith("Iasi");   
     sikuli.findAndClose("GoogleMapsPopUp");
     maps.search("Iasi");
     driver.close();
    }
    
    @Test(dependsOnMethods = {"navigateToMaps"}, groups = "sikuli", enabled = false)
    public void verifyCorrectLocation(){
    
        sikuli.compareScreenWith("Iasi");
        driver.close();
    }
    
}
