/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.googlesites;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Keys;
/**
 *
 * @author Oana
 */
public class MapsPage {
   
   private final WebDriver driver;
   private static final String SEARCH_BOX = "input[aria-label*='Search']";
   private static final String CLOSE_BUTTON = "//span[aria-label*='Clear Search']/../";
   
    MapsPage(WebDriver driver) {
        this.driver = driver;
    }
    
    public void search(String location){
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(By.cssSelector(SEARCH_BOX)).sendKeys(location);
        driver.findElement(By.cssSelector(SEARCH_BOX)).sendKeys(Keys.ENTER);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
    
}
