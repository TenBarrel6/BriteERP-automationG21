package com.BriteERP.step_definitions;

import com.BriteERP.utilities.ConfigurationReader;
import com.BriteERP.utilities.Driver;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class Hooks {
WebDriver driver;
	@Before
	public void setUp(){
		System.out.println("Before hooks");
		Driver.get().get(ConfigurationReader.get("urlqa1"));
		Driver.get().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

	}

	@After
	public void tearDown(Scenario scenario){
		// check if the scenario is failed
		if (scenario.isFailed()){
			// take that screenshot
			final byte[] screenshot = ((TakesScreenshot) Driver.get()).getScreenshotAs(OutputType.BYTES);
			// attach the scenario to the report
			scenario.embed(screenshot, "image/png");
		}
		Driver.closeDriver();
	}


}
