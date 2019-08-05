package com.BriteERP.tests;

import com.BriteERP.pages.CRMpage;
import com.BriteERP.pages.LoginPage;
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
    public void expctedValueTest() throws InterruptedException{
        CRMpage crm = new CRMpage();
        crm.crmTab.click();
        crm.listViewButton.click();
        List<WebElement> book = driver.findElements(By.xpath("//tbody/tr[3]/td"));
        String expectedRevenue = book.get(8).getText();
        crm.pivotSortButton.click();
        crm.expandTotalButton.click();
        Thread.sleep(2000);
        crm.expandTotalButton.click();
        Thread.sleep(2000);
        crm.opportunityTotalButton.click();
        Thread.sleep(3000);
        List<WebElement> book2 = driver.findElements(By.xpath("//tbody/tr[3]/td"));
        Assert.assertEquals(expectedRevenue, book2.get(1).getText());
    }
}
