package com.techademy.virtusa.cucumbertestng;

import static org.testng.Assert.assertThrows;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

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
	
	@When("I Validate Ajax Loader link")
	public void verifyAjaxlink() {
		if(driver.findElements(By.xpath("//a[@id='ajax-loader']")).size()==1) {
			System.out.println("Ajax Loader link present");
		}else {
			Assert.fail("Ajax Loader link not present");
		}
	}
	
	@And("I click Ajax Loader link")
	public void clicAjaxlink() {
		driver.findElement(By.xpath("//a[@id='ajax-loader']")).click();
		
		String originalWindow = driver.getWindowHandle();
		for (String windowHandle : driver.getWindowHandles()) {
		    if(!originalWindow.contentEquals(windowHandle)) {
		        driver.switchTo().window(windowHandle);
		        break;
		    }
		}
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.titleIs("WebDriver | Ajax-Loader"));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("button1"))));
	}
	
	@And("I click Click Me link")
	public void clicClickMelink() {
		driver.findElement(By.id("button1")).click();
	}
	
	@Then("I validate pop up message")
	public void verifypopupmessage() {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h4[@class='modal-title' and text()='Well Done For Waiting....!!!']"))));
		if(driver.findElement(By.xpath("//h4[@class='modal-title' and text()='Well Done For Waiting....!!!']")).isDisplayed()) {
			System.out.println("Popup Message is displayed");
		}else {
			Assert.fail("Popup Message is not displayed");
		}
	}
	
    @After
	public void teardown() {   
           driver.quit();
	}
	

}
