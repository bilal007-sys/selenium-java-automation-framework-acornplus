package com.acornautomation.tests;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.acornautomation.pages.AppointmentsPage;
import com.acornautomation.pages.SidebarPage;

public class AppointmentsTest extends BaseClass {
	
	AppointmentsPage appointmentsPage;
	SidebarPage sidebarPage;
	SoftAssert softAssert;
	
	public static final String errorEmailRequired = "Email is required";

    @Test(priority = 1, enabled = true, groups = {"Smoke"}, description = "Verify book an appointment modal displays after clicking book appointment button")
    public void verifyAndClickBookAppointmentButton() {
    	
    	appointmentsPage = new AppointmentsPage();
    	sidebarPage = new SidebarPage();
    	softAssert = new SoftAssert();
    	
    	reportStep("Click the Appointments from sidebar menu");
    	sidebarPage.getSidebarMenuItemByText("Appointments").click();
    	
    	reportStep("Click the Book Appointment button");
    	appointmentsPage.getBookAppointmentButton().click();
    	
    	reportStep("Verify book appointment modal displays");
    	softAssert.assertTrue(appointmentsPage.getBookAppointmentModal().isDisplayed(), "Error! Book appointment modal not opens");
    	
    	softAssert.assertAll();
    }
    
    @Test(dependsOnMethods = "verifyAndClickBookAppointmentButton", groups = {"Smoke"}, description = "Verify Date, Service, and Veterinarian fields and enters data")
    public void verifyAndEnterDate_Service_Veterinarian() {
    	
    	reportStep("Enter appointment date");
    	appointmentsPage.getDateField().sendKeys("11/11/2026");
    	
    	softAssert.assertAll();
    }
    
    @Test(dependsOnMethods = "verifyAndClickBookAppointmentButton", groups = {"Smoke"}, description = "Verify Client field and enters data")
    public void verifyAndEnterClient() {
    	
    	reportStep("Enter client name");
    	appointmentsPage.getClientField().sendKeys("lee");
    	
    	reportStep("Select 'Lee Catherine' client");
    	appointmentsPage.getRandomDropdownOptionFromClientSearchResults().click();
    	
    	softAssert.assertAll();
    }
    
    @Test(dependsOnMethods = "verifyAndEnterClient", groups = {"Smoke"}, description = "Verify Pet field and selects a pet")
    public void verifyAndEnterPet() {
    	
    	reportStep("Click pet dropdown button");
    	reportStep("Selects a pet by index");
    	appointmentsPage.selectPetByIndex(1);
    	
    	softAssert.assertAll();
    }
    
    @Test(dependsOnMethods = "verifyAndEnterDate_Service_Veterinarian", groups = {"Smoke"}, description = "Verify appointment slots and selects a random time slot")
    public void verifyAndSelectRandomAppointmentSlot() {
    	
    	reportStep("Get random appointment slot");
    	WebElement pickedTimeSlot = appointmentsPage.getRandomAppointmentSlot();
    	
    	String pickedTimeSlotText = pickedTimeSlot.getText();
    	System.out.println("Picked Time Slot Is = " + pickedTimeSlotText);
    	
    	reportStep("Click random appointment slot");
    	pickedTimeSlot.click();  	
    	
    	softAssert.assertAll();
    }
    
    @Test(dependsOnMethods = "verifyAndSelectRandomAppointmentSlot", groups = {"Smoke"}, description = "Verify and click book appointment button at bottom")
    public void verifyAndClickBookAppointmentButtonAtBottom() {
    	
//    	reportStep("Click book appointment button at bottom");
//    	appointmentsPage.getBookAppointmentButtonAtBottom().click();
    	
    	reportStep("Click cancel button at bottom");
    	appointmentsPage.getCancelButtonAtBottom().click();

    	softAssert.assertAll();
    }

}
