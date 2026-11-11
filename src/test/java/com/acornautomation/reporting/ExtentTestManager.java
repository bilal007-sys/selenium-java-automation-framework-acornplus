package com.acornautomation.reporting;

import com.aventstack.extentreports.ExtentTest;

public final class ExtentTestManager {
	
	private static final ThreadLocal<ExtentTest> extentTest =
            new ThreadLocal<>();

    private ExtentTestManager() {
    }

    public static void setTest(ExtentTest test) {

        extentTest.set(test);
    }

    public static ExtentTest getTest() {

        return extentTest.get();
    }

    public static void removeTest() {

        extentTest.remove();
    }

}
