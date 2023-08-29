package com.techademy.virtusa.cucumbertestng;


import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features={"src/test/resources/features"},
				  glue= {"com.techademy.virtusa.cucumbertestng"},
//				  dryRun=true,
				  monochrome=true
				)
public class CukesRunner {
	
}
