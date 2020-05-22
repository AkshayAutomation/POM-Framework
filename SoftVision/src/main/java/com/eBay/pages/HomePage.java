package com.eBay.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.eBay.base.BaseClass;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class HomePage extends BaseClass{

	//Initializing the Page Objects
	public HomePage(AppiumDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(id = "com.ebay.mobile:id/logo")
	public WebElement Logo;


	@AndroidFindBy(xpath = "//android.widget.TextView[@content-desc=\"Search keyword Search for anything\"]")
	public WebElement SearchTab;

	@AndroidFindBy(id = "com.ebay.mobile:id/search_src_text")
	public WebElement SearchBar;

	//Actions
	public boolean eBayIconValidation() {
		return Logo.isDisplayed();
	}

	public boolean searchTabValidation() {
		return SearchTab.isDisplayed();
	}
	
	
	public void searchProduct() throws InterruptedException {
		System.out.println("Searching Product");
		Thread.sleep(4000);
		SearchTab.click();
		Thread.sleep(2000);
		SearchBar.sendKeys(getCellData(1,0, "CogniData"));
		((AndroidDriver<AndroidElement>)driver).pressKey(new KeyEvent(AndroidKey.ENTER));
		Thread.sleep(3000);
		
	}
}
