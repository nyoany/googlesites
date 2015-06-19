/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlesites;

import static com.googlesites.WebDriverInstance.profile;
import java.io.File;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 *
 * @author Oana
 */
@Listeners({CaptureFailure.class})
public class SanityTests {

    Site site;
    LoginPage loginPage;
    Sites sites;
    WebDriver driver;
    static final FirefoxProfile profile = new FirefoxProfile();

    @BeforeClass
    public void before() {

        driver = new FirefoxDriver(new FirefoxBinary(new File("D:\\firefox 24\\firefox.exe")), profile);
        driver.manage().window().maximize();
        WebDriverInstance wdi = new WebDriverInstance();
        wdi.setCurrentDriver(driver);
    }

    @Test(priority = 1)
    public void blankSiteTest() {
        Overview overview = new Overview(driver);
        overview.navigateToOverviewPage();
        loginPage = overview.navigateToLogin();
        sites = loginPage.logIn("johnjjones02@gmail.com", "MyPasswordIsC0@l");
        site = sites.navigateToSite("blanksitezzz");
        site.verifyBlankSiteTitleIs("Home");
        site.verifyDefaultNavigationLeftToolbar();
        site.verifyTheMainContentHasDisplayed("Hello to my Google site page!!");

    }

    @Test(priority = 2, dependsOnMethods = {"blankSiteTest"})
    public void verifyCancel() {

        ConfirmationPopUp confirmation = new ConfirmationPopUp(driver);
        site.clickOnEditButton();
        site.changeThePageTitleTo("New title");
        site.clickOnButton("Cancel");
        confirmation.verifyTheConfirmationPopUpIsDisplayed();
        confirmation.confirm();
        site.verifyBlankSiteTitleIs("Home");
        site.verifyTheMainContentHasDisplayed("Hello to my Google site page!!");

    }

    @Test(priority = 3, dependsOnMethods = {"blankSiteTest", "verifyCancel"})
    public void verifySave() {

        site.clickOnEditButton();
        site.changeThePageTitleTo("New title");
        site.clickOnButton("Save");
        site.verifyBlankSiteTitleIs("New title");
        site.clickOnEditButton();
        site.changeThePageTitleTo("Home");
        site.clickOnButton("Save");
        site.verifyBlankSiteTitleIs("Home");

    }

    @AfterClass
    public void after() {

        driver.close();
    }

}
