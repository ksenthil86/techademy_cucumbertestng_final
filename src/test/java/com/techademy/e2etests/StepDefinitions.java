package com.techademy.e2etests;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitions extends TestRunner {
	
	private Scenario scenario;
	Utils utils = new Utils();

	@Before
    public void before(Scenario scenarioVal) {
        this.scenario = scenarioVal;
        System.out.println("Scenario: " + scenario.getName());
    }
	
	
	@Given("I launch Sauce Labs Ecommerce website and verify {string}")
	public void i_launch_sauce_labs_ecommerce_website_and_verify(String title) throws IOException {
		driver.get(URL);
		utils.captureScreenshot();
		Assert.assertEquals(driver.getTitle(), title);
	}
	
	@Given("^I login to Sauce Labs Ecommerce website \"(.*)\" and \"(.*)\"$")
	public void i_login_to_sauce_labs_ecommerce_website(String username,String password) throws InterruptedException, IOException {
		driver.findElement(By.id("user-name")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("login-button")).submit();
		Thread.sleep(2000);
		if(!driver.findElement(By.xpath("//span[text()='Products']")).isDisplayed()) {
			Assert.fail("Failed - Login UnSuccessful");
		}
		utils.captureScreenshot();
	}
	
	@When("I add multiple items to cart")
	public void i_add_multiple_items_to_cart() throws IOException {
		driver.findElement(By.xpath("//div[@class='inventory_item_name' and text()='Sauce Labs Backpack']/following::button[text()='Add to cart']")).click();
		driver.findElement(By.xpath("//div[@class='inventory_item_name' and text()='Sauce Labs Bike Light']/following::button[text()='Add to cart']")).click();
		utils.captureScreenshot();
	}
	
	@When("I click Checkout")
	public void i_click_checkout() throws IOException {
	    driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
	    utils.captureScreenshot();
	    driver.findElement(By.id("checkout")).click();
	}
	
	@When("I enter user details")
	public void i_enter_user_details() throws IOException {
		HashMap<String,String> userdetails = utils.readTestData();
		driver.findElement(By.id("first-name")).sendKeys(userdetails.get("FirstName"));
		driver.findElement(By.id("last-name")).sendKeys(userdetails.get("LastName"));
		driver.findElement(By.id("postal-code")).sendKeys(userdetails.get("PostalCode"));
		utils.captureScreenshot();
		driver.findElement(By.id("continue")).click();
	}
	
	@When("I click Finish button")
	public void i_click_finish_button() throws IOException {
		utils.captureScreenshot();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(100));
		wait.until(ExpectedConditions.elementToBeClickable(By.id("finish"))).click();		
	}
	
	@Then("^I verify the message \"(.*)\"$")
	public void i_verify_the_message(String msg) throws IOException {
		WebElement thankyoumessage = driver.findElement(By.xpath("//div[@id=\"checkout_complete_container\"]/h2[text()='"+msg+"']"));
		utils.captureScreenshot();
		if(thankyoumessage.isDisplayed()) {
			System.out.println("PASSED - Thank You Message Displayed");
		}else {
			Assert.fail("FAILED - Thank You Message Not Displayed" );
		}
	}
	
	

}
