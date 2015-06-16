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
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class ScreenShotOnFailure extends TestListenerAdapter {

    static final WebDriverInstance wdi = new WebDriverInstance();

    @Override
    public void onTestFailure(ITestResult result) {

        File scrFile = ((TakesScreenshot) wdi.getCurrentDriverInstance()).getScreenshotAs(OutputType.FILE);
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
    
    public void generateReport(List xmlSuites, List<ISuite> suites,
        String outputDirectory) {
        //Iterating over each suite included in the test
        for (ISuite suite : suites) {
            //Following code gets the suite name
            String suiteName = suite.getName();
	    //Getting the results for the said suite
	    Map<String, ISuiteResult> suiteResults = suite.getResults();
	    for (ISuiteResult sr : suiteResults.values()) {
	        ITestContext tc = sr.getTestContext();
	        System.out.println("Passed tests for suite '" + suiteName +
	             "' is:" + tc.getPassedTests().getAllResults().size());
	        System.out.println("Failed tests for suite '" + suiteName +
	             "' is:" + 
	             tc.getFailedTests().getAllResults().size());
	        System.out.println("Skipped tests for suite '" + suiteName +
	             "' is:" + 
	             tc.getSkippedTests().getAllResults().size());
	      }
        }
    }
}
