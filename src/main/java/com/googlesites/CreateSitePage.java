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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.sikuli.api.DesktopScreenRegion;
import org.sikuli.script.*;
import static org.testng.Assert.*;

public class CreateSitePage {

    // private final WebDriver driver;
    private final WebDriver driver;
    private final String TEMPLATES_CONTAINER = "sites-create-site-select-template-radiogroup";
    private final String SITE_NAME_ID = "siteTitle";
    private final String SITE_URL_ID = "siteName";
    private final String THEME_EXPANDER = "sites-create-site-select-theme-header";
    private final String THEME_SELECTOR = "theme-";
    private final String DESCRIPTION = "settingsSiteDesc";
    static final String SITES_LINK = "https://sites.google.com/";

    CreateSitePage(WebDriver driver) {
        this.driver = driver;
    }

    public void verifyTemplatesAreDisplayed() {

        assertTrue(driver.findElement(By.id(TEMPLATES_CONTAINER)).isDisplayed(), "The templates are not displayed.");
    }

    public void nameTheSite(String siteName) {

        driver.findElement(By.id(SITE_NAME_ID)).sendKeys(siteName);
    }

    public void nameTheURL(String siteUrl) {

        driver.findElement(By.id(SITE_URL_ID)).clear();
        driver.findElement(By.id(SITE_URL_ID)).sendKeys(siteUrl);
    }

    public void expandThemes() {

        driver.findElement(By.id(THEME_EXPANDER)).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    public void selectTheme(String theme) {

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,250)", "");
        driver.findElement(By.id(THEME_SELECTOR + theme.toLowerCase())).click();

    }

    public void writeDescription(String description) {
        driver.findElement(By.id("sites-create-site-description")).click();
        driver.findElement(By.id(DESCRIPTION)).sendKeys(description);
    }

    public void clickOnCapcha() {

        Sikuli sikuli = new Sikuli();
        sikuli.findAndClick("captcha.png", "captcha icon");
    }

    public Match verifyNotRobotDisplayed() throws FindFailed {

        Region r = new Region(new DesktopScreenRegion().getBounds());
        Pattern notRobot = new Pattern("D:\\photos\\not robot.png");
        return r.find(notRobot);
    }

    public void clickOnReturnButton() {

        driver.findElement(By.xpath("//a[@href='" + SITES_LINK + "']")).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    public void clickOnCreate() {

        driver.findElement(By.id("createBtn")).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    public void createSite(String siteName, String url) {

        nameTheSite(siteName);
        nameTheURL(url);
        clickOnCapcha();
    }
}
