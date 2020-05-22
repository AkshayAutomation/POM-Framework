package com.eBay.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.eBay.base.BaseClass;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductDetailsPage extends BaseClass{

	    //Initializing the Page Objects
		public ProductDetailsPage(AppiumDriver<AndroidElement> driver) {
			PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		}

		@AndroidFindBy(xpath = "//android.widget.ImageView[@resource-id='com.ebay.mobile:id/imageview_image']")
		public WebElement ProductImage;

		@AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc='Close']")
		public WebElement CloseImageView;
		
		
		//Methods
		public void orientation() throws InterruptedException
		{
			driver.rotate(ScreenOrientation.LANDSCAPE);
			System.out.println("The app is running in :" +driver.getOrientation()+ " Orientation");
			Thread.sleep(2000);
			driver.rotate(ScreenOrientation.PORTRAIT);
			System.out.println("The app is running in :" +driver.getOrientation()+ " Orientation");
			Thread.sleep(2000);
			CloseImageView.click();
			
		}
	
		//Methods
		public void getDimension()
		{
			//Validating the dimension of the mobile screen
			Dimension dimensions = driver.manage().window().getSize();
			System.out.println("The scripts are running in the mobile screen with the dimensions :" +dimensions.getWidth()+ ": Width ," +dimensions.getHeight()+":Height");
		}
		
		
		
		
		
}
