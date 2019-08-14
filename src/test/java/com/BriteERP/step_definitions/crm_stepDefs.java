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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class crm_stepDefs {
    WebDriver driver;
    CRMpage crm = new CRMpage();



    @Given("I login as {string}")
    public void i_login_as(String username) {
        username = ConfigurationReader.get(username);
        String password = ConfigurationReader.get("crm57password");

        LoginPage login = new LoginPage();
        login.username.sendKeys(username);
        login.password.sendKeys(password);
        login.submit.click();

    }



    @When("I click on the crm tab")
    public void i_click_on_crm_the_tab() {

        crm.crmTab.click();
        BrowserUtils.waitFor(3);

    }
    @When("I click on Pivot board sign")
    public void i_click_on_Pivot_board_sign() {

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

        crm.listViewButton.click();


    }

    @Then("{string} price on the Pivot board matches corresponding the price on list board")
    public void price_on_the_Pivot_board_matches_corresponding_the_price_on_list_board(String value) {

        String actual1 = "";
        String actual2 = "";

        int index =0;
        for (int i = 0; i < crm.listItemNames.size(); i++) {
            String item = crm.listItemNames.get(i).getText();
            if (item.equals(value)) {
                actual1 = crm.listItemPrices.get(i).getText();
            }
        }
        crm.pivotSortButton.click();
        crm.expandTotalButton.click();
        crm.expandTotalButton.click();
        crm.totalOpportunity.click();


            for (int j = 0; j < crm.sumRavenues.size(); j++) {
                String it = crm.pivotItemNames.get(j).getText();
                if(it.equals(value)){
                    actual2 = crm.sumRavenues.get(j).getText();
                }


        }
            Assert.assertEquals(actual1,actual2);





    }

    //Scenario 2
    @When("I double click on {string}")
    public void i_double_click_on(String string) {
 crm.expandTotalButton.click();
 crm.expandTotalButton.click();

    }

    @When("I click on {string} option from dropdown")
    public void i_click_on_option_from_dropdown(String option) {

        crm.totalOpportunity.click();

    }

    @Then("{string} matches sum of all revenues")
    public void matches_sum_of_all_revenues(String string) {

        double expectedSum = Double.valueOf(crm.sumRavenues.get(0).getText().replace(",", ""));
        double actual = 0.0;
        crm.sumRavenues.remove(0);

        for (WebElement each : crm.sumRavenues) {
            actual += Double.valueOf(each.getText().replace(",", ""));
            System.out.println(actual);
        }
        org.testng.Assert.assertEquals(actual, expectedSum);
    }


}
