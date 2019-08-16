package com.BriteERP.step_definitions;

import com.BriteERP.pages.CRMpage;
import com.BriteERP.pages.LoginPage;
import com.BriteERP.utilities.BrowserUtils;
import com.BriteERP.utilities.ConfigurationReader;
import com.BriteERP.utilities.Driver;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;


public class crm_stepDefs {
    CRMpage crm = new CRMpage();
    String actualS1 = null, expectedS1 = null;


    @Given("I login as {string}")
    public void i_login_as(String userType) {
        String username = null, password = null;
        if(userType.equals("CRM manager")) {
            username = ConfigurationReader.get("crm57");
            password = ConfigurationReader.get("crm57password");
        }
        Driver.get().manage().window().maximize();
        LoginPage login = new LoginPage();
        BrowserUtils.waitFor(1);
        BrowserUtils.highlight(login.username);
        login.username.sendKeys(username);
        BrowserUtils.waitFor(1);
        BrowserUtils.highlight(login.password);
        login.password.sendKeys(password);
        BrowserUtils.waitFor(1);
        BrowserUtils.highlight(login.submit);
        login.submit.click();

    }


    @When("I click on the crm tab")
    public void i_click_on_crm_the_tab() {
        BrowserUtils.waitFor(1);
        BrowserUtils.highlight(crm.crmTab);
        crm.crmTab.click();
        BrowserUtils.waitFor(3);

    }

    @When("Wait for {int} seconds")
    public void wait_for_seconds(int seconds) {
        BrowserUtils.waitFor(seconds);
    }

    @When("I click on Pivot board button")
    public void i_click_on_Pivot_board_button() {
        BrowserUtils.waitFor(1);
        BrowserUtils.highlight(crm.pivotSortButton);
        crm.pivotSortButton.click();
    }

    @Then("Page title should be {string}")
    public void page_title_should_be(String exptitle) {
        String actualTitle = Driver.get().getTitle();
        Assert.assertEquals(exptitle, actualTitle);
    }

    // Scenario 1

    @When("I click on the List board")
    public void i_click_on_the_List_board() {
        BrowserUtils.waitFor(1);
        BrowserUtils.highlight(crm.listViewButton);
        crm.listViewButton.click();
    }

    @When("Save {string} price to expected")
    public void save_price_to_expected(String productName) {
        for(int i = 0; i<crm.listItemNames.size(); i++){
            if(crm.listItemNames.get(i).getText().contains(productName)){
                expectedS1 = crm.listItemPrices.get(i).getText();
            }
        }
    }

    @Then("Check if {string} price on the Pivot board matches corresponding price on the list board")
    public void check_if_price_on_the_Pivot_board_matches_corresponding_price_on_the_list_board(String productName) {
        BrowserUtils.waitFor(1);
        BrowserUtils.highlight(crm.expandTotalButton);
        crm.expandTotalButton.click();
        BrowserUtils.waitFor(1);
        BrowserUtils.highlight(crm.expandTotalButton);
        crm.expandTotalButton.click();
        BrowserUtils.scrollToElement(crm.totalOpportunity);
        BrowserUtils.waitFor(1);
        BrowserUtils.highlight(crm.totalOpportunity);
        crm.totalOpportunity.click();
        BrowserUtils.waitFor(5);

        for (int j = 0; j < crm.sumRavenues.size(); j++) {
            String it = crm.pivotItemNames.get(j).getText();
            if (it.equals(productName)) {
                actualS1 = crm.sumRavenues.get(j).getText();
            }
        }
        Assert.assertEquals(expectedS1, actualS1);
    }

    //Scenario 2
    @When("I double click on {string}")
    public void i_double_click_on(String string) {
        BrowserUtils.waitFor(1);
        BrowserUtils.highlight(crm.expandTotalButton);
        crm.expandTotalButton.click();
        BrowserUtils.waitFor(1);
        BrowserUtils.highlight(crm.expandTotalButton);
        crm.expandTotalButton.click();

    }

    @When("I click on {string} option from dropdown")
    public void i_click_on_option_from_dropdown(String option) {
        BrowserUtils.scrollToElement(crm.totalOpportunity);
        BrowserUtils.waitFor(1);
        BrowserUtils.highlight(crm.totalOpportunity);
        crm.totalOpportunity.click();
    }

    @Then("Total revenue expected matches sum of all revenues")
    public void total_revenue_expected_matches_sum_of_all_revenues() {
        CRMpage crm = new CRMpage();
        BrowserUtils.waitFor(5);
        Double expectedSum = Double.parseDouble(crm.sumRavenues.get(0).getText().replace(",", ""));
        Double actual = 0.0;
        System.out.println(crm.sumRavenues.size());
        for (int i = 1; i<crm.sumRavenues.size(); i++) {
            actual += Double.parseDouble(crm.sumRavenues.get(i).getText().replace(",", ""));
        }
        org.testng.Assert.assertEquals(actual, expectedSum);
    }


}
