package com.BriteERP.pages;

import com.BriteERP.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CRMpage {

    public CRMpage() {
        PageFactory.initElements(Driver.get(), this);
    }

    @FindBy(xpath = "//span[@class='oe_menu_text' and contains(text(),'CRM')]")
    public WebElement crmTab;

    @FindBy(xpath = "//button[@accesskey='c' and contains(text(),'Create')]")
    public WebElement createOpportunityButton;

    @FindBy(xpath = "//input[@name='name']")
    public WebElement opportunityTitleInputField;

    @FindBy(xpath = "//input[@name='planned_revenue']")
    public WebElement expectedRevenueInputField;

    @FindBy(xpath = "//button[@name='close_dialog']")
    public WebElement createButtonForCreateAnOpportunity;

    @FindBy(xpath = "//button[@data-original-title='Pivot']")
    public WebElement pivotSortButton;

    @FindBy(xpath = "//td[contains(text(), 'Total')]")
    public WebElement expandTotalButton;

    @FindBy(xpath = "//td[@class='o_pivot_header_cell_opened']")
    public WebElement totsExpButton;

    @FindBy(xpath = "//a[contains(text(), 'Opportunity')]")
    public WebElement totalOpportunity;

    @FindBy(xpath = "//td[@class='o_pivot_header_cell_closed']")
    public WebElement tots2;

    @FindBy(xpath = "//li[@data-field='name']")
    public WebElement dropOportunities;

    @FindBy(xpath = "//tbody/tr[1]/td[2]")
    public WebElement totalExpectedRevenueField;

    @FindBy(xpath = "//button[@accesskey='l']")
    public WebElement listViewButton;

    @FindBy(xpath = "//tbody/tr/td[3]")
    public List<WebElement> listItemNames;

    @FindBy(xpath = "//tbody/tr/td[9]")
    public List<WebElement> listItemPrices;

    @FindBy(xpath = "//tbody/tr/td[1]")
    public List<WebElement> pivotItemNames;

    @FindBy(xpath = "//table[@class='table-hover table-condensed table-bordered']/tbody/tr/td[2]")
    public List<WebElement> sumRavenues;

    @FindBy(xpath = "//a[@href='#' and contains (text (), 'Opportunity')]")
    public WebElement opportunityTotalButton;

    public void totalOpportunityChoices(String name) {
        WebElement choice = Driver.get().findElement(By.xpath("//ul[@class='dropdown-menu o_pivot_field_menu']/li/a[contains(text(), '" + name + "')]"));
        choice.click();
    }

    public void moduleTabButtonClick(String moduleName) {
        WebElement tab = Driver.get().findElement(By.xpath("//a[@class='oe_menu_toggler']/span[contains (text(), '" + moduleName + "')]"));
        tab.click();
    }

    public int numberRowsForRevenue() {
        List<WebElement> tableRows = Driver.get().findElements(By.xpath("//tbody/tr/td[2]"));
        return tableRows.size();
    }


}
