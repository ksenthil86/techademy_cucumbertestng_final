package com.techademy.e2etests;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

@CucumberOptions(
		features = {"src/test/resources/features"},
		glue = {"com.techademy.e2etests"},
		monochrome = true,
		dryRun = false,
		plugin = {"pretty", "html:target/cucumber-reports/cucumber.html"})
public class TestRunner {
	private TestNGCucumberRunner testNGCucumberRunner;
	public static WebDriver driver;
	public final static int TIMEOUT = 10;
	protected static String URL = "";

	@BeforeSuite(alwaysRun = true)
	public void setUpCucumber() throws Exception {
		testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());

        ChromeOptions options = new ChromeOptions(); 
        options.addArguments("--remote-allow-origins=*"); 
        options.addArguments("--start-maximized");
	    driver = new ChromeDriver(options);
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
	    URL="https://www.saucedemo.com/";
	}

	@Test(groups = "cucumber", description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
	public void runScenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) {
		testNGCucumberRunner.runScenario(pickleWrapper.getPickle());
	}

	@DataProvider
	public Object[][] scenarios() {
		return testNGCucumberRunner.provideScenarios();
	}
	

	@AfterSuite(alwaysRun = true)
	public void tearDownClass() throws Exception {
		driver.quit();
		testNGCucumberRunner.finish();
	}
	
}