package com.eBay.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class BaseClass {


	public static AppiumDriver<AndroidElement> driver;
	public static Properties prop;

	
		
		public void setup() throws MalformedURLException
		{
			//Setting Up Environment for Appium Tests
			System.out.println("Environment Setup Initiated");

			try {
				
				//Setting all the required capabilities from property file instead of refactoring code
				prop = new Properties();
				FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\eBay\\config\\config.AppProperties");
				prop.load(ip);
				
				
				//Setting desired capabilities for the test cases
				//Using real device
				DesiredCapabilities caps= new DesiredCapabilities();

				//Device Related Capabilities
				//Capabilities are set from Config.AppProperties File
				caps.setCapability(MobileCapabilityType.DEVICE_NAME, prop.getProperty("DeviceName"));
				caps.setCapability(MobileCapabilityType.PLATFORM_NAME, prop.getProperty("PlatformName"));
				caps.setCapability(MobileCapabilityType.UDID, prop.getProperty("UDID"));
				caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, prop.getProperty("PlatformVersion"));
				
				
				//eBay App related Capabilities
				caps.setCapability("appPackage", prop.getProperty("AppPackage"));
				caps.setCapability("appActivity", prop.getProperty("MainActivity"));
				//Resetting the app data after the execution of scripts
				caps.setCapability("noReset", true);



				//Appium Server 
				URL url = new URL("http://127.0.0.1:4723/wd/hub");
				driver = new AndroidDriver<AndroidElement>(url, caps);
				driver.resetApp();
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				Thread.sleep(5000);


			} catch (Exception ex) {
				System.out.println(ex.getCause());
				System.out.println(ex.getMessage());
			}

		}

		public static String getCellData(int rownum, int colnum, String fileName)
		{
			try {

				FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\"+fileName+".xlsx");
				XSSFWorkbook wb = new XSSFWorkbook(file);
				XSSFSheet sheet = wb.getSheet("ProductSearch");
				XSSFRow row= sheet.getRow(rownum);
				XSSFCell cell = row.getCell(colnum);
				System.out.println("the test data, passed in the script is : "+cell.getStringCellValue());

				return cell.getStringCellValue();
			}
			catch (Exception e)
		     {
		        System.out.println(e);
		         return "Exception Occured";
		    }
		}

		
		public void teardown() throws IOException
		{
			System.out.println("Automation Script Ended");
			driver.closeApp();
		}



}
