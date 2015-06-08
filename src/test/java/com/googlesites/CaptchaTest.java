package com.googlesites;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 *
 * @author Oana
 */
@Listeners({ScreenShotOnFailure.class})
public class CaptchaTest {

       static WebDriver driver = new WebDriverInstance().getCurrentDriverInstance();
       LoginPage loginPage;
       Sites sites;
       Site site;
       CreateSitePage createSite;
       
      @Test
      public void createSite(){
      PageFactory.initElements(driver,CaptchaTest.class);
      Overview overview = new Overview(driver);
      overview.navigateToOverviewPage();
      loginPage = overview.navigateToLogin();
      sites = loginPage.logIn("johnjjones02@gmail.com","MyPasswordIsC0@l");
      createSite = sites.clickOnCreateButton();
      createSite.createSite("Gollum1124", "siteyy1");
      }  
    
}
