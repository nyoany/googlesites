/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlesites;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.WebDriver;
import org.sikuli.api.DesktopScreenRegion;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

/**
 *
 * @author Oana
 */
public class Sikuli {

    public Match find(String path, String failMessage) {

        Region r = new Region(new DesktopScreenRegion().getBounds());
        Pattern captcha = new Pattern(path);
        Match m = null;
        try {
            m = r.find(captcha);
        } catch (FindFailed ex) {
            Logger.getLogger(Sikuli.class.getName()).log(Level.SEVERE, null, ex);
            fail("The " + failMessage + " has not been found on the current page.");
        }
        m.highlight(2);
        assertNotNull(m, "The " + failMessage + " is not displayed correctly.");
        assertTrue(m.getScore() > 0.7, "The match is lower than 90%, the " + failMessage + " is not displayed correctly, it is : " + m.getScore() * 100 + "% accurate.");
        return m;
    }

    public Match findAndClick(String path, String failMessage) {

        Match match = find(path, failMessage);

        if (match != null) {
            match.click();
        }
        return match;
    }

    public void findAndType(String path, String failMessage, String text, WebDriver driver) {
        find("D:\\photos\\popup.png", "pop up");
    }
}
