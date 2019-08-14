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
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class sumOfRavenues extends TestBase {

@BeforeMethod
    public void SetUp(){
    driver.get(ConfigurationReader.get("urlqa2"));
}

@Test
    public void sums(){
    extentLogger = report.createTest("sumOfRevenues");
    //driver.findElement(By.xpath("//*[@id='top_menu']/li[10]/a/b")).click();
    BrowserUtils.waitFor(5);
    LoginPage log = new LoginPage();
    log.sign_in.click();
    extentLogger.info("Logging in as event manager");
    String username = ConfigurationReader.get("crm57");
    String password = ConfigurationReader.get("crm57password");
    log.login(username, password);
    BrowserUtils.waitFor(5);
    extentLogger.info("Navigating to CRM page");
    CRMpage crm = new CRMpage();
    crm.crmTab.click();
    BrowserUtils.waitFor(5);
    crm.pivotSortButton.click();

    BrowserUtils.waitFor(5);
    crm.totsExpButton.click();
    crm.tots2.click();

    crm.dropOportunities.click();
    BrowserUtils.waitFor(5);
    extentLogger.info("Verifying the total sum with actual sum of all items");

    double expectedSum= Double.valueOf(crm.sumRavenues.get(0).getText().replace(",",""));
    double actual =0.0;
    crm.sumRavenues.remove(0);

    for (WebElement each : crm.sumRavenues) {
        actual+=Double.valueOf(each.getText().replace(",",""));
        System.out.println(actual);}
    Assert.assertEquals(actual, expectedSum);
    extentLogger.pass("PASSED");

}
}
