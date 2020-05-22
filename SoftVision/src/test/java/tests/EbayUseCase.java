package tests;

import java.net.MalformedURLException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.eBay.base.BaseClass;
import com.eBay.pages.CheckoutPage;
import com.eBay.pages.HomePage;
import com.eBay.pages.ProductDetailsPage;
import com.eBay.pages.ProductsPage;

public class EbayUseCase extends BaseClass{
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	HomePage homepage;
	ExtentTest test;
	
	@BeforeSuite
	public void reportSetup()
	{
		//System.getProperty("user.dir")+"\\src\\main\\java\\com\\eBay\\config\\config.AppProperties"
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\"+"eBay.html");	
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		
	}
	
	
	@BeforeClass
	public void initialization() throws MalformedURLException{
		setup();
		homepage= new HomePage(driver);
	}

	//App Launch Validation
	@Test(priority = 1)
	public void appLaunchValidation() throws Exception
	{
		test = extent.createTest("App Launch","eBay Launch validation");
		//App Launch Message
		System.out.println("App Launched");
		Thread.sleep(3000);
	
		boolean icon = homepage.eBayIconValidation();
		Assert.assertTrue(icon, "eBay icon is displayed");
		test.log(Status.INFO, "App Launched Successfully");
	}

	
	//Search Tab Validation
	@Test(priority = 2)
	public void searchOptionValidation()
	{
		test = extent.createTest("Search Option Validation");
		boolean SearchTab = homepage.searchTabValidation();
		Assert.assertTrue(SearchTab, "Search Option is Available");
		test.log(Status.INFO, "Search Option is Available");
	}

	
	//Search Product
	@Test(priority = 3)
	public void productSearch() throws InterruptedException
	{
		test = extent.createTest("Search product Validation");
		homepage.searchProduct();
		Thread.sleep(4000);
		test.log(Status.INFO, "Search product is Available");
	}
	
	
	//Select random product from list of products 
	@Test(priority = 4)
	public void SelectRandom() throws InterruptedException
	{
		test = extent.createTest("Random Product Selection");
		ProductsPage productpage = new ProductsPage(driver);
		productpage.swipe();
		productpage.selectRandomProduct();
		Thread.sleep(3000);
		test.log(Status.INFO, "Random Product is selected");
	}
	
	@Test(priority = 5)
	public void validateProduct() throws InterruptedException
	{
		test = extent.createTest("Product Validation");
		ProductDetailsPage productdetail = new ProductDetailsPage(driver);
		
		productdetail.ProductImage.isDisplayed();
		productdetail.ProductImage.click();
		productdetail.orientation();
		productdetail.getDimension();
		test.log(Status.INFO, "Product Details are Validated");
	}
	
	@Test(priority = 6)
	public void validateDeliveryoption() throws InterruptedException
	{
		
		try {
			test = extent.createTest("Delivery Option validation");
			CheckoutPage checkout = new CheckoutPage(driver);
			checkout.checkoutValidation();
			checkout.productDeliveryValidation();
			test.getStatus();
			
		} catch (Exception e) {
			test.log(Status.FAIL, "Item cannot be delivered");
		}
		
	}
	
	@AfterClass
	public void appTearDown(){
		System.out.println("Automation Script Ended");
		driver.closeApp();
	}
	
	@AfterSuite
	public void teardownReport()
	{
		//Flush everything to the log file
		extent.flush();
		
	}
	
	
}
