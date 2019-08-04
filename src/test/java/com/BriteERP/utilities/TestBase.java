package com.BriteERP.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class TestBase {
    protected WebDriver driver;
    protected Actions actions;
    protected WebDriverWait wait;
    protected ExtentReports report;
    protected ExtentHtmlReporter htmlReporter;
    protected ExtentTest extentLogger;


    @BeforeTest(alwaysRun = true)
    public void setUpTest() {
        // actual reporter
        report = new ExtentReports();
        // System.getProperty("user.dir") ---> get the path to current project
        // test-output --> folder in the current project, will be created by testng if
        // it does not already exist
        // report.html --> name of the report file
        String filePath = System.getProperty("user.dir") + "/test-output/" + LocalDate.now().format(DateTimeFormatter.ofPattern("MM_dd_yyyy")) + "/report.html";
        htmlReporter = new ExtentHtmlReporter(filePath);
        report.attachReporter(htmlReporter);

        htmlReporter.config().setReportName("BriteERP automated test reports");

        report.setSystemInfo("Environment", "urlQA1");
        report.setSystemInfo("OS", System.getProperty("os.name"));
        report.setSystemInfo("Browser", ConfigurationReader.get("browser"));
    }

    @AfterTest(alwaysRun = true)
    public void tearDownTest() { report.flush(); }


    @BeforeMethod(alwaysRun = true)
    public void setUpMethod() throws InterruptedException {
        // initilializes the webdriver object in test base class using the Driver utility
        driver = Driver.get();

        // setting implicit wait --> when elements not found, it will keep trying to find it for 10 seconds
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // set up the explicit wait object.
        wait = new WebDriverWait(driver, 10);

        // Actions class enable advanced interactions like double click, drag drop ...
        actions = new Actions(driver);

        // initilializes the webdriver object in test base class using the Driver utility
        driver = Driver.get();

        // setting implicit wait --> when elements not found, it will keep trying to find it for 10 seconds
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // set up the explicit wait object.
        wait = new WebDriverWait(driver, 10);

        // Actions class enable advanced interactions like double click, drag drop ...
        actions = new Actions(driver);


    }

    @AfterMethod(alwaysRun = true)
    public void tearDownMethod(ITestResult result) throws InterruptedException, IOException {
        // if the test failed
        if (result.getStatus() == ITestResult.FAILURE) {
            // record the failed test
            extentLogger.fail(result.getName());
            // take screen shot and add to report0
            String screenshotLocation = BrowserUtils.getScreenshot(result.getName());
            extentLogger.addScreenCaptureFromPath(screenshotLocation);
            // capture the exception
            extentLogger.fail(result.getThrowable());
        } else if (result.getStatus() == ITestResult.SKIP) {
            extentLogger.skip("Test case skipper: " + result.getName());
        }

        Thread.sleep(4000);
        Driver.closeDriver();
    }

}
