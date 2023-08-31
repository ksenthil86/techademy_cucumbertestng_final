package testngtests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class ReqResTest {
	
    private static WebDriver driver;
    public final static int TIMEOUT = 10;
    
  @BeforeMethod
  public void beforeMethod() {
	  System.setProperty("webdriver.chrome.driver","C:\\WebDriver\\bin\\chromedriver.exe");
      ChromeOptions options = new ChromeOptions(); 
      options.addArguments("--remote-allow-origins=*"); 
      options.addArguments("--start-maximized");
	        driver = new ChromeDriver(options);
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
	        
  }
  
  @Test(invocationCount = 1) //Run the same test 10 times
  public void postTest() throws IOException {
	  
	   // Open WebDriverUniversity website
	   driver.get("http://webdriveruniversity.com/index.html");
	   
	   //Check Ajax Loader link and click the link
		if(driver.findElements(By.xpath("//a[@id='ajax-loader']")).size()==1) {
			System.out.println("Ajax Loader link present");
		}else {
			Assert.fail("Ajax Loader link not present");
		}
		driver.findElement(By.xpath("//a[@id='ajax-loader']")).click();
		
		//Go to the new tab
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
		
		//Click 'Click Me' link
		driver.findElement(By.id("button1")).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h4[@class='modal-title' and text()='Well Done For Waiting....!!!']"))));
		
		//Check if the pop up message is displayed
		if(driver.findElement(By.xpath("//h4[@class='modal-title' and text()='Well Done For Waiting....!!!']")).isDisplayed()) {
			System.out.println("Popup Message is displayed");
		}else {
			Assert.fail("Popup Message is not displayed");
		}
		
		//Capture Screenshot
		captureScreenshot();
  }

  @AfterMethod
  public void afterMethod() {
	  driver.quit();
  }
  
  public void captureScreenshot() throws IOException {
	  
	  File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	  Long timestamp = Instant.now().getEpochSecond();
	  FileUtils.copyFile(screenshot,new File("screenshots/screenshot-"+timestamp+".png"));
  }

}
