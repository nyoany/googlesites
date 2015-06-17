/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlesites;

/**
 *
 * @author Oana
 */
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static org.testng.Assert.*;

public class Site {

    private final WebDriver driver;
    private final String SITE_URL = "https://sites.google.com/site/@siteName@/";
    private final String SELECTED_PAGE = "//div[contains(text(),'@text@')]";
    private final String ENABLED_PAGE = "//a[contains(text(), '@text@')]";
    private final String BLANK_SITE_PAGE_TITLE = "sites-page-title";
    private final String MAIN_CONTENT = "//div[@id='sites-canvas-main-content']/table/tbody/tr/td/div";
    private final String EDIT_BUTTON = "edit-start-btn";
    private final String EDIT_PAGE_TITLE = "input[aria-label*='Page title']";
    private final String SAVE_CANCEL_BUTTONS = "sites-editor-button-sites-@buttonName@";
    private final String EDITABLE_CONTENT = "sites-tile-name-content-1-editing-editable-content";
    private final String ACCOUNT_CHOOSER_IDENTIFIER = "accountchooser-title";

    Site(WebDriver driver) {
        this.driver = driver;
    }

    public Object navigateToSiteWithURL(String URL) {

        driver.get(SITE_URL.replace("@siteName@", URL));
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        if (driver.findElement(By.id(ACCOUNT_CHOOSER_IDENTIFIER)) != null) {
            return new AccountChooser(driver);
        }
        return new LoginPage(driver);
    }

    public void verifyDefaultNavigationLeftToolbar() {

        assertTrue(driver.findElement(By.xpath(SELECTED_PAGE.replace("@text@", "Home"))).isDisplayed(), "The Home page is not selected by default.");
        assertTrue(driver.findElement(By.xpath(ENABLED_PAGE.replace("@text@", "Sitemap"))).isDisplayed(), "The Sitemap page is not enabled for selection by default.");
    }

    public void verifyBlankSiteTitleIs(String title) {

        String actualPageTitle = driver.findElement(By.id(BLANK_SITE_PAGE_TITLE)).getText();
        assertEquals(title, actualPageTitle, "Expected the page title to be " + title + " but was " + actualPageTitle);
    }

    public void verifyTheMainContentHasDisplayed(String text) {

        String actualContent = driver.findElement(By.xpath(MAIN_CONTENT)).getText();
        assertEquals(actualContent, text, "Expected content : " + text + " but found " + actualContent);
    }

    public void clickOnEditButton() {

        driver.findElement(By.id(EDIT_BUTTON)).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public void clickOnButton(String buttonLabel) {

        driver.findElement(By.id(SAVE_CANCEL_BUTTONS.replace("@buttonName@", buttonLabel.toLowerCase()))).click();
        if (buttonLabel.equals("Cancel")) {

        }
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public void changeThePageTitleTo(String newTitle) {

        driver.findElement(By.cssSelector(EDIT_PAGE_TITLE)).clear();
        driver.findElement(By.cssSelector(EDIT_PAGE_TITLE)).sendKeys(newTitle);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public void changePageContentTo(String newContent) {

        driver.findElement(By.id(EDITABLE_CONTENT)).clear();
        driver.findElement(By.id(EDITABLE_CONTENT)).sendKeys(newContent);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

}
