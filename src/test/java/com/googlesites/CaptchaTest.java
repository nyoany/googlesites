package com.googlesites;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class CaptchaTest {

    List<String> sitesURLs = new ArrayList<String>();
    Overview overview;
    LoginPage loginPage;
    Sites sites;
    Site site;
    CreateSitePage createSite;
    Sikuli sikuli = new Sikuli();
    WebDriver driver;

    @Test(groups = "sikuli")
    public void createSite() {

        FirefoxProfile profile = new FirefoxProfile();
        driver = new FirefoxDriver(new FirefoxBinary(new File("D:\\firefox 24\\firefox.exe")), profile);
        WebDriverInstance wdi = new WebDriverInstance();
        wdi.setCurrentDriver(driver);
        driver.manage().window().maximize();
        sitesURLs.add("blanksitezzz");
        sitesURLs.add("intranetlintranetl111");
        sitesURLs.add("alf12opitds");
        overview = new Overview(driver);
        overview.navigateToOverviewPage();
        loginPage = overview.navigateToLogin();
        sites = loginPage.logIn("johnjjones02@gmail.com", "MyPasswordIsC0@l");
        sites.verifyTheOnlySitesAre(sitesURLs);
        createSite = sites.clickOnCreateButton();
        createSite.createSite("Gollum1124", "siteyy1");
        driver.close();
    }

}
