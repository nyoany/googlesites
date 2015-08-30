/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlesites;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 *
 * @author Oana
 */
public class IntranetPage {

    private final WebDriver driver;
    private final String INTRANET_LINK = "a[href='https://sites.google.com/site/intranetlintranetl111/']";
    private final String NAVIGATION_LINKS = "a[href='/site/intranetlintranetl111/@link@']";
    private final String BUTTON_CSS = "div[id='sites-file-cabinet-@buttontype@-btn']";
    private final String COMMENT_TEXTAREA = "textarea[aria-label='Comment draft']";
    private final String POST_COMMENT = "div[title='Post comment']";
    private final String CURRENT_DATE = "currentDate1";
    private final String RETURN_IN_TIME = "navBack1";
    private final String GO_TO_FUTURE = "navForward1";
    private final String POSTED_COMMENT = "//div[contains(text(), '@comment@')]";

    IntranetPage(WebDriver driver) {
        this.driver = driver;
    }

    public void verifySiteNameIs(String siteName) {

        String actualSiteName = driver.findElement(By.cssSelector(INTRANET_LINK)).getText();
        assertEquals(siteName, actualSiteName, "The site name is not " + siteName + " it is " + actualSiteName);
    }

    public void navigateTo(String location) {

        if (location.equals("Sitemap")) {
            location = "system/app/pages/sitemap/hierarchy";
        }
        driver.findElement(By.cssSelector(NAVIGATION_LINKS.replace("@link@", location.toLowerCase()))).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public void addComment(String comment) {

        WebElement commentSection = driver.findElement(By.cssSelector(COMMENT_TEXTAREA));
        commentSection.click();
        commentSection.sendKeys(comment);
        driver.findElement(By.cssSelector(POST_COMMENT)).click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        assertNotNull(driver.findElement(By.xpath(POSTED_COMMENT.replace("@comment@", comment))), "The added comment is not displayed.");
    }

    public void verifyDateIs(String date) {

        String actualDisplayedDate = driver.findElement(By.cssSelector(".date-top")).getText();
        assertEquals(date, actualDisplayedDate, "The date shown is not " + date + " it is " + actualDisplayedDate);
    }

    public void goToNextMonth() {

        driver.findElement(By.id(GO_TO_FUTURE)).click();
    }

    public void goToPreviousMonth() {

        driver.findElement(By.id(RETURN_IN_TIME)).click();
    }
}
