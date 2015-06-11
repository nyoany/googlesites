/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlesites;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 *
 * @author Oana
 */
@Listeners({ScreenShotOnFailure.class})
public class IntranetTests {

    static WebDriver driver = new WebDriverInstance().getCurrentDriverInstance();
    Site site;
    LoginPage loginPage;
    Sites sites;
    Overview overview = new Overview(driver);
    IntranetPage intranet = new IntranetPage(driver);

    @BeforeClass
    public void before() {

        PageFactory.initElements(driver, IntranetTests.class);
    }

    @Test
    public void intranetTest() {

        Sikuli sikuli = new Sikuli();
        overview.navigateToOverviewPage();
        loginPage = overview.navigateToLogin();
        sites = loginPage.logIn("johnjjones02@gmail.com", "MyPasswordIsC0@l");
        site = sites.navigateToSite("intranetlintranetl111");
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
    }

    @AfterClass
    public void after() {

        driver.close();
    }

}
