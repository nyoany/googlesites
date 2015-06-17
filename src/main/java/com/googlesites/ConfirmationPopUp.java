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

/**
 *
 * @author Oana
 */
public class ConfirmationPopUp {

    private final WebDriver driver;
    private final String CONFIRMATION_POP_UP = "div[role*='alertdialog']";
    private final String CONFIRMATION_TITLE = "//span[contains(text(),'Confirm')]";
    private final String CONFIRMATION_TEXT = "//div[contains(text(),'Are you sure you want to discard your unsaved changes?')]";
    private final String CONFIRMATION_BUTTONS = "button[name*='@action@']";
    private final String DISCARD_CHANGES = "Discard changes";
    private final String CONTINUE = "Continue editing";

    ConfirmationPopUp(WebDriver driver) {
        this.driver = driver;

    }

    public void verifyTheConfirmationPopUpIsDisplayed() {

        assertTrue(driver.findElement(By.cssSelector(CONFIRMATION_POP_UP)).isDisplayed(), "The confirmation pop up is not displayed.");
        assertTrue(driver.findElement(By.xpath(CONFIRMATION_TITLE)).isDisplayed(), "The confirmation title is not displayed.");
        assertTrue(driver.findElement(By.xpath(CONFIRMATION_TEXT)).isDisplayed(), "The confirmation text is not displayed.");
        assertTrue(driver.findElement(By.cssSelector(CONFIRMATION_BUTTONS.replace("@action@", "ok"))).isDisplayed(), "The confirmation discard button is not displayed.");
        assertTrue(driver.findElement(By.cssSelector(CONFIRMATION_BUTTONS.replace("@action@", "cancel"))).isDisplayed(), "The confirmation continue button is not displayed.");
        String actualConfirmationText = driver.findElement(By.cssSelector(CONFIRMATION_BUTTONS.replace("@action@", "ok"))).getText();
        assertEquals(DISCARD_CHANGES, actualConfirmationText, "Expected text for confirmation : " + DISCARD_CHANGES + " but was : " + actualConfirmationText);
        String actualDiscardText = driver.findElement(By.cssSelector(CONFIRMATION_BUTTONS.replace("@action@", "cancel"))).getText();
        assertEquals(CONTINUE, actualDiscardText, "Expected text for confirmation : " + CONTINUE + " but was : " + actualDiscardText);

    }

    public void confirm() {

        driver.findElement(By.cssSelector(CONFIRMATION_BUTTONS.replace("@action@", "ok"))).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public void cancel() {

        driver.findElement(By.cssSelector(CONFIRMATION_BUTTONS.replace("@action@", "cancel"))).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
}
