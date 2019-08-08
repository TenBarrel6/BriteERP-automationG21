package com.BriteERP.pages;

import com.BriteERP.utilities.BrowserUtils;
import com.BriteERP.utilities.ConfigurationReader;
import com.BriteERP.utilities.Driver;
import com.BriteERP.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTesting extends TestBase {
    @BeforeMethod
    public void setUpMethod2() {
        driver.get(ConfigurationReader.get("urlqa2"));
    }

    @Test
    public void loginDriverTest() {
        driver.findElement(By.xpath("//*[@id=\"top_menu\"]/li[10]/a/b")).click();
        String username = ConfigurationReader.get("crm55");
        String password = ConfigurationReader.get("crm55password");

        LoginPage loginPage = new LoginPage();


        loginPage.username.sendKeys(username);
        loginPage.password.sendKeys(password);


        loginPage.submit.click();

        BrowserUtils.waitFor(5);

        String title = driver.getTitle();

        Assert.assertTrue(driver.getTitle().contains(title));

    }

}
