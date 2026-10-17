# 🚀 OrangeHRM Selenium Test Automation Framework

An enterprise-grade, Page Object Model (POM) test automation framework built using **Java 21**, **Selenium WebDriver 4**, **TestNG**, and **Maven** for the [OrangeHRM Open Source Demo](https://opensource-demo.orangehrmlive.com/) web application.

---

## 📑 Architecture Overview

This project follows the official architecture specified for our team assignment:

```
selenium-automation-framework/
├── pom.xml                                      # Maven dependencies & build config
├── testng.xml                                   # TestNG suite runner configuration
├── README.md                                    # Setup & execution instructions
├── Test_Cases_Execution_Sheet.xlsx              # Formatted color-coded QA execution & defect matrix
├── Test_Cases_Execution_Sheet.csv               # Raw CSV version of test execution sheet
└── src/
    ├── main/
    │   ├── java/
    │   │   └── com/example/
    │   │       ├── config/
    │   │       │   └── ConfigReader.java        # Properties reader with system overrides
    │   │       ├── driver/
    │   │       │   └── DriverManager.java       # ThreadLocal WebDriver manager
    │   │       ├── pages/
    │   │       │   ├── BasePage.java            # Shared explicit waits & interactions
    │   │       │   ├── LoginPage.java           # Login page actions & locators
    │   │       │   ├── DashboardPage.java       # Dashboard page actions & locators
    │   │       │   ├── AdminPage.java           # Admin module page actions & locators
    │   │       │   ├── PIMPage.java             # PIM module page actions & locators
    │   │       │   └── MyInfoPage.java          # My Info module page actions & locators
    │   │       └── utils/
    │   │           └── ScreenshotUtils.java     # Test failure screenshot capture utility
    │   └── resources/
    │       └── config.properties                # Browser, URL & timeout configurations
    └── test/
        ├── java/
        │   └── com/example/
        │       ├── listeners/
        │       │   └── TestListener.java        # TestNG failure listener for screenshots
        │       └── tests/
        │           ├── BaseTest.java            # Setup/Teardown driver lifecycle
        │           ├── LoginTest.java           # Login module TestNG test cases
        │           ├── AdminTest.java           # Admin module TestNG test cases
        │           ├── PIMTest.java             # PIM module TestNG test cases
        │           └── MyInfoTest.java          # My Info module TestNG test cases
        └── resources/
            └── config.properties                # Test configuration properties
```

---

## 👥 Team Member Roles & Module Split

| Team Member | Module Assigned | Git Branch | Core Test Classes & Pages | Status |
|---|---|---|---|:---:|
| **Manas (Lead)** | **Login & Authentication** | `login-testing` | `LoginPage`, `DashboardPage`, `LoginTest` | **Completed & Verified ✅** |
| **Arpita Singh** | **Admin / User Management** | `admin-testing` | `AdminPage`, `AdminTest` | Skeleton Ready 🔄 |
| **Aryan Chaudhary** | **PIM / Employee Management** | `pim-testing` | `PIMPage`, `PIMTest` | Skeleton Ready 🔄 |
| **Jyoti Gupta** | **My Info / Personal Details** | `myinfo-testing` | `MyInfoPage`, `MyInfoTest` | Skeleton Ready 🔄 |

---

## 🌐 Application Under Test

* **Application**: [OrangeHRM Open Source Demo](https://opensource-demo.orangehrmlive.com/web/index.php/auth/login)
* **Default Credentials**:
  * **Username**: `Admin`
  * **Password**: `admin123`

---

## ⚙️ Configuration (`config.properties`)

```properties
url=https://opensource-demo.orangehrmlive.com/web/index.php/auth/login
username=Admin
password=admin123
browser=chrome
headless=true
timeout.explicit=15
timeout.implicit=10
```

---

## 🛠️ How to Run Tests

### 1. Running via Maven (Command Line)
To execute the automated test suite:
```bash
mvn test
```

To run a specific test class:
```bash
mvn test -Dtest=LoginTest
```

To watch the browser physically open on your screen (headed mode):
```bash
mvn test -Dheadless=false
```

### 2. Running in Eclipse IDE
1. Open Eclipse ➔ **File** ➔ **Import...** ➔ **Existing Maven Projects**.
2. Select the repository root folder containing `pom.xml`.
3. Eclipse m2e will automatically download all dependencies.
4. Right-click `testng.xml` or `LoginTest.java` ➔ **Run As** ➔ **TestNG Test**.

---

## 📊 Test Case Execution & Defect Matrix

The project includes an interactive spreadsheet (`Test_Cases_Execution_Sheet.xlsx`) documenting our testing cycles:
* **Cycle 1 (Initial Run)**: Contains genuine real-world bugs and synchronization challenges (`DEF-001` through `DEF-005`).
* **Cycle 2 (Retest)**: Retesting after implementing explicit wait strategies, achieving **100% PASS**.

---

## 🌿 Git Workflow

1. Clone repo: `git clone https://github.com/manas67-droid/SeleniumTesting.git`
2. Checkout assigned branch: `git checkout -b <module>-testing`
3. Commit with clear message: `git commit -m "Add Selenium tests for <module> module"`
4. Push and open Pull Request: `git push -u origin <module>-testing`
