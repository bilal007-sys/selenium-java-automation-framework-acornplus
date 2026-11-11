package com.acornautomation.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.acornautomation.tests.BaseClass;

public class ClientsPage extends BaseClass {
	
	public ClientsPage() {

		elementsInitializer();
	}
	
	
	
	@FindBy(xpath = "//button[normalize-space()='Add Client']")
	private WebElement buttonAddClient;

	@FindBy(xpath = "//button[normalize-space()='Add Pet']")
	private WebElement buttonAddPet;
	
	@FindBy(xpath = "//form[contains(@class,'VettyClients_client_dialog')]//h2[normalize-space()='Add Client']")
	private WebElement modalAddClientTitle;
	
	@FindBy(name = "firstName")
	private WebElement inputFieldFirstName;

	@FindBy(name = "lastName")
	private WebElement inputFieldLastName;

	@FindBy(name = "phone")
	private WebElement inputFieldPhone;

	@FindBy(name = "email")
	private WebElement inputFieldEmail;
	
	@FindBy(name = "smsOptIn")
	private WebElement checkboxSMSConsent;
	
	@FindBy(name = "notes")
	private WebElement textAreaNotes;
	
	@FindBy(xpath = "//button[contains(@class,'VettyClients_btn_submit')]")
	private WebElement buttonAddClientAtBottom;
	
	@FindBy(xpath = "//button[contains(@class,'VettyClients_btn_cancel')]")
	private WebElement buttonCancel;
	
	@FindBy(xpath = "//tr[contains(@class,'VettyClients_table_row')]")
	private List<WebElement> clientRows;

	// Client Name
	@FindBy(xpath = "//tr[contains(@class,'VettyClients_table_row')]//td[1]//span[contains(@class,'client_name')]")
	private List<WebElement> clientNames;

	// Phone Numbers
	@FindBy(xpath = "//tr[contains(@class,'VettyClients_table_row')]//td[2]//div[contains(@class,'contact_item')][1]/span")
	private List<WebElement> clientPhones;

	// Email Addresses
	@FindBy(xpath = "//tr[contains(@class,'VettyClients_table_row')]//td[2]//div[contains(@class,'contact_item')][2]/span")
	private List<WebElement> clientEmails;

	// Date Added
	@FindBy(xpath = "//tr[contains(@class,'VettyClients_table_row')]//td[3]")
	private List<WebElement> clientDates;

	// Added By
	@FindBy(xpath = "//tr[contains(@class,'VettyClients_table_row')]//td[4]")
	private List<WebElement> clientAddedBy;

	// SMS Status
	@FindBy(xpath = "//tr[contains(@class,'VettyClients_table_row')]//td[5]")
	private List<WebElement> clientSmsStatus;
	
	
	
	public WebElement getAddClientButton() {

		waitForElementClickable(buttonAddClient);
		return buttonAddClient;
	}
	
	public WebElement getAddClientModal() {

		waitForElementDisplayed(modalAddClientTitle);
		return modalAddClientTitle;
	}
	
	public void enterClientDetails(String firstName, String lastName, String phone, String email) {
		
		inputFieldFirstName.sendKeys(firstName);
		inputFieldLastName.sendKeys(lastName);
		inputFieldPhone.sendKeys(phone);
		inputFieldEmail.sendKeys(email);
	}
	
	public WebElement getSMSConsentCheckbox() {

		waitForElementClickable(checkboxSMSConsent);
		return checkboxSMSConsent;
	}
	
	public WebElement getNotesTextArea() {

		waitForElementDisplayed(textAreaNotes);
		return textAreaNotes;
	}
	
	public WebElement getAddClientButtonAtBottom() {

		waitForElementClickable(buttonAddClientAtBottom);
		return buttonAddClientAtBottom;
	}
	
	public WebElement getCancelButtonAtBottom() {

		waitForElementClickable(buttonCancel);
		return buttonCancel;
	}
	
	public List<List<String>> getClientRecords() {

    List<List<String>> allClientRecords = new ArrayList<>();

    waitForAllElementsDisplayed(clientRows);

    for (int i = 0; i < clientRows.size(); i++) {

        List<String> clientDetails = new ArrayList<>();

        clientDetails.add(clientNames.get(i).getText().trim());
        clientDetails.add(clientPhones.get(i).getText().trim());
        clientDetails.add(clientEmails.get(i).getText().trim());
        clientDetails.add(clientDates.get(i).getText().trim());
        clientDetails.add(clientAddedBy.get(i).getText().trim());
        clientDetails.add(clientSmsStatus.get(i).getText().trim());

        allClientRecords.add(clientDetails);
    }
    
    	return allClientRecords;
    }
	
	public String normalizePhoneNumber(String phone) {
	    String number = phone.replaceAll("[^0-9]", "");

	    if (number.length() == 11 && number.startsWith("1")) {
	        number = number.substring(1);
	    }

	    return number;
	}

}