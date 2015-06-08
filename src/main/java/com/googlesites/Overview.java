/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlesites;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static org.testng.Assert.*;

/**
 *
 * @author Oana
 */
public class Overview {

    private final WebDriver driver;
    static final String LINKS_XPATH = "//a[text(contains(@text@))]";
    static final String OVERVIEW_LINK = "http://www.google.com/sites/overview.html";
    static final String SITES_LINK = "http://sites.google.com";
    static final String LOGINPAGECHECK = "gaia_loginform";

    Overview(WebDriver driver) {
        this.driver = driver;
    }

    public void verifySiteTypesAreDisplayed(List<String> siteTypes) {

        for (String type : siteTypes) {
            assertTrue(driver.findElement(By.xpath(LINKS_XPATH.replace("@text@", type))).isDisplayed(), "The site type " + type + " is not displayed.");
        }
    }

    public void navigateToOverviewPage() {

        driver.get(OVERVIEW_LINK);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    public void navigateToAccounts() {

        assertNotNull(driver.findElement(By.cssSelector("a[href='" + SITES_LINK + "']")), "The Google Sites icon is not displayed.");
        driver.findElement(By.cssSelector("a[href='" + SITES_LINK + "']")).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        assertTrue(driver.findElement(By.id(LOGINPAGECHECK)).isDisplayed(), "The navigation to accounts chooser did not take place.");
    }

    public LoginPage navigateToLogin() {

        assertNotNull(driver.findElement(By.cssSelector("a[href='" + SITES_LINK + "']")), "The Google Sites icon is not displayed.");
        driver.findElement(By.cssSelector("a[href='" + SITES_LINK + "']")).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        return new LoginPage(driver);
    }
}
