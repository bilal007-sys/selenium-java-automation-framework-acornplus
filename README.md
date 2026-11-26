# Acorn CRM – Selenium Java Automation Framework

## Overview

A UI test automation framework developed using **Java, Selenium WebDriver, Maven, and TestNG** to automate functional testing of the Acorn CRM web application.

The framework follows the **Page Object Model (POM)** design pattern to maintain reusable, organized, and maintainable automation code. It includes automated test execution, centralized test data management, and Extent Reports integration for test execution reporting.

This is an actively evolving project, with continuous efforts to expand test coverage across application modules, scenarios, and edge cases, with the goal of reducing manual testing efforts by **60–70%**.

## Application Under Test

**Application:** [Acorn CRM](https://acorn.vet/)

This framework is developed to automate functional and UI testing of the Acorn CRM web application.

## Tech Stack

- **Programming Language:** Java
- **Automation Tool:** Selenium WebDriver
- **Test Framework:** TestNG
- **Build Management:** Maven
- **Design Pattern:** Page Object Model (POM)
- **Reporting:** Extent Reports
- **IDE:** Eclipse
- **Version Control:** Git & GitHub

## Project Structure

```text
AcornCRMAutomation/
│
├── src/test/java/com/acornautomation/
│   │
│   ├── Pages/
│   │   ├── LoginPage.java
│   │   ├── AppointmentsPage.java
│   │   ├── ClientsPage.java
│   │   ├── HeaderPage.java
│   │   └── SidebarPage.java
│   │
│   ├── Test/
│   │   ├── BaseClass.java
│   │   ├── LoginTest.java
│   │   ├── AppointmentsTest.java
│   │   └── ClientsTest.java
│   │
│   ├── Utilities/
│   │   └── TestData.java
│   │
│   └── Reporting/
│       ├── ExtentManager.java
│       ├── ExtentReportListener.java
│       └── ExtentTestManager.java
│
├── pom.xml
├── testng.xml
├── .gitignore
└── README.md
```

## Framework Features

- Page Object Model (POM) implementation for better code organization and reusability.
- Reusable page classes and common test setup through a base test class.
- TestNG-based test execution and test suite management.
- Support for parallel execution of multiple test classes through TestNG configuration.
- Maven-based dependency and build management.
- Centralized test data management.
- Extent Reports integration with listeners for detailed execution reporting.
- Git-based version control and GitHub project management.
- Continuously expanding test coverage across application modules and functional scenarios.

## Configuration & Execution

**Prerequisites:**
- Java JDK
- Eclipse IDE or another Java IDE
- Maven
- Google Chrome browser

**Run the automation suite:**

1. Clone the repository:
   ```bash
   git clone https://github.com/bilal007-sys/selenium-java-automation-framework-acornplus.git
   ```

2. Import the project into Eclipse as an existing Maven project.

3. Allow Maven to download and resolve the required dependencies defined in `pom.xml`.

4. Configure the required application URL and test credentials in the project, if applicable.

5. Run the `testng.xml` file as a TestNG Suite to execute the configured test classes.

## Project Goals & Roadmap

This framework is actively under development. Planned improvements include:

- Expanding automation coverage across additional CRM modules.
- Adding more positive, negative, boundary, and end-to-end test scenarios.
- Improving reusable utilities and framework maintainability.
- Enhancing test execution, reporting, and parallel execution capabilities.
- Increasing automated regression coverage to reduce manual testing efforts by **60–70%**.

## Author

**Bilal Shahzad**

SQA Engineer | Selenium Java Automation

[GitHub Profile](https://github.com/bilal007-sys)
