package com.acornautomation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.acornautomation.tests.BaseClass;

public class LoginPage extends BaseClass {
	
	public LoginPage() {

		elementsInitializer();
	}
	
	
	@FindBy(name = "email")
	WebElement inputFieldEmail;
	
	@FindBy(name = "password")
	WebElement inputFieldPassword;
	
	@FindBy(css = ".Forms_btn__1eRoW.BlueButton_theme_btn3__xJu5I")
	WebElement buttonLogin;
	
	
	
//	public LoginPage(WebDriver driver) {
//
//        this.driver = driver;
//    }
	
	
	
	public void setEmail(String email) {

		this.clearFieldData(inputFieldEmail);
		inputFieldEmail.sendKeys(email);
	}

	public void setPassword(String password) {

		clearFieldData(inputFieldPassword);
		inputFieldPassword.sendKeys(password);
	}
	
	public WebElement getLoginButton() {

		return buttonLogin;
	}
	
	/*
	public WebElement getInputFieldPassword() {

		return inputFieldPassword;

	}

	public void submitPassword() {

		inputFieldPassword.submit();

	} */

}
