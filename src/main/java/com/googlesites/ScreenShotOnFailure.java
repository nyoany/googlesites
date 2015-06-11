/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlesites;

/**
 *
 * @author P3700482
 */
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class ScreenShotOnFailure extends TestListenerAdapter {

    static final WebDriverInstance wdi = new WebDriverInstance();
    static WebDriver driver = wdi.getCurrentDriverInstance();

    @Override
    public void onTestFailure(ITestResult result) {

        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy__hh_mm_ssaa");
        String destDir = "D:\\de invatat\\workspace\\testsOutput\\";
        new File(destDir).mkdirs();
        String destFile = result.getMethod().getMethodName() + dateFormat.format(new Date()) + ".png";

        try {
            FileUtils.copyFile(scrFile, new File(destDir + destFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
