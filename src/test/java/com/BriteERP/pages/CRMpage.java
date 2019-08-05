package com.BriteERP.pages;

import com.BriteERP.utilities.Driver;
import com.BriteERP.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CRMpage extends TestBase {

	public CRMpage(){
		PageFactory.initElements(Driver.get(), this);
	}

	@FindBy(xpath ="//span[@class='oe_menu_text' and contains(text(),'CRM')] ")
	public WebElement crmTab;

	@FindBy(xpath = "//button[@accesskey='c' and contains(text(),'Create')] ")
	public WebElement createOpportunityButton;

	@FindBy(xpath="//input[@name='name']")
	public WebElement opportunityTitleInputField;

	@FindBy(xpath ="//input[@name='planned_revenue']")
	public WebElement expectedRevenueInputField;

	@FindBy(xpath = "//button[@name='close_dialog']")
	public WebElement createButtonForCreateAnOpportunity;

	@FindBy(xpath = "//button[@data-original-title='Pivot']")
	public WebElement pivotSortButton;

	@FindBy(xpath = "(//table[@class='table-hover table-condensed table-bordered']//td)[1]")
	public WebElement expandTotalButton;

	@FindBy(xpath = "//a[@href='#' and contains (text (), 'Opportunity')]")
	public WebElement opportunityTotalButton;

	@FindBy(xpath = "//tbody/tr[1]/td[2]")
	public WebElement totalExpectedRevenueField;

	public int numberRowsForRevenue(){
		List<WebElement> tableRows = driver.findElements(By.xpath("//tbody/tr/td[2]"));
		return tableRows.size();
	}







}
