package com.acornautomation.pages;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import com.acornautomation.tests.BaseClass;

public class SidebarPage extends BaseClass {
	
	public SidebarPage() {

		elementsInitializer();
	}
	
	
	
	@FindBy(id = "all_dropdown_set")
	WebElement searchBarCategoryDDMenu;
	
	@FindBy(css = ".Header_upgrade__Mj19B.BlueButton_theme_btn3__xJu5I")
	WebElement btnProfileHeader;
	
	@FindBy(xpath = "/html/body/header/div/div/div[3]/div/div/div[2]/div/a[6]")
	WebElement VettyAILink;
	
	
	
	@FindBy(className = "VettyNav_list__D7bHq")
	WebElement sideBarMenu;
	
	@FindBy(className = "VettyNav_link__eWSzh")
	private List<WebElement> sideBarMenuItems;
	
	
	
	public WebElement getSidebarMenu() {

		waitForElementDisplayed(sideBarMenu);
		return sideBarMenu;
	}
	
	public List<WebElement> getSidebarMenuItems() {

		waitForAllElementsDisplayed(sideBarMenuItems);
		return sideBarMenuItems;
	}
	
	public WebElement getSidebarMenuItemByText(String sideBarMenuItemName) {
		
		List<WebElement> listOfSidebarMenuItems = getSidebarMenuItems();

	    for (WebElement singleSidebarMenuItem : listOfSidebarMenuItems) {
	    	
	        if (singleSidebarMenuItem.getText().trim().equals(sideBarMenuItemName)) {
	            return singleSidebarMenuItem;
	        }
	    }

	    throw new NoSuchElementException("Menu item not found: " + sideBarMenuItemName);
	}
	
}
