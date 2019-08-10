package com.BriteERP.runners;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/com/BriteERP/features/Login.feature",
        glue = "com/BriteERP/step_definitions"
)
public class TestRunner {
}
