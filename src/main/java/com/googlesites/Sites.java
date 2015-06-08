/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlesites;

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
    private final String SITE_XPATH = "//a[contains(text(),'@text@')]";

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
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        return csp;
    }

    public Site navigateToSite(String siteName) {

        driver.findElement(By.xpath(SITE_XPATH.replace("@text@", siteName))).click();
        return new Site(driver);
    }
}
