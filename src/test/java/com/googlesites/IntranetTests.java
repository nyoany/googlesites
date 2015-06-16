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
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
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
        sikuli.find("D:\\photos\\Documents.png", "Documents page");
        intranet.addComment("Nice site!");
        intranet.navigateTo("Calendar");
        sikuli.find("D:\\photos\\Calendar.png", "Calendar page");
        intranet.navigateTo("Directory");
        sikuli.find("D:\\photos\\Directory.png", "Directory page");
        intranet.navigateTo("Discussion");
        sikuli.find("D:\\photos\\Discussion.png", "Discussion page");
        intranet.navigateTo("Announcements");
        sikuli.find("D:\\photos\\Announcements.png", "Announcements page");
        intranet.navigateTo("Resources");
        sikuli.find("D:\\photos\\Resources.png", "Resources page");
        intranet.navigateTo("Contact");
        sikuli.find("D:\\photos\\Contact.png", "Contact page");
        intranet.navigateTo("Sitemap");
        sikuli.find("D:\\photos\\Sitemap.png", "Sitemap page");
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
        sikuli.find("D:\\photos\\intranet.png","Intranet template page");
        driver.close();
       }

}
