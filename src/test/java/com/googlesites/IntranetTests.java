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
import static org.testng.Assert.assertEquals;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 *
 * @author Oana
 */
@Listeners({CaptureFailure.class})
public class IntranetTests {

    Site site;
    LoginPage loginPage;
    Sites sites;
    Overview overview;
    IntranetPage intranet;
    WebDriver driver;
    Sikuli sikuli = new Sikuli();
    FirefoxProfile profile = new FirefoxProfile();

    @DataProvider
    public static Object[][] intranetPages() {
        return new Object[][]{{"Documents", true}, {"Calendar", true}, {"Directory", true}, {"Discussion", true}, {"Announcements", true}, {"Sitemap", true}};
    }

    @Test(groups = "sikuli", enabled = false)
    public void intranetTest() {
        driver = new FirefoxDriver(new FirefoxBinary(new File("D:\\firefox 24\\firefox.exe")), profile);
        driver.manage().window().maximize();
        WebDriverInstance wdi = new WebDriverInstance();
        wdi.setCurrentDriver(driver);
        overview = new Overview(driver);
        overview.navigateToOverviewPage();
        loginPage = overview.navigateToLogin();
        sites = loginPage.logIn("johnjjones02@gmail.com", "MyPasswordIsC0@l");
        site = sites.navigateToSite("intranetlintranetl111");
        intranet = new IntranetPage(driver);
        intranet.navigateTo("Documents");
        sikuli.find("Documents.png", "Documents page", 0.7);
        intranet.navigateTo("Calendar");
        sikuli.find("Calendar.png", "Calendar page", 0.7);
        intranet.navigateTo("Directory");
        sikuli.find("Directory.png", "Directory page", 0.7);
        intranet.navigateTo("Discussion");
        sikuli.find("Discussion.png", "Discussion page", 0.7);
        intranet.navigateTo("Announcements");
        sikuli.find("Announcements.png", "Announcements page", 0.7);
        intranet.navigateTo("Sitemap");
        sikuli.find("Sitemap.png", "Sitemap page", 0.7);
        driver.close();
    }

    @Test(groups = "sikuli", priority = 1)
    public void navigateToIntranet() {

        driver = new FirefoxDriver(new FirefoxBinary(new File("D:\\firefox 24\\firefox.exe")), profile);
        driver.manage().window().maximize();
        WebDriverInstance wdi = new WebDriverInstance();
        wdi.setCurrentDriver(driver);
        overview = new Overview(driver);
        overview.navigateToOverviewPage();
        loginPage = overview.navigateToLogin();
        sites = loginPage.logIn("johnjjones02@gmail.com", "MyPasswordIsC0@l");
        site = sites.navigateToSite("intranetlintranetl111");
        intranet = new IntranetPage(driver);
    }

    @Test(groups = "sikuli", dataProvider = "intranetPages", priority = 2, dependsOnMethods = {"navigateToIntranet"})
    public void verifyIntranetPages(String pageName, boolean result) {

        intranet.navigateTo(pageName);
        assertEquals(sikuli.find(pageName + ".png", pageName + " page", 0.7) != null, result);
        if(pageName.equals("Sitemap")){
          driver.close();
        }
    }


}
