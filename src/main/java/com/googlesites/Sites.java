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
public class Sites {

    private final WebDriver driver;
    private final String SELECTED_CLASS = "sites-dashboard-sidebar-current";
    private final String CREATE_BUTTON = "ownerCreateBtn";
    private final String OPTIONS_LIST_CLASS = "sites-dashboard-sidebar";
    private final String OPTION_XPATH = "//ul/li/a[contains(text(),'@text@')]";
    private final String SITES_LIST_CSS = "a[href*='/site/@siteURL@/']";
    private final String SITES_LIST = "div[class*='goog-ws-dash-yours goog-ws-dash-inside'] ul li";

    Sites(WebDriver driver) {
        this.driver = driver;
    }

    public void verifySideBarOptionIsSelected(String option) {

        String actualText = driver.findElement(By.className(SELECTED_CLASS)).getText();
        assertTrue(actualText.equals(option), "The selected option is : " + actualText);
    }

    public void verifyCreateButtonIsEnabled() {

        assertTrue(driver.findElement(By.id(CREATE_BUTTON)).isEnabled());
    }

    public void selectOption(String option) {

        driver.findElement(By.xpath(OPTION_XPATH.replace("@text@", option))).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    public CreateSitePage clickOnCreateButton() {

        driver.findElement(By.id(CREATE_BUTTON)).click();
        CreateSitePage csp = new CreateSitePage(driver);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        return csp;
    }

    public Site navigateToSite(String siteName) {

        String mainWindow = driver.getWindowHandle();
        driver.findElement(By.cssSelector(SITES_LIST_CSS.replace("@siteURL@", siteName))).click();

        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(mainWindow)) {
                driver.switchTo().window(handle);
            }
        }
        return new Site(driver);
    }

    public void verifyTheOnlySitesAre(List<String> sitesURLs) {

        int numberOfSites = driver.findElements(By.cssSelector(SITES_LIST)).size();
        assertEquals(sitesURLs.size(), numberOfSites, "Expected " + sitesURLs.size() + " but was " + numberOfSites + ".");

        for (String siteURL : sitesURLs) {

            assertTrue(driver.findElement(By.cssSelector(SITES_LIST_CSS.replace("@siteURL@", siteURL))).isDisplayed(), "The site with the URL "
                    + siteURL + " is not displayed.");
        }

    }
}
