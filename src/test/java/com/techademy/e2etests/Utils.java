package com.techademy.e2etests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Instant;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Utils extends TestRunner {
	
	public HashMap<String,String> readTestData() throws IOException{

		 File file = new File(".//src//test//resources//TestData.xls");
		 FileInputStream inputStream = new FileInputStream(file);
		 HSSFWorkbook wb=new HSSFWorkbook(inputStream);
		 HSSFSheet sheet=wb.getSheet("Sheet1");
		 HSSFRow row=sheet.getRow(1);
		 HashMap<String,String> testdata = new HashMap<String,String>();
		 testdata.put("FirstName",row.getCell(0).toString());
		 testdata.put("LastName",row.getCell(1).toString());
		 testdata.put("PostalCode",row.getCell(2).toString());
		 
		 return testdata;
	}
	
	  public void captureScreenshot() throws IOException {
		  
		  File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		  Long timestamp = Instant.now().getEpochSecond();
		  FileUtils.copyFile(screenshot,new File("screenshots/screenshot-"+timestamp+".png"));
	  }

}
