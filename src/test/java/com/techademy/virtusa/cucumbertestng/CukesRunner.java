package com.techademy.virtusa.cucumbertestng;


import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features={"src/test/resources/features"},
				  glue= {"com.techademy.virtusa.cucumbertestng"},
				  plugin = {"pretty", "html:target/cucumber-reports/cucumber.html"},
				  monochrome=true
				)
public class CukesRunner {
	
}
