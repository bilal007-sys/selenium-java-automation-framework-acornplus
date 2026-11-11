package com.acornautomation.tests;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.acornautomation.pages.ClientsPage;
import com.acornautomation.pages.SidebarPage;
import com.acornautomation.utilities.TestData;

public class ClientsTest extends BaseClass {
	
	ClientsPage clientsPage;
	SidebarPage sidebarPage;
	SoftAssert softAssert;
	
	public static final String errorEmailRequired = "Email is required";
	
	// Store client details for later verification
	public static final String firstName = TestData.getFirstName();
	public static final String lastName = TestData.getLastName();
	public static final String phone = TestData.getPhone();
	public static final String email = TestData.getEmail();
	public static boolean smsConsent;
	

    @Test(priority = 1, enabled = true, groups = {"Smoke"}, description = "Verify Add Client modal displays after clicking add client button")
    public void verifyAndClickAddClientButton() {
    	
    	clientsPage = new ClientsPage();
    	sidebarPage = new SidebarPage();
    	softAssert = new SoftAssert();
    	
    	reportStep("Click the Clients & Pets from sidebar menu");
    	sidebarPage.getSidebarMenuItemByText("Clients & Pets").click();
    	
    	reportStep("Click the Add Client button");
    	clientsPage.getAddClientButton().click();
    	
    	reportStep("Verify add client modal displays");
    	softAssert.assertTrue(clientsPage.getAddClientModal().isDisplayed(), "Error! Add client modal not opens");
    	
    	softAssert.assertAll();
    }
    
    @Test(dependsOnMethods = "verifyAndClickAddClientButton", groups = {"Smoke"}, description = "Verify first name, last name, phone, and email fields and enters data")
    public void verifyAndEnterName_Phone_Email() {
    	
    	reportStep("Enter client details");
    	clientsPage.enterClientDetails(firstName, lastName, phone, email);
    	
    	reportStep("Enter client notes");
    	clientsPage.getNotesTextArea().sendKeys(TestData.getParagraph());
    	
    	softAssert.assertAll();
    }
    
    @Test(dependsOnMethods = "verifyAndEnterName_Phone_Email", groups = {"Smoke"}, description = "Verify SMS preference checkbox and select randomly")
    public void verifyAndAcceptSMSPreferenceRandomly() {
    	
    	reportStep("Select SMS preference randomly");
    	
    	// Generate random true or false
    	smsConsent = new java.util.Random().nextBoolean();

    	// Select or leave unchecked based on random value
    	if (smsConsent) {
    		clientsPage.getSMSConsentCheckbox().click();
    	}

    	System.out.println("SMS Consent Selected = " + smsConsent);
    	
    	softAssert.assertAll();
    }
    
    @Test(dependsOnMethods = "verifyAndEnterName_Phone_Email", groups = {"Smoke"}, description = "Verify and click add client button at bottom")
    public void verifyAndClickAddClientButtonAtBottom() {
    	
    	reportStep("Click add client button");
    	clientsPage.getAddClientButtonAtBottom().click();
    	
//    	reportStep("Click cancel button");
//    	clientsPage.getCancelButtonAtBottom().click();
    	
    	System.out.println(firstName);
    	System.out.println(lastName);
    	System.out.println(phone);
    	System.out.println(email);

    	softAssert.assertAll();
    }
    
    @Test(dependsOnMethods = "verifyAndClickAddClientButtonAtBottom", groups = {"Smoke"}, description = "Get and print all client records at page")
    public void getAndPrintAllClientRecordsAtPage() {
    	
//    	reportStep("Click add client button");
//    	clientsPage.getAddClientButtonAtBottom().click();
    	
//    	reportStep("Click cancel button");
//    	clientsPage.getCancelButtonAtBottom().click();
    	
    	List<List<String>> clients = clientsPage.getClientRecords();

        System.out.println("Total clients displayed: " + clients.size());

        for (List<String> client : clients) {

            System.out.println("Name: " + client.get(0));
            System.out.println("Phone: " + client.get(1));
            System.out.println("Email: " + client.get(2));
            System.out.println("Date: " + client.get(3));
            System.out.println("Added By: " + client.get(4));
            System.out.println("SMS Status: " + client.get(5));

            System.out.println("--------------------------------");
        }

    	softAssert.assertAll();
    }
    
    @Test(dependsOnMethods = "verifyAndClickAddClientButtonAtBottom", groups = {"Smoke"}, description = "Verify newly created client is added at page or not")
    public void verifyNewClientAddedAtPage() {
    	
    	List<List<String>> clients = clientsPage.getClientRecords();
    	
    	String expectedName = firstName + " " + lastName;
    	String expectedSmsStatus = smsConsent ? "SMS Yes" : "SMS No";
    	
    	boolean clientFound = false;
    	
    	for (List<String> client : clients) {
    		
    		if (client.get(0).equalsIgnoreCase(expectedName)
    	            && clientsPage.normalizePhoneNumber(client.get(1)).equals(clientsPage.normalizePhoneNumber(phone))
    	            && client.get(2).equalsIgnoreCase(email)
    	            && client.get(5).equalsIgnoreCase(expectedSmsStatus)) {

            clientFound = true;
            break;
        }
    }
    	
    	softAssert.assertTrue(clientFound, "Either newly created client not added, or added with wrong details!");
    	
    	softAssert.assertAll();
    
    }

}
