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
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 *
 * @author Oana
 */
@Listeners({ScreenShotOnFailure.class})
public class IntranetTests {

    Site site;
    LoginPage loginPage;
    Sites sites;
    Overview overview;
    Sikuli sikuli = new Sikuli();
    FirefoxProfile profile = new FirefoxProfile();


    @Test(groups = "sikuli")
    public void intranetTest() {
        WebDriver driver = new FirefoxDriver(new FirefoxBinary(new File("D:\\firefox 24\\firefox.exe")), profile);
        driver.manage().window().maximize();
        WebDriverInstance wdi = new WebDriverInstance();
        wdi.setCurrentDriver(driver);
        overview = new Overview(driver);
        overview.navigateToOverviewPage();
        loginPage = overview.navigateToLogin();
        sites = loginPage.logIn("johnjjones02@gmail.com", "MyPasswordIsC0@l");
        site = sites.navigateToSite("intranetlintranetl111");
        IntranetPage intranet = new IntranetPage(driver);
        intranet.navigateTo("Documents");
        sikuli.find("Documents.png", "Documents page", 0.7);
        intranet.addComment("Nice site!");
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
 
    @Test(groups = "sikuli")
     public void verifyDefaultIntranetTemplate(){
        WebDriver driver = new FirefoxDriver(new FirefoxBinary(new File("D:\\firefox 24\\firefox.exe")), profile);
        driver.manage().window().maximize();
        WebDriverInstance wdi = new WebDriverInstance();
        wdi.setCurrentDriver(driver);
        overview = new Overview(driver);
        overview.navigateToOverviewPage();
        loginPage = overview.navigateToLogin();
        sites = loginPage.logIn("johnjjones02@gmail.com", "MyPasswordIsC0@l");
        site = sites.navigateToSite("intranetlintranetl111");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        sikuli.find("intranet.png","Intranet template page", 0.7);
        driver.close();
       }

}
