package com.grubhub.ofos.restauranttest;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.comcast.crm.generic.basetest.BaseClass;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.AddCategoryPage;
import com.comcast.crm.objectrepositoryutility.AddrestaurantPage;
import com.comcast.crm.objectrepositoryutility.AdminloginPage;
import com.comcast.crm.objectrepositoryutility.Allrestaurantpage;
import com.comcast.crm.objectrepositoryutility.DashboardPage;
/**
 * 
 * 
 * @author Deepak
 *
 */
@Listeners(com.grubhub.ofos.generic.listenerutility.ListImpClass.class)
public class AddRestaurantandVerifyHeaderTest extends BaseClass {

	@Test(groups = {"smokeTest"})
	public void addrestaurantandverifyallrestaurantTest() throws Throwable {	

		/*naviagte to restaurnat dropdown*/
		
		wLib.waitForPageToLoad(driver);
		System.out.println("=login=");
		
		String URL = System.getProperty("url" ,fLib.getDataFromPropertiesFile("url") );
		String USERNAME = System.getProperty("username" , fLib.getDataFromPropertiesFile("adminusername"));
		String PASSWORD = System.getProperty("password" , fLib.getDataFromPropertiesFile("password"));
		AdminloginPage lp = new AdminloginPage(driver);
		
		lp.loginToapp(URL, USERNAME, PASSWORD);
		
		/* navgate to restaurant*/
		DashboardPage dp= new DashboardPage(driver);
		dp.restaurant();
		String categoryname = eLib.getDataFromExcel("admin", 1, 0) + jLib.getRandomNumber();
		AddCategoryPage ac= new AddCategoryPage(driver);
		ac.addcatrgory(categoryname);
		
		String  category = eLib.getDataFromExcel("admin", 4, 0);
		 String Restaurnatname = eLib.getDataFromExcel("admin", 1, 1);
		 String Businesseamil = eLib.getDataFromExcel("admin", 1, 2); 
		 String phonenumber = eLib.getDataFromExcel("admin", 1, 3);
		 String websiteurl = eLib.getDataFromExcel("admin", 1, 4);
		 String  openhours = eLib.getDataFromExcel("admin", 1, 5);
		 String  closehours = eLib.getDataFromExcel("admin", 1, 6);
		 String  opendays = eLib.getDataFromExcel("admin", 1, 7);
		 String  RestaurantAddress = eLib.getDataFromExcel("admin", 1, 8);
		 AddrestaurantPage ap= new AddrestaurantPage(driver);
		 ap.getAddrestaurantlink().click();
		 ap.addrestaurant(Restaurnatname, Businesseamil, websiteurl, RestaurantAddress, phonenumber, openhours, closehours, opendays,category);
		 //verification headr
		 
		 String headr = ap.getHeadrinfo().getText();
		 System.out.println(headr);
		 String expected="New Restaurant Added Successfully.";
		 Assert.assertTrue(headr.contains(expected));
		 
		 Allrestaurantpage allres= new Allrestaurantpage(driver);
			allres.getAllrestaurantlink().click();
		
	String actualname = driver.findElement(By.xpath("//td[text()='"+Restaurnatname+"']")).getText();
	Assert.assertEquals(actualname, Restaurnatname);

	
	
	
	
	}

}
