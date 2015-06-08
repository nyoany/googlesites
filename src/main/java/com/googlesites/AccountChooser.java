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
public class AccountChooser {

    private final WebDriver driver;
    private static final String LIST_XPATH = "//ul[@id='account-list']/li";

    AccountChooser(WebDriver driver) {
        this.driver = driver;
    }

    public void verifyTheAccountsAre(List<String> accountList) {

        int actualAccountsNo = driver.findElements(By.xpath(LIST_XPATH)).size();
        assertEquals(actualAccountsNo, accountList.size(), "The number of accounts is " + actualAccountsNo + " not " + accountList.size());

        for (String account : accountList) {

            assertTrue(driver.findElement(By.xpath(LIST_XPATH + "[@id=account-" + account + "]")).isDisplayed(), "The account " + account + " is not displayed.");
        }

    }

    public void chooseAccount(String account) {

        driver.findElement(By.xpath(LIST_XPATH + "[@id=account-" + account + "]//button")).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

}
