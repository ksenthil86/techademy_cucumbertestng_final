package com.techademy.virtusa.cucumbertestng;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;

public class StepDefinitions {
	
    private static WebDriver driver;
    public final static int TIMEOUT = 10;
	
    @Before
	public void setup() {   
           
        ChromeOptions options = new ChromeOptions(); 
        options.addArguments("--remote-allow-origins=*"); 
        options.addArguments("--start-maximized");
	        driver = new ChromeDriver(options);
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
	}
	
	
	@Given("^I login to WebDriverUniversity website \"(.*)\"$")
	public void logintowebsite(String url) {
		driver.get(url);
	}

}
