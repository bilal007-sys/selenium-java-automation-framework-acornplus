package com.acornautomation.tests;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import com.acornautomation.reporting.ExtentReportListener;
import com.acornautomation.reporting.ExtentTestManager;

@Listeners(ExtentReportListener.class)
public class BaseClass {
	
//	protected WebDriver driver;
	protected static WebDriver driver;
    protected static JavascriptExecutor jsExecutor;
    protected String browser = "chrome";
//    protected String baseURL = "https://voice-note-dev-7wpsc.ondigitalocean.app/";
    protected String baseURL = "https://voice-note-live-d6qpq.ondigitalocean.app/";
    protected static WebDriverWait wait;
    
    public static WebDriver getDriver() {

        return driver;
    }


    // ============================================================
    // SETUP
    // ============================================================

    @BeforeSuite(alwaysRun = true)
    public void setup() {

        try {

            if (browser.equalsIgnoreCase("chrome")) {
                driver = new ChromeDriver();
            } else if (browser.equalsIgnoreCase("firefox")) {
                driver = new FirefoxDriver();
            } else if (browser.equalsIgnoreCase("edge")) {
                driver = new EdgeDriver();
            } else {
                throw new IllegalArgumentException(
                        "Unsupported browser: " + browser
                );
            }

            driver.manage().window().maximize();
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            jsExecutor = (JavascriptExecutor) driver;
            driver.get(baseURL);

        } catch (Exception e) {

            System.out.println("Unable to initialize browser.");
            System.out.println("Cause: " + e.getCause());
            System.out.println("Message: " + e.getMessage());

            e.printStackTrace();

            throw e;
        }
    }


    // ============================================================
    // PAGE FACTORY INITIALIZER
    // ============================================================
    
    @BeforeClass
    public void elementsInitializer() {

        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }


    // ============================================================
    // JAVASCRIPT METHODS
    // ============================================================

    public boolean jsPageLoadingStatus() {

        try {

            String pageStatus = (String) jsExecutor.executeScript("return document.readyState");
            return pageStatus.equals("complete");

        } catch (Exception e) {

            System.out.println("Unable to determine page loading status: " + e.getMessage());
            return false;
        }
    }


    public void jsPageScroller(WebElement element) {

        jsExecutor.executeScript("arguments[0].scrollIntoView({block:'center'});", element);
    }


    public void jsElementClicker(WebElement element) {

        jsExecutor.executeScript("arguments[0].click();", element);
    }


    public void jsClear(WebElement element) {

        jsExecutor.executeScript("arguments[0].value = '';", element);
    }


    // ============================================================
    // FIELD METHODS
    // ============================================================

    public void clearFieldData(WebElement element) {

        element.sendKeys(Keys.CONTROL, "a");
        element.sendKeys(Keys.BACK_SPACE);
    }


    public void clearPhoneFieldData(WebElement element) {

        element.sendKeys(Keys.CONTROL, "a");
        element.sendKeys(Keys.BACK_SPACE);
    }


    public int[] getMinMaxCharacterLength(WebElement element) {

        int[] charLength = new int[2];

        String minLength = element.getAttribute("minlength");
        String maxLength = element.getAttribute("maxlength");

        if (minLength != null && !minLength.isEmpty()) {
            charLength[0] = Integer.parseInt(minLength);

        } else {
            charLength[0] = 0;
        }

        if (maxLength != null && !maxLength.isEmpty()) {
            charLength[1] = Integer.parseInt(maxLength);

        } else {
            charLength[1] = 0;
        }

        System.out.println("Minimum character length: " + charLength[0]);
        System.out.println("Maximum character length: " + charLength[1]);

        return charLength;
    }


    // ============================================================
    // WAIT METHODS
    // ============================================================

    public WebDriverWait myWait(Duration timeout) {

        return new WebDriverWait(driver, timeout);
    }


    public void waitForElementVisible(WebElement element) {

        wait.until(ExpectedConditions.visibilityOf(element));
    }


    public void waitForElementClickable(WebElement element) {

        wait.until(ExpectedConditions.elementToBeClickable(element));
    }


    public boolean waitForElementDisplayed(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;

        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean waitForAllElementsDisplayed(List<WebElement> elements) {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(elements));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    


    // ============================================================
    // ELEMENT METHODS
    // ============================================================

    public boolean isWebElementDisplayed(WebElement element) {

        try {
            return element.isDisplayed();

        } catch (
                NoSuchElementException |
                org.openqa.selenium.StaleElementReferenceException e) {

            return false;
        }
    }


    // ============================================================
    // EXTENT REPORT METHODS
    // ============================================================

    public void reportStep(String message) {

        if (ExtentTestManager.getTest() != null) {

            ExtentTestManager.getTest().info(message);
        }
    }


    // ============================================================
    // RANDOM DATA GENERATORS
    // ============================================================

    public String randomNumberGenerator() {

        String digits = "1234567890";
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        int length = 10;

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(digits.length());
            stringBuilder.append(digits.charAt(index));
        }

        return stringBuilder.toString();
    }


    public String randomStringGenerator() {

        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvwxyz";
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        int length = 10;

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(alphabet.length());
            stringBuilder.append(alphabet.charAt(index));
        }

        return stringBuilder.toString();
    }


    public String randomEmailGenerator() {

        return "testuser" + randomNumberGenerator() + "@gmail.com";
    }


    // ============================================================
    // TEARDOWN
    // ============================================================

    @AfterSuite(alwaysRun = true)
    public void tearDown() {

        try {

            if (driver != null) {

//                driver.quit();
//                driver.close();
            }

        } catch (Exception e) {

            System.out.println("Error while closing browser: " + e.getMessage());
        }
    }

}
