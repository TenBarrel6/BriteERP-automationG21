package com.BriteERP.runners;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"json:target/cucumber.json"},
        features = "src/test/resources/com/BrightERP/features/",
        glue = "com/BriteERP/step_definitions",
        tags = "@CRM",
        dryRun = false
)
public class TestRunner {
}
