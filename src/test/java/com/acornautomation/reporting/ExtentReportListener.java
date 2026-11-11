package com.acornautomation.reporting;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.acornautomation.tests.BaseClass;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ExtentReportListener implements ITestListener {
	
	private final ExtentReports extent = ExtentManager.getExtentReports();


    @Override
    public void onTestStart(ITestResult result) {

    // Get the actual test class name
    String className = result.getTestClass().getRealClass().getSimpleName();

    // Get test method's qualified name
    String testName = result.getMethod().getQualifiedName();

    // Create ExtentTest instance
    ExtentTest test = extent.createTest(testName);

    // Add class name as a category
    test.assignCategory(className);

    // Add TestNG description if one exists
    String description = result.getMethod().getDescription();

    if (description != null && !description.trim().isEmpty()) {
        test.info(description);
    }

    // Add existing TestNG groups/categories
    String[] groups = result.getMethod().getGroups();

    if (groups != null) {
        for (String group : groups) {
            test.assignCategory(group);
        }
    }

    // Store ExtentTest instance
    ExtentTestManager.setTest(test);
}


    @Override
    public void onTestSuccess(ITestResult result) {

        ExtentTest test = ExtentTestManager.getTest();

        if (test != null) {

            test.pass("Test Case Passed");
        }

        ExtentTestManager.removeTest();
    }


    @Override
    public void onTestFailure(ITestResult result) {

        ExtentTest test = ExtentTestManager.getTest();

        if (test != null) {

            Throwable throwable = result.getThrowable();

            if (throwable != null) {

                test.fail(throwable);
            } else {

                test.fail("Test Case Failed");
            }


            // Capture screenshot
            String screenshotPath = captureScreenshot(result.getMethod().getMethodName());

            if (screenshotPath != null) {

                try {

                    test.addScreenCaptureFromPath(screenshotPath);

                } catch (Exception e) {

                    test.warning("Unable to attach screenshot: " + e.getMessage());
                }
            }
        }

        ExtentTestManager.removeTest();
    }


    @Override
    public void onTestSkipped(ITestResult result) {

        ExtentTest test = ExtentTestManager.getTest();

        if (test != null) {

            Throwable throwable = result.getThrowable();

            if (throwable != null) {

                test.skip(throwable);

            } else {

                test.skip("Test Case Skipped");
            }
        }

        ExtentTestManager.removeTest();
    }


    @Override
    public void onFinish(ITestContext context) {

        extent.flush();
    }


    // ============================================================
    // SCREENSHOT
    // ============================================================

    private String captureScreenshot(String testName) {

        WebDriver driver = BaseClass.getDriver();

        if (driver == null) {

            return null;
        }

        if (!(driver instanceof TakesScreenshot)) {

            return null;
        }

        try {

            String screenshotDirectory = System.getProperty("user.dir") + "/test-output/ExtentReport/screenshots";

            Path directory = Paths.get(screenshotDirectory);

            Files.createDirectories(directory);

            String fileName = testName + "_" + System.currentTimeMillis() + ".png";

            Path destination = directory.resolve(fileName);

            File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            Files.copy(source.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);

            return destination.toString();

        } catch (IOException e) {

            System.out.println("Screenshot capture failed: " + e.getMessage());

            return null;
        }
    }

}
