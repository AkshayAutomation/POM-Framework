package com.eBay.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CheckoutPage {
	ExtentTest test;
	//Initializing the Page Objects
	public CheckoutPage(AppiumDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//android.widget.Button[@resource-id='com.ebay.mobile:id/cta_button']")
	public WebElement BuyOption;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.ebay.mobile:id/alertTitle']")
	public WebElement PopUpMessage;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='OK']")
	public WebElement OK;
	
	
	//Methods
	public void checkoutValidation() throws InterruptedException
	{
		if(BuyOption.isEnabled())
		{
			System.out.println("product is in stock and available");
			BuyOption.click();
			Thread.sleep(4000);
		}
		else
		{
			Assert.fail("Product is not available in store");
		}

	}


	public void productDeliveryValidation()
	{
		if(PopUpMessage.isDisplayed())
		{
			String Message = PopUpMessage.getText();
			System.out.println(Message);
			Assert.assertEquals( Message, "Item cannot be posted to you");
			Assert.fail("Seller cannot deliver the product to primary address");
		}
		else
		{
			System.out.println("Delivery is available to primary address");
		}
	}



}
