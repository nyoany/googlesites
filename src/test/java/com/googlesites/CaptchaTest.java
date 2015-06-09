package com.googlesites;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.sikuli.api.DesktopScreenRegion;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 *
 * @author Oana
 */
@Listeners({ScreenShotOnFailure.class})
public class CaptchaTest {

       static WebDriver driver = new WebDriverInstance().getCurrentDriverInstance();
       List<String> sitesURLs = new ArrayList<String>();
       Overview overview = new Overview(driver);
       LoginPage loginPage;
       Sites sites;
       Site site;
       CreateSitePage createSite;
       ConfirmationPopUp confirmation = new ConfirmationPopUp(driver);
       
       @BeforeClass
       public void before(){
       
       PageFactory.initElements(driver, CaptchaTest.class);
       sitesURLs.add("blanksitezzz");
       sitesURLs.add("intranetlintranetl111");
       
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
        Region r = new Region(new DesktopScreenRegion().getBounds());
        Pattern captcha = new Pattern("D:\\photos\\intranet.png");
        Match m = null;
        try {
            m = r.find(captcha);
        } catch (FindFailed ex) {
            Logger.getLogger(CreateSitePage.class.getName()).log(Level.SEVERE, null, ex);
            fail("The Intranet template page does not look like it should.");
        }
        assertNotNull(m, "The Intranet page is not displayed correctly.");
        assertTrue(m.getScore()>0.9, "The match is lower than 90%, the Intranet template page is not displayed correctly, it is : " + m.getScore() + ".");
       }
    
}
