package com.acornautomation.pages;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.acornautomation.tests.BaseClass;

public class AppointmentsPage extends BaseClass {
	
	public AppointmentsPage() {

		elementsInitializer();
	}
	
	
	
//	@FindBy(xpath = "//button[normalize-space()='Book appointment']")
//	WebElement btnBookAppointment;
	
//	Use following if button text changes (additional word added) e.g. Book appointment now / Book Appointment →
//	@FindBy(xpath = "//button[contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'book appointment')]")
//	private WebElement btnBookAppointment;
	
	@FindBy(xpath = "//button[translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = 'book appointment']")
	private WebElement btnBookAppointment;
	
	@FindBy(css = "div.VettyAppointments_book__CNZlg")
	private WebElement modalBookAppointment;
	
	@FindBy(xpath = "//label[.//span[normalize-space()='Date *']]//input")
	private WebElement inputFieldDate;
	
	@FindBy(xpath = "//label[.//span[normalize-space()='Service']]//select")
	private WebElement dropDownService;
	
	@FindBy(css = "input[placeholder='Search By Name, Phone Or Email']")
	private WebElement inputFieldClient;
	
//	Use following locator if placeholder of client input field in above locator keeps on changing from developer side multiple times
//	@FindBy(xpath = "//div[contains(@class,'VettyAppointments_field')][.//span[normalize-space()='Client *']]//input")
//	private WebElement inputFieldClient;
	
	@FindBy(xpath = "//ul[contains(@class,'VettyAppointments_owner_results')]//li/button")
	private List<WebElement> searchResultsClient;
	
	@FindBy(xpath = "//div[contains(@class,'VettyAppointments_field')][.//span[normalize-space()='Pet *']]//button[@aria-haspopup='listbox']")
	private WebElement dropDownButtonPet;

	@FindBy(xpath = "//div[contains(@class,'VettyAppointments_field')][.//span[normalize-space()='Pet *']]//ul[@role='listbox']/li[@role='option']")
	private List<WebElement> dropDownOptionsPet;
	
	@FindBy(xpath = "//button[contains(@class,'VettyAppointments_slot_pill')]")
	private List<WebElement> listOfAppointmentSlots;
	
	@FindBy(xpath = "//button[contains(@class,'VettyAppointments_submit_btn')]")
	private WebElement buttonBookAppointment;
	
	@FindBy(xpath = "//button[contains(@class,'VettyAppointments_cancel_btn')]")
	private WebElement buttonCancel;
	
	
		
	public WebElement getBookAppointmentButton() {

		waitForElementDisplayed(btnBookAppointment);
		return btnBookAppointment;
	}
	
	public WebElement getBookAppointmentModal() {

		waitForElementDisplayed(modalBookAppointment);
		return modalBookAppointment;
	}
	
	public WebElement getDateField() {

		waitForElementDisplayed(inputFieldDate);
		return inputFieldDate;
	}
	
	public WebElement getServiceDropdown() {

		waitForElementDisplayed(dropDownService);
		return dropDownService;
	}
	
	public void selectAppointmentServiceByValue(String value) {
	    Select service = new Select(getServiceDropdown());
	    service.selectByValue(value);
	}
	
	public WebElement getClientField() {

		waitForElementDisplayed(inputFieldClient);
		return inputFieldClient;
	}
	
	public WebElement getRandomDropdownOptionFromClientSearchResults() {

		waitForAllElementsDisplayed(searchResultsClient);
		return searchResultsClient.get(0);
	}
	
	public void selectPetByIndex(int index) {

	    // Open the custom dropdown
	    waitForElementClickable(dropDownButtonPet);
	    dropDownButtonPet.click();

	    // Wait for the dynamically rendered options
	    waitForAllElementsDisplayed(dropDownOptionsPet);

	    // Validate index and exclude the placeholder at index 0
	    if (index < 1 || index >= dropDownOptionsPet.size()) {
	        throw new IllegalArgumentException("Invalid pet index: " + index + ". Available options: " + (dropDownOptionsPet.size() - 1));
	    }

	    // Select the pet
	    dropDownOptionsPet.get(index).click();
	}
	
	public WebElement getRandomAppointmentSlot() {

		waitForAllElementsDisplayed(listOfAppointmentSlots);
		return listOfAppointmentSlots.get(0);
	}
	
	public WebElement getBookAppointmentButtonAtBottom() {

		waitForElementClickable(buttonBookAppointment);
		return buttonBookAppointment;
	}
	
	public WebElement getCancelButtonAtBottom() {

		waitForElementClickable(buttonCancel);
		return buttonCancel;
	}

}