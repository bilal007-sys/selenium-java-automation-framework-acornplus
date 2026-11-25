package com.acornautomation.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

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
	private List<WebElement> clientRowsRecord;

	// Client Name
	@FindBy(xpath = "//tr[contains(@class,'VettyClients_table_row')]//td[1]//span[contains(@class,'client_name')]")
	private List<WebElement> clientNamesRecord;

	// Phone Numbers
	@FindBy(xpath = "//tr[contains(@class,'VettyClients_table_row')]//td[2]//div[contains(@class,'contact_item')][1]/span")
	private List<WebElement> clientPhonesRecord;

	// Email Addresses
	@FindBy(xpath = "//tr[contains(@class,'VettyClients_table_row')]//td[2]//div[contains(@class,'contact_item')][2]/span")
	private List<WebElement> clientEmailsRecord;

	// Date Added
	@FindBy(xpath = "//tr[contains(@class,'VettyClients_table_row')]//td[3]")
	private List<WebElement> clientDatesRecord;

	// Added By
	@FindBy(xpath = "//tr[contains(@class,'VettyClients_table_row')]//td[4]")
	private List<WebElement> clientAddedByRecord;

	// SMS Status
	@FindBy(xpath = "//tr[contains(@class,'VettyClients_table_row')]//td[5]")
	private List<WebElement> clientSmsStatusRecord;
	
	@FindBy(xpath = "//input[contains(@placeholder,'Search By Name, Phone Or Email...')]")
	private WebElement inputFieldClientSearch;
	
	// Client Details Modal
	@FindBy(xpath = "//div[contains(@class,'client_modal_wrapper')]")
	private WebElement clientDetailsModal;

	@FindBy(xpath = "//h2[contains(@class,'detail_name')]")
	private WebElement clientDetailsModalName;

	@FindBy(xpath = "//span[contains(@class,'client_since_badge')]")
	private WebElement clientDetailsModalSince;

	@FindBy(xpath = "//div[contains(@class,'info_row')][.//span[normalize-space()='Phone']]//span[contains(@class,'info_val')]")
	private WebElement clientDetailsModalPhone;

	@FindBy(xpath = "//div[contains(@class,'info_row')][.//span[normalize-space()='Email']]//span[contains(@class,'info_val')]")
	private WebElement clientDetailsModalEmail;

	@FindBy(xpath = "//div[contains(@class,'info_row')][.//span[normalize-space()='Messaging']]//span[contains(@class,'messaging_val')]/span[last()]")
	private WebElement clientDetailsModalSmsStatus;

	// Add Pet Button At Client Modal
	@FindBy(xpath = "//button[contains(@class,'btn_add_pet_link') and normalize-space()='Add Pet']")
	private WebElement buttonAddPetAtClientDetailsModal;
	
	/*
	 * Above all locators are related to Client; client page, client modal, client search etc.
	 * Below all locators are related to Pet; pet page, pet modal etc.
	 */
	
	// Pet Form
	@FindBy(xpath = "//form[contains(@class,'pet_dialog')]")
	private WebElement addNewPetModal;

	// Pet Form Fields
	@FindBy(xpath = "//form[contains(@class,'pet_dialog')]//input[@placeholder='Enter pet ID']")
	private WebElement inputFieldPetId;

	@FindBy(xpath = "//form[contains(@class,'pet_dialog')]//input[@placeholder='Enter pet name']")
	private WebElement inputFieldPetName;

	@FindBy(xpath = "//form[contains(@class,'pet_dialog')]//input[@placeholder='Enter breed name']")
	private WebElement inputFieldPetBreed;

	@FindBy(xpath = "//form[contains(@class,'pet_dialog')]//input[@placeholder='e.g. Up to date | rabies Jan 2026']")
	private WebElement inputFieldVaccinationStatus;

//	@FindBy(xpath = "//form[contains(@class,'pet_dialog')]//input[@placeholder='Enter owner phone number']")
//	private WebElement inputOwnerPhone;

	@FindBy(xpath = "//form[contains(@class,'pet_dialog')]//input[@placeholder='Name, address and phone etc']")
	private WebElement inputFieldEmergencyContact;

	// Pet Species Dropdown
	@FindBy(xpath = "//form[contains(@class,'pet_dialog')]//input[@placeholder='Select a species']")
	private WebElement dropdownSpecies;

	// Pet Sex Options
	@FindBy(xpath = "//form[contains(@class,'pet_dialog')]//div[contains(@class,'sex_pills_container')]//button[contains(@class,'sex_pill')]")
	private List<WebElement> petSexOptions;

	@FindBy(xpath = "//form[contains(@class,'pet_dialog')]//button[contains(@class,'btn_submit')]")
	private WebElement buttonCreatePet;

	@FindBy(xpath = "//form[contains(@class,'pet_dialog')]//button[contains(@class,'btn_cancel')]")
	private WebElement buttonCancelPet;
	
	
	
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

	    return wait.until(driver -> {

	        try {
	            List<List<String>> allClientRecords = new ArrayList<>();

	            waitForAllElementsDisplayed(clientRowsRecord);

	            for (int i = 0; i < clientRowsRecord.size(); i++) {

	                List<String> clientDetails = new ArrayList<>();

	                clientDetails.add(clientNamesRecord.get(i).getText().trim());
	                clientDetails.add(clientPhonesRecord.get(i).getText().trim());
	                clientDetails.add(clientEmailsRecord.get(i).getText().trim());
	                clientDetails.add(clientDatesRecord.get(i).getText().trim());
	                clientDetails.add(clientAddedByRecord.get(i).getText().trim());
	                clientDetails.add(clientSmsStatusRecord.get(i).getText().trim());

	                allClientRecords.add(clientDetails);
	            }

	            return allClientRecords;

	        } catch (StaleElementReferenceException e) {
	            return null;
	        }
	    });
	}
	
	public String normalizePhoneNumber(String phone) {
		
	    String number = phone.replaceAll("[^0-9]", "");

	    if (number.length() == 11 && number.startsWith("1")) {
	        number = number.substring(1);
	    }

	    return number;
	}
	
	public void searchClientByName(String clientName) {

	    waitForElementDisplayed(inputFieldClientSearch);

	    inputFieldClientSearch.clear();
	    inputFieldClientSearch.sendKeys(clientName);

	    wait.until(ExpectedConditions.attributeToBe(inputFieldClientSearch, "value", clientName));

	    wait.until(driver -> {
	        try {
	            for (WebElement client : clientNamesRecord) {

	                if (client.getText().trim().toLowerCase().contains(clientName.trim().toLowerCase())) {
	                    return true;
	                }
	            }

	            return clientNamesRecord.isEmpty();

	        } catch (StaleElementReferenceException e) {
	            return false;
	        }
	    });
	}
	
	public void searchClientByEmail(String email) {

	    waitForElementDisplayed(inputFieldClientSearch);

	    inputFieldClientSearch.clear();
	    inputFieldClientSearch.sendKeys(email);

	    wait.until(ExpectedConditions.attributeToBe(inputFieldClientSearch, "value", email));

	    wait.until(driver -> {
	        try {
	            for (WebElement client : clientEmailsRecord) {

	                if (client.getText().trim().equalsIgnoreCase(email.trim())) {
	                    return true;
	                }
	            }

	            return clientEmailsRecord.isEmpty();

	        } catch (StaleElementReferenceException e) {
	            return false;
	        }
	    });
	}
	
	public WebElement getNewCreatedClientRecord(String email) {

	    waitForAllElementsDisplayed(clientRowsRecord);

	    for (int i = 0; i < clientEmailsRecord.size(); i++) {

	        if (clientEmailsRecord.get(i).getText().trim().equalsIgnoreCase(email.trim())) {

	            waitForElementClickable(clientRowsRecord.get(i));
	            return clientRowsRecord.get(i);
	        }
	    }

	    throw new NoSuchElementException("Client record not found: " + email);
	}
	
	public boolean getClientDetailsModal() {
		
		try {
			return waitForElementDisplayed(clientDetailsModal);
	    } catch (TimeoutException e) {
	        return false;
	    }

	}
	
	public String getClientModalName() {
		
	    waitForElementDisplayed(clientDetailsModalName);
	    return clientDetailsModalName.getText().trim();
	}

	public String getClientModalPhone() {
		
	    waitForElementDisplayed(clientDetailsModalPhone);
	    return clientDetailsModalPhone.getText().trim();
	}

	public String getClientModalEmail() {
		
	    waitForElementDisplayed(clientDetailsModalEmail);
	    return clientDetailsModalEmail.getText().trim();
	}

	public String getClientModalSmsStatus() {
		
	    waitForElementDisplayed(clientDetailsModalSmsStatus);
	    return clientDetailsModalSmsStatus.getText().trim();
	}

	public String getClientModalSince() {
		
	    waitForElementDisplayed(clientDetailsModalSince);
	    return clientDetailsModalSince.getText().trim();
	}
	
	public WebElement getAddPetButtonAtClientDetailsModal() {

	    waitForElementClickable(buttonAddPetAtClientDetailsModal);
	    return buttonAddPetAtClientDetailsModal;
	}
	
	public boolean getNewPetModal() {
		
	    try {
	        return waitForElementDisplayed(addNewPetModal);
	    } catch (TimeoutException e) {
	        return false;
	    }
	}
	
	public void enterPetId(String petId) {
		
	    waitForElementDisplayed(inputFieldPetId);
	    inputFieldPetId.sendKeys(petId);
	}

	public void enterPetName(String petName) {
		
	    waitForElementDisplayed(inputFieldPetName);
	    inputFieldPetName.sendKeys(petName);
	}

	public void enterPetBreed(String breed) {
		
	    waitForElementDisplayed(inputFieldPetBreed);
	    inputFieldPetBreed.sendKeys(breed);
	}

	public void enterPetVaccinationStatus(String vaccinationStatus) {
		
	    waitForElementDisplayed(inputFieldVaccinationStatus);
	    inputFieldVaccinationStatus.sendKeys(vaccinationStatus);
	}

//	public void enterOwnerPhone(String phone) {
	
//	    waitForElementDisplayed(inputOwnerPhone);
//	    inputOwnerPhone.sendKeys(phone);
//	}

	public void enterPetEmergencyContact(String emergencyContact) {
		
	    waitForElementDisplayed(inputFieldEmergencyContact);
	    inputFieldEmergencyContact.sendKeys(emergencyContact);
	}
	
	public WebElement getPetSpeciesDropdown() {

	    waitForElementClickable(dropdownSpecies);
	    return dropdownSpecies;
	}
	
	public void selectPetSex(String sex) {

	    waitForAllElementsDisplayed(petSexOptions);

	    for (WebElement option : petSexOptions) {

	        if (option.getText().trim().endsWith(sex)) {
	            wait.until(ExpectedConditions.elementToBeClickable(option)).click();
//	            waitForElementClickable(option).click();
	            return;
	        }
	    }

	    throw new NoSuchElementException("Pet sex option not found: " + sex);
	}
	
	public void getCreatePetButton() {
	    wait.until(ExpectedConditions.elementToBeClickable(buttonCreatePet)).click();
//	    waitForElementClickable(buttonCreatePet).click();
	}

	public void getCancelPetButton() {
	    wait.until(ExpectedConditions.elementToBeClickable(buttonCancelPet)).click();
//	    waitForElementClickable(buttonCancelPet).click();
	}

}