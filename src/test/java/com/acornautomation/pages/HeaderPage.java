package com.acornautomation.pages;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import com.acornautomation.tests.BaseClass;

public class HeaderPage extends BaseClass {
	
	public HeaderPage() {

		elementsInitializer();
	}
	
	
	
	@FindBys(@FindBy(xpath = "//*[@id=\"popover-contained\"]/div[2]/span/a"))
	List<WebElement> profileDDMenu;
	
	@FindBy(id = "all_dropdown_set")
	WebElement searchBarCategoryDDMenu;
	
	@FindBy(className = "SearchHeader_advance_search__jvNHt")
	WebElement btnSearch;
	
	
	
	@FindBy(css = ".Header_login_icon_wrapper__m8XcH")
	WebElement btnLoginHeader;
	
	@FindBy(css = ".Header_upgrade__Mj19B.BlueButton_theme_btn3__xJu5I")
	WebElement btnProfileHeader;
	
	@FindBy(className = "ProfileMenu_profile_link__60C52")
	private List<WebElement> profileDropdownMenuItems;
	
	
	
	public WebElement getLoginButtonHeader() {

		waitForElementClickable(btnLoginHeader);
		return btnLoginHeader;
	}
	
	public WebElement getProfileButtonHeader() {

		waitForElementClickable(btnProfileHeader);
		return btnProfileHeader;
	}
	
	public List<WebElement> getProfileDropdownMenuItems() {

		waitForAllElementsDisplayed(profileDropdownMenuItems);
		return profileDropdownMenuItems;
	}
	
	public WebElement getProfileDropdownMenuItemByText(String profileDropdownMenuItemName) {
		
		List<WebElement> listOfProfileDropdownMenuItems = getProfileDropdownMenuItems();

	    for (WebElement singleProfileDropdownMenuItem : listOfProfileDropdownMenuItems) {
	    	
	        if (singleProfileDropdownMenuItem.getText().trim().equals(profileDropdownMenuItemName)) {
	            return singleProfileDropdownMenuItem;
	        }
	    }

	    throw new NoSuchElementException("Menu item not found: " + profileDropdownMenuItemName);
	}

}
