/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlesites;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
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

    public boolean find(String path, String failMessage, double score) {

        String photoPath = ""; 
          if(path.contains("screenshots")){
        photoPath = path;
        }
          else{
          photoPath = "D:\\photos\\" + path;
          }
        Region r = new Region(new DesktopScreenRegion().getBounds());
        Pattern pattern = new Pattern(photoPath);
        Match m = null;
        try {
            m = r.find(pattern);
        } catch (FindFailed ex) {
            Logger.getLogger(Sikuli.class.getName()).log(Level.SEVERE, null, ex);
            fail("The " + failMessage + " has not been found on the current page.");
            return false;
        }
       // m.highlight(2);
        assertNotNull(m, "The " + failMessage + " is not displayed correctly.");
        assertTrue(m.getScore() > score, "The match is lower than 70%, the " + failMessage + " is not displayed correctly, it is : " + m.getScore() * 100 + "% accurate.");
        return true;
    }

    public boolean findAndClick(String path, String failMessage) {

        return find(path, failMessage, 0.7);
    }

    public void takeScreenshotTo(String locationName){
     
        File outputfile = new File("D:\\de invatat\\licence\\screenshots\\" + locationName + ".png");
        if(!outputfile.exists() && !outputfile.isDirectory()){
        BufferedImage screenshot =  new DesktopScreenRegion().capture();
        try {  
            ImageIO.write(screenshot, "png", outputfile);
        } catch (IOException ex) {
            Logger.getLogger(Sikuli.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
    }
    
    public void compareScreenWith(String fileName){
    
        find("D:\\de invatat\\licence\\screenshots\\" + fileName + ".png", fileName + " page", 0.8);
    }
    
    public boolean findAndClose(String path){
    
        Region r = new Region(new DesktopScreenRegion().getBounds());
        Pattern pattern = new Pattern("D:\\photos\\" + path + ".png");
        Pattern closeButton  =  new Pattern("D:\\photos\\CloseButton.png");
        try {
            Match m = r.find(pattern);
            m.highlight(2);
            Match button  = m.find(closeButton);
            button.highlight(2);
            button.click();
        } catch (FindFailed ex) {
            
            return false;
        }
        return true;
    
    }
}
