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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

      WebElement emailEl =  driver.findElement(By.id("Email"));
      driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
       
        if(emailEl.getAttribute("value")!= null){
        emailEl.clear();
        }
        emailEl.sendKeys(email);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        String actualSetEmail = emailEl.getAttribute("value");
        if(!actualSetEmail.equals(email)){
        typeEmail(email);
        }
    }

    public void typePassword(String password) {

        WebElement pass = driver.findElement(By.id("Passwd"));
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
       
        if(pass.getAttribute("value")!= null){
        pass.clear();
        }
        pass.sendKeys(password);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        String actualSetPassword = pass.getAttribute("value");
        if(!actualSetPassword.equals(password)){
        typePassword(password);
        }
    }

    public Sites signIn() {

        driver.findElement(By.id("signIn")).click();
        Sites sites = new Sites(driver);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        return sites;
    }
    
    
    public Sites clickNext() {

        Sites sites = new Sites(driver);
        if(driver.findElement(By.id("next"))!= null){
        
        driver.findElement(By.id("next")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Passwd")));
       
        }
        return sites;
    }

    public Sites logIn(String email, String password) {

        typeEmail(email);
        clickNext();
        typePassword(password);
        return signIn();
    }
}
