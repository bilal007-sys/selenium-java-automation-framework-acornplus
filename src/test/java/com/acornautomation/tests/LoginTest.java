package com.acornautomation.tests;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.acornautomation.pages.HeaderPage;
import com.acornautomation.pages.LoginPage;
import com.acornautomation.pages.SidebarPage;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;

public class LoginTest extends BaseClass {
	
	LoginPage loginPage;
	HeaderPage headerPage;
	SidebarPage sidebarPage;
	SoftAssert softAssert;
	
	public static final String errorEmailRequired = "Email is required";
	public static final String errorPasswordRequired = "Password is required";
	public static final String errorMalformedEmail = "Email is invalid";

    @Test(priority = 1, enabled = true, groups = {"Login"}, description = "Verify user can login successfully")
    public void verifyLoginSuccessfully() {
    	
    	loginPage = new LoginPage();
    	headerPage = new HeaderPage();
    	sidebarPage = new SidebarPage();
    	softAssert = new SoftAssert();
    	
    	reportStep("Click Login button at header");
    	headerPage.getLoginButtonHeader().click();
    	
    	reportStep("Enter email address");
    	loginPage.setEmail("ejohnson@premierhomevetcare.com");
    	
    	reportStep("Enter password");
    	loginPage.setPassword("123123@Aa");
    	
    	reportStep("Click sign in button");
    	loginPage.getLoginButton().click();
    	
    	softAssert.assertAll();
    }
    
    @Test(dependsOnMethods = "verifyLoginSuccessfully", groups = {"Login"}, description = "Verify profile dropdown can be opened after login")
    public void verifyAndClickHeaderProfileButton() {
    	
    	reportStep("Verify header profile button displays");
    	softAssert.assertTrue(headerPage.getProfileButtonHeader().isEnabled(), "Error! Header profile button not displayed/enabled.");
    	
    	reportStep("Click profile dropdown button");
    	headerPage.getProfileButtonHeader().click();
    	
    	reportStep("Click Vetty AI link");
    	headerPage.getProfileDropdownMenuItemByText("VettyAI").click();
    	
    	softAssert.assertAll();
    }
    
    @Test(dependsOnMethods = "verifyAndClickHeaderProfileButton", groups = {"Smoke"}, description = "Verify sidebar menu displays at left after clicking VettyAI link from profile dropdown")
    public void verifyAndClickMenuItemAppointments() {
    	
    	reportStep("Verify sidebar menu displays");
    	softAssert.assertTrue(sidebarPage.getSidebarMenu().isDisplayed(), "Error! Sidebar menu not displayed at left side.");
    	
    	reportStep("Get list of sidebar menu items");
    	List<WebElement> sideBarMenuItems = sidebarPage.getSidebarMenuItems();
    	
    	reportStep("Verify sidebar menu list of items are 9");
    	softAssert.assertEquals(sideBarMenuItems.size(), 9, "Error! Size of side bar menu items is not 9.");
    	
    	softAssert.assertAll();
    }

}
