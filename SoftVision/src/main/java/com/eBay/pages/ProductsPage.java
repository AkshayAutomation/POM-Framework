package com.eBay.pages;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.eBay.base.BaseClass;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class ProductsPage extends BaseClass{

	public ProductsPage(AppiumDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//android.widget.RelativeLayout[@index='2']")
	public WebElement Product1;

	@AndroidFindBy(xpath = "//android.widget.RelativeLayout[@index='3']")
	public WebElement Product2;

	@AndroidFindBy(id = "com.ebay.mobile:id/text_slot_1")
	public WebElement PopUp;

	@AndroidFindBy(xpath = "//android.widget.RelativeLayout")
	public List<WebElement> ListProducts;
	
	//Methods
	public void selectRandomProduct() throws InterruptedException
	{
		Thread.sleep(2000);
		PopUp.click();
		Thread.sleep(2000);

		//getting list of all the products for the mentioned X path

		//Choosing a random product within the list of products
		System.out.println(ListProducts.size());  
		Random r = new Random(); 
		int randvalue =r.nextInt(ListProducts.size());
		ListProducts.get(randvalue).click();
		System.out.println(randvalue);
		

	}

	public void swipe() throws InterruptedException
	{
		Thread.sleep(2000);
		PopUp.click();
		Thread.sleep(2000);
		
		int a=Product1.getLocation().getX();
		int b=Product1.getLocation().getY();

		int x=Product2.getLocation().getX();
		int y=Product2.getLocation().getY();

		//Swipe from one product to another Product
		TouchAction ta = new TouchAction(driver);
		ta.press(PointOption.point(a, b)).waitAction(new WaitOptions().withDuration(Duration.ofMillis(600))).moveTo(PointOption.point(x, y)).release().perform();

	}
}
