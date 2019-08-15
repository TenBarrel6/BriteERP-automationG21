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
import org.openqa.selenium.WebElement;


public class crm_stepDefs {
    CRMpage crm = new CRMpage();


    @Given("I login as {string}")
    public void i_login_as(String userType) {
        String username = null, password = null;
        if(userType.equals("CRM manager")) {
            username = ConfigurationReader.get("crm57");
            password = ConfigurationReader.get("crm57password");
        }

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

    @When("I click on Pivot board sign")
    public void i_click_on_Pivot_board_sign() {
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

    @Then("{string} price on the Pivot board matches corresponding the price on list board")
    public void price_on_the_Pivot_board_matches_corresponding_the_price_on_list_board(String value) {

        String actual1 = "";
        String actual2 = "";

        int index = 0;
        for (int i = 0; i < crm.listItemNames.size(); i++) {
            String item = crm.listItemNames.get(i).getText();
            if (item.equals(value)) {
                actual1 = crm.listItemPrices.get(i).getText();
            }
        }
        BrowserUtils.waitFor(1);
        BrowserUtils.highlight(crm.pivotSortButton);
        crm.pivotSortButton.click();
        BrowserUtils.waitFor(1);
        BrowserUtils.highlight(crm.expandTotalButton);
        crm.expandTotalButton.click();
        BrowserUtils.waitFor(1);
        BrowserUtils.highlight(crm.expandTotalButton);
        crm.expandTotalButton.click();
        BrowserUtils.waitFor(1);
        BrowserUtils.highlight(crm.totalOpportunity);
        crm.totalOpportunity.click();


        for (int j = 0; j < crm.sumRavenues.size(); j++) {
            String it = crm.pivotItemNames.get(j).getText();
            if (it.equals(value)) {
                actual2 = crm.sumRavenues.get(j).getText();
            }


        }
        Assert.assertEquals(actual1, actual2);


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

    @Then("{string} matches sum of all revenues")
    public void matches_sum_of_all_revenues(String string) {
        System.out.println(crm.sumRavenues.size());
        double expectedSum = Double.parseDouble(crm.sumRavenues.get(0).getText().replace(",", ""));
        double actual = 0.0;
        crm.sumRavenues.remove(0);

        for (WebElement each : crm.sumRavenues) {
            actual += Double.parseDouble(each.getText().replace(",", ""));
//            System.out.println(actual);
        }
        System.out.println(actual);
        System.out.println(expectedSum);
        org.testng.Assert.assertEquals(actual, expectedSum);
    }


}
