package com.BriteERP.tests;

import com.BriteERP.pages.CRMpage;
import com.BriteERP.pages.LoginPage;
import com.BriteERP.utilities.BrowserUtils;
import com.BriteERP.utilities.ConfigurationReader;
import com.BriteERP.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class ExpectedValueTest extends TestBase {

    @BeforeMethod
    public void setUpMethod2() {
        driver.get(ConfigurationReader.get("urlqa1"));

        String username = ConfigurationReader.get("crm57");
        String password = ConfigurationReader.get("crm57password");

        LoginPage login = new LoginPage();
        login.username.sendKeys(username);
        login.password.sendKeys(password);
        login.submit.click();

    }

    @Test
    public void expctedValueTest() {
        BrowserUtils.waitFor(3);
        extentLogger = report.createTest("Positive login as Driver");
        CRMpage crm = new CRMpage();
        extentLogger.info("Clicking on the CRM Module");
        crm.crmTab.click();
        extentLogger.info("Clicking on the list view button");
        crm.listViewButton.click();
        extentLogger.info("Taking the expected value from list");
        List<WebElement> book = driver.findElements(By.xpath("//tbody/tr[3]/td"));
        String expectedRevenue = book.get(8).getText();
        extentLogger.info("Clicking on the pivot view button");
        crm.pivotSortButton.click();
        extentLogger.info("Expand total");
        crm.expandTotalButton.click();
        crm.expandTotalButton.click();
        crm.opportunityTotalButton.click();
        extentLogger.info("Taking the actual value of book");
        List<WebElement> book2 = driver.findElements(By.xpath("//tbody/tr[2]/td"));
        Assert.assertEquals(expectedRevenue, book2.get(1).getText());
        extentLogger.pass("PASSED");
    }
}
