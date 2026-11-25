package com.acornautomation.tests;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

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
	public static String expectedName = "";
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
    public void verifyAndEnterClientDetails() {
    	
    	reportStep("Enter client details");
    	clientsPage.enterClientDetails(firstName, lastName, phone, email);
    	
    	reportStep("Enter client notes");
    	clientsPage.getNotesTextArea().sendKeys(TestData.getParagraph());
    	
    	softAssert.assertAll();
    }
    
    @Test(dependsOnMethods = "verifyAndEnterClientDetails", groups = {"Smoke"}, description = "Verify SMS preference checkbox and select randomly")
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
    
    @Test(dependsOnMethods = "verifyAndEnterClientDetails", groups = {"Smoke"}, description = "Verify and click add client button at bottom")
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
    	
    	expectedName = firstName + " " + lastName;
    	String expectedSmsStatus = smsConsent ? "SMS Yes" : "SMS No";
    	String expectedAddedBy = "Manual";
    	
    	String expectedClientSince = LocalDate.now().format(DateTimeFormatter.ofPattern("MMM d, yyyy", Locale.ENGLISH));
    	
    	boolean clientFound = false;
    	
    	// First, check records displayed on the first page
        List<List<String>> clients = clientsPage.getClientRecords();
    	
    	for (List<String> client : clients) {
    		
    		if (client.get(0).equalsIgnoreCase(expectedName)
    	            && clientsPage.normalizePhoneNumber(client.get(1)).equals(clientsPage.normalizePhoneNumber(phone))
    	            && client.get(2).equalsIgnoreCase(email)
    	            && client.get(3).equals(expectedClientSince)
    	            && client.get(4).equalsIgnoreCase(expectedAddedBy)
    	            && client.get(5).equalsIgnoreCase(expectedSmsStatus)) {

            clientFound = true;
            break;
        }
    }
    	
    	// If client is not found on the first page, search by client name
        if (!clientFound) {

            System.out.println("Client not found on first page. Searching for: " + expectedName);

//            clientsPage.searchClientByName(firstName);
            clientsPage.searchClientByEmail(email);

            // Fetch filtered search results
            clients = clientsPage.getClientRecords();

            for (List<String> client : clients) {

                if (client.get(0).equalsIgnoreCase(expectedName)
                		&& clientsPage.normalizePhoneNumber(client.get(1)).equals(clientsPage.normalizePhoneNumber(phone))
                        && client.get(2).equalsIgnoreCase(email)
                        && client.get(3).equals(expectedClientSince)
                        && client.get(4).equalsIgnoreCase(expectedAddedBy)
                        && client.get(5).equalsIgnoreCase(expectedSmsStatus)) {

                    clientFound = true;
                    break;
                }
            }
        }
    	
    	softAssert.assertTrue(clientFound, "Either newly created client not added, or added with wrong details!");
    	
    	if (clientFound) {
            System.out.println("Newly created client verified successfully!");
        }
    	
    	softAssert.assertAll();
    
    }

    @Test(dependsOnMethods = "verifyNewClientAddedAtPage", groups = {"Smoke"}, description = "Verify the Client modal displays after clicking newly created client")
    public void clickAndVerifyClientModalOpens() {
    	
    	reportStep("Click newly created client record at page ");
    	clientsPage.getNewCreatedClientRecord(email).click();
    	
    	reportStep("Verify client details modal displays");
    	softAssert.assertTrue(clientsPage.getClientDetailsModal(), "Error! Client details modal not opens");
    	
    	softAssert.assertAll();
    }
    
    @Test(dependsOnMethods = "clickAndVerifyClientModalOpens", groups = {"Smoke"}, description = "Verify client modal details are correct")
    public void verifyClientModalDetailsCorrect() {
    	
    	String expectedSmsStatus = smsConsent ? "SMS Enabled" : "No SMS Consent";
    	String expectedClientSince = "Client Since " + LocalDate.now().format(DateTimeFormatter.ofPattern("MMM yyyy", Locale.ENGLISH));
    	
    	reportStep("Verify client details modal name matches correctly");
    	softAssert.assertEquals(clientsPage.getClientModalName(), expectedName, "Error! Client name mismatches");
    	System.out.println("BILAL EXP NAME = " + clientsPage.getClientModalName());
    	
    	reportStep("Verify client details modal phone matches correctly");
    	softAssert.assertEquals(clientsPage.getClientModalPhone(), "+1" + clientsPage.normalizePhoneNumber(phone), "Error! Client phone mismatches");
    	System.out.println("BILAL EXP PHONE = " + clientsPage.getClientModalPhone());
    	
    	reportStep("Verify client details modal email matches correctly");
    	softAssert.assertEquals(clientsPage.getClientModalEmail(), email, "Error! Client email mismatches");
    	System.out.println("BILAL EXP EMAIL = " + clientsPage.getClientModalEmail());
    	
    	reportStep("Verify client details modal sms status matches correctly");
    	softAssert.assertEquals(clientsPage.getClientModalSmsStatus(), expectedSmsStatus, "Error! SMS consent status mismatches");
    	System.out.println("BILAL EXP SMS = " + clientsPage.getClientModalSmsStatus());
    	
    	reportStep("Verify client details modal month & year matches correctly");
    	softAssert.assertEquals(clientsPage.getClientModalSince(), expectedClientSince, "Error! Client creation month mismatches");
    	System.out.println("BILAL EXP MONTH & YEAR = " + clientsPage.getClientModalSince());

    	softAssert.assertAll();
    }

    @Test(dependsOnMethods = "clickAndVerifyClientModalOpens", groups = {"Smoke"}, description = "Verify add new pet modal displays after clicking Add Pet button")
    public void clickAndVerifyAddNewPetModalOpens() {
    	
    	reportStep("Click the Add Pet button");
    	clientsPage.getAddPetButtonAtClientDetailsModal().click();
    	
    	reportStep("Verify add new pet modal displays");
    	softAssert.assertTrue(clientsPage.getNewPetModal(), "Error! New pet modal not opens");
    	
    	softAssert.assertAll();
    }

}
