package com.BriteERP.step_definitions;

import com.BriteERP.utilities.BrowserUtils;
import com.BriteERP.utilities.ConfigurationReader;
import com.BriteERP.utilities.Driver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.gherkin.model.Feature;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class Hooks {
    protected WebDriver driver;
    protected Actions actions;
    protected WebDriverWait wait;
    protected ExtentReports report;
    protected ExtentHtmlReporter htmlReporter;
    protected ExtentTest extentLogger;

    @Before
    public void setUp() {
        Driver.get().get(ConfigurationReader.get("urlqa1"));
        Driver.get().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }

    @After
    public void tearDown(Scenario scenario) {
        // check if the scenario is failed
        if (scenario.isFailed()) {
            // takes a screenshot
            try{
                BrowserUtils.getScreenshot(scenario.getName().replace(" ",""));
            }catch (IOException io){
                System.out.println("Failed to get screenshot after scenario: "+scenario.getName()+
                        "\n"+scenario.getSourceTagNames());
                io.printStackTrace();
            }
        }
        BrowserUtils.sendEmailReport(ConfigurationReader.get("manager23Email"), scenario);
        BrowserUtils.cleanDirectory(ConfigurationReader.get("ScreenshotDIR"));
        System.out.println(scenario.getName());
        Driver.closeDriver();
    }


}
