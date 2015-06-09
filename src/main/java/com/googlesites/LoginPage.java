/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlesites;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 *
 * @author Oana
 */
public class LoginPage {

    private final WebDriver driver;

    LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void typeEmail(String email) {

        driver.findElement(By.id("Email")).sendKeys(email);
    }

    public void typePassword(String password) {

        driver.findElement(By.id("Passwd")).sendKeys(password);
    }

    public Sites signIn() {

        driver.findElement(By.id("signIn")).click();
        Sites sites = new Sites(driver);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        return sites;
    }

    public Sites logIn(String email, String password) {

        typeEmail(email);
        typePassword(password);
        return signIn();
    }
}
