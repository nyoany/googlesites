package com.googlesites;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 *
 * @author Oana
 */
@Listeners({ScreenShotOnFailure.class})
public class CaptchaTest {

       static WebDriver driver;
       List<String> sitesURLs = new ArrayList<String>();
       Overview overview;
       LoginPage loginPage;
       Sites sites;
       Site site;
       CreateSitePage createSite;
       ConfirmationPopUp confirmation;
       Sikuli sikuli = new Sikuli();
       
       @BeforeClass
       public void before(){
       FirefoxProfile profile = new FirefoxProfile();
       driver = new FirefoxDriver(new FirefoxBinary(new File("D:\\firefox 24\\firefox.exe")), profile);
       PageFactory.initElements(driver, CaptchaTest.class);
       overview = new Overview(driver);
       confirmation = new ConfirmationPopUp(driver);
       sitesURLs.add("blanksitezzz");
       sitesURLs.add("intranetlintranetl111");
       driver.manage().window().maximize();
       }
         
      @Test(priority = 1)
      public void createSite(){
     
      overview.navigateToOverviewPage();
      loginPage = overview.navigateToLogin();
      sites = loginPage.logIn("johnjjones02@gmail.com","MyPasswordIsC0@l");
      sites.verifyTheOnlySitesAre(sitesURLs);
      createSite = sites.clickOnCreateButton();
      createSite.createSite("Gollum1124", "siteyy1");
      } 
      
      @Test(priority = 2)
      public void blankSiteTest(){
          
      overview.navigateToOverviewPage();
      overview.navigateToLogin();
      site = sites.navigateToSite("blanksitezzz");
      site.verifyBlankSiteTitleIs("Home");
      site.verifyDefaultNavigationLeftToolbar();
      site.verifyTheMainContentHasDisplayed("Hello to my Google site page!!");
      site.clickOnEditButton();
      site.changeThePageTitleTo("New title");
      site.clickOnButton("Cancel");
      confirmation.verifyTheConfirmationPopUpIsDisplayed();
      confirmation.confirm();
      site.verifyBlankSiteTitleIs("Home");
      site.verifyTheMainContentHasDisplayed("Hello to my Google site page!!");
      site.clickOnEditButton();
      site.changeThePageTitleTo("New title");
      site.clickOnButton("Save");
      site.verifyBlankSiteTitleIs("New title");
      site.clickOnEditButton();
      site.changeThePageTitleTo("Home");
      site.clickOnButton("Save");
      site.verifyBlankSiteTitleIs("Home");
      }      
      
       @Test(priority = 3)
       public void verifyDefaultIntranetTemplate(){
       
        overview.navigateToOverviewPage();
        overview.navigateToLogin();
        site = sites.navigateToSite("intranetlintranetl111");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        sikuli.find("D:\\photos\\intranet.png","Intranet template page");
       
       }
    
       
       
       @AfterClass
       public void after(){
       
           driver.close();
       }
}
