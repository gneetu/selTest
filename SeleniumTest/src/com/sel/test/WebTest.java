package com.sel.test;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class  WebTest{
	FirefoxDriver driver;
	String searchText = "//input[@id='lst-ib']";
	String searchString="Datalicious";
	String buttonSearch="btnG";
	String getlistofa ="//div[@id='rso']//h3/a";
	
	
	public void TaskFirst() throws Exception
	{
		//geckodriver path
		System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir") + File.separator +"/lib/geckodriver.exe");
		
		//set firefox driver
		DesiredCapabilities dc=DesiredCapabilities.firefox();
		dc.setBrowserName("firefox");
		dc.setPlatform(Platform.WIN10);
		
		driver =  new FirefoxDriver(dc);
		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
		driver.get("http://www.google.com");
		driver.findElement(By.xpath(searchText)).sendKeys(searchString);
		driver.findElement(By.name(buttonSearch)).click();
		
		List<WebElement> findElements = driver.findElements(By.xpath(getlistofa));
		String first_link = findElements.get(0).getAttribute("href");
		driver.navigate().to(first_link);				
	}
	
	public void TaskSecond() {
		
		JavascriptExecutor javascript = (JavascriptExecutor) driver;
		
		String DomainUsingJS=(String)javascript.executeScript("return document.domain");  
		System.out.println("My Current URL Is  : "+DomainUsingJS);
		
		String sText =  javascript.executeScript("return document.title;").toString();
		System.out.println(sText);
	}

	public void closeDriver()
	{
		driver.close();
	}
	
	
	public static void main(String args[]) throws Exception
	{
		WebTest tf = new WebTest();
		tf.TaskFirst();
		tf.TaskSecond();
		tf.closeDriver();
	}
	
	
}
