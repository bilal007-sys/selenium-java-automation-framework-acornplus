package com.acornautomation.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public final class ExtentManager {
	
	private static ExtentReports extent;

    private ExtentManager() {
    }

    public static synchronized ExtentReports getExtentReports() {

        if (extent == null) {

            String reportPath = System.getProperty("user.dir") + "/test-output/ExtentReport/ExtentReport.html";

            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);

            // Report configuration
            sparkReporter.config().setDocumentTitle("Acorn Automation Test Report");

            sparkReporter.config().setReportName("Acorn Automation Execution Report");

            sparkReporter.config().setTheme(Theme.STANDARD);

            extent = new ExtentReports();

            extent.attachReporter(sparkReporter);

            // System information
            extent.setSystemInfo("Operating System", System.getProperty("os.name"));

            extent.setSystemInfo("Java Version", System.getProperty("java.version"));

            extent.setSystemInfo("User", System.getProperty("user.name"));

            extent.setSystemInfo("Environment", "QA");
        }

        return extent;
    }

}
