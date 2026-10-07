# 🚀 OrangeHRM

An enterprise-grade, maintainable Selenium WebDriver test automation suite built in Java for the **SauceDemo (Swag Labs)** e-commerce web application. Developed as a collaborative team project adhering to industry best practices, Git feature branching, and strict QA test tracking.

---

## 📑 Table of Contents
1. [Project Overview](#-project-overview)
2. [Target Application](#-target-application)
3. [Team Members & Module Division](#-team-members--module-division)
4. [Project Structure](#-project-structure)
5. [Prerequisites & Environment Setup](#-prerequisites--environment-setup)
6. [Importing & Running in Eclipse IDE](#-importing--running-in-eclipse-ide)
7. [Running from Command Line](#-running-from-command-line)
8. [Git & GitHub Collaboration Workflow](#-git--github-collaboration-workflow)
9. [Test Execution & Defect Tracking](#-test-execution--defect-tracking)
10. [Troubleshooting Guide](#-troubleshooting-guide)

---

## 📋 Project Overview

This repository demonstrates an end-to-end automated UI testing framework using **Selenium WebDriver (Java)** without relying on heavy build abstractions. It showcases:
* Pure Java test automation with explicit synchronization (`WebDriverWait`).
* Handling dynamic JavaScript / React single-page application behavior (`JavascriptExecutor`).
* Reusable driver architecture via `DriverFactory` supporting headless and headed execution.
* Multi-branch Git collaboration with Pull Request reviews.
* Formal QA Test Case Execution and Defect Tracking documented in an Excel matrix.

---

## 🌐 Target Application

* **Application**: [SauceDemo (Swag Labs)](https://www.saucedemo.com/)
* **Category**: E-Commerce Web Application (Products, Cart, Checkout, Authentication)
* **Target Users**:
  * `standard_user`: Valid active user.
  * `locked_out_user`: Account status validation.
  * `invalid_user`: Unregistered / bad credentials validation.

---

## 👥 Team Members & Module Division

| Member | Assigned Module | Git Branch | Core Test Classes | Status |
|---|---|---|---|:---:|
| **Manas (Lead)** | **Login & Authentication** | `login-testing` | `LoginValidTest`, `LoginInvalidTest`, `LoginEmptyCredentialsTest`, `LoginLockedOutTest` | **Completed ✅** |
| **Arpita Singh** | **Product Catalog & Search/Sort** | `search-testing` | `ProductListTest`, `SearchSortTest` | In Progress 🔄 |
| **Aryan Chaudhary** | **Shopping Cart** | `cart-testing` | `CartAddRemoveTest`, `CartBadgeCounterTest` | In Progress 🔄 |
| **Jyoti Gupta** | **Checkout & Payment** | `checkout-testing` | `CheckoutValidationTest`, `OrderPlacementTest` | In Progress 🔄 |

---

## 📁 Project Structure

```
SeleniumTesting/
├── .gitignore                          # Excludes binaries, class files, and temporary caches
├── README.md                           # Master project documentation
├── Test_Cases_Execution_Sheet.xlsx     # Formatted Excel sheet with color-coded test statuses
├── Test_Cases_Execution_Sheet.csv      # Raw CSV version for universal access
├── .project                            # Eclipse project definition
├── .classpath                          # Eclipse build path referencing external Selenium JARs
└── src/
    └── tests/
        ├── FirstTest.java                  # Baseline browser smoke test
        ├── LoginValidTest.java             # Positive login & session logout test
        ├── LoginInvalidTest.java           # Negative login invalid credentials test
        ├── LoginEmptyCredentialsTest.java  # Input validation for required fields
        ├── LoginLockedOutTest.java         # Account lockout error handling test
        ├── AllLoginTestsSuite.java         # Master test runner with execution summary
        └── utils/
            ├── DriverFactory.java          # Configures ChromeDriver (options, headless toggle, timeouts)
            └── TestConfig.java             # Centralized application URLs, credentials, and settings
```

---

## 🛠️ Prerequisites & Environment Setup

### 1. Java Development Kit (JDK 21 or higher)
Ensure Java and `javac` are available in your system path:
```bash
java -version
javac -version
```

### 2. Google Chrome Browser
Google Chrome must be installed. Selenium 4 uses **Selenium Manager** to automatically match and supply the required ChromeDriver binary for your browser version.

### 3. Selenium Java Dependencies (Manual Setup)
Download and extract `selenium-java-4.x.zip` from [Selenium Official Downloads](https://www.selenium.dev/downloads/):
* Include all non-source `.jar` files in your classpath:
  * `selenium-api-4.x.jar`
  * `selenium-chrome-driver-4.x.jar`
  * `selenium-remote-driver-4.x.jar`
  * All runtime library JARs (`guava`, `byte-buddy`, `opentelemetry`).
* *Do not add `*-sources.jar` or documentation files.*

---

## 💻 Importing & Running in Eclipse IDE

### 1. Import the Project into Eclipse
1. Launch Eclipse IDE.
2. Select **File** ➔ **Open Projects from File System...**
3. Click **Directory...** and select the cloned repository folder (`SeleniumTesting`).
4. Click **Finish**.

### 2. Configure Selenium Build Path (if needed)
1. Right-click the project ➔ **Build Path** ➔ **Configure Build Path...**
2. Go to the **Libraries** tab ➔ select **Classpath** ➔ click **Add External JARs...**
3. Select all regular `.jar` files from your extracted Selenium Java folder.
4. Click **Apply and Close**. All red compilation icons will disappear.

### 3. Run Any Test in Eclipse
* Right-click any test file (e.g., `LoginValidTest.java`) ➔ **Run As** ➔ **Java Application** (Shortcut: `Ctrl + F11`).
* To run the entire suite, right-click `AllLoginTestsSuite.java` ➔ **Run As** ➔ **Java Application**.

---

## ⚡ Running from Command Line

You can compile and run all tests directly from Windows PowerShell:

```powershell
# 1. Point to your extracted Selenium JARs directory
$jars = (Get-ChildItem "C:\path\to\selenium-java-4.x\*.jar" | Where-Object { $_.Name -notlike "*-sources.jar" } | ForEach-Object { $_.FullName }) -join ";"

# 2. Compile all source files into bin/
mkdir bin -Force
javac -cp $jars -d bin (Get-ChildItem -Path "src" -Filter "*.java" -Recurse | ForEach-Object { $_.FullName })

# 3. Run the automated test suite
java -cp "bin;$jars" tests.AllLoginTestsSuite
```

### 👁️ Headed vs Headless Mode:
Tests run in **headless mode** by default for maximum speed and background stability. To watch Chrome physically open and interact on your screen, add `-Dheadless=false`:
```powershell
java -Dheadless=false -cp "bin;$jars" tests.LoginValidTest
```

---

## 🌿 Git & GitHub Collaboration Workflow

Our team strictly adheres to feature-branch development:

```
master (protected base)
  │
  ├─── login-testing (PR #1) ────── Merged ✅
  ├─── search-testing (PR #2) ───── Under Review 🔄
  ├─── cart-testing (PR #3) ─────── In Development 🔄
  └─── checkout-testing (PR #4) ─── In Development 🔄
```

### Standard Teammate Workflow:

1. **Clone the Central Repository**:
   ```bash
   git clone https://github.com/manas67-droid/SeleniumTesting.git
   cd SeleniumTesting
   ```

2. **Create Your Feature Branch**:
   ```bash
   git checkout -b <your-module>-testing
   ```

3. **Stage Only Assignment Files** (Never commit `.class` files, IDE metadata, or JARs):
   ```bash
   git status
   git add src/tests/YourTest.java
   ```

4. **Commit with Meaningful Conventional Messages**:
   ```bash
   git commit -m "Add Selenium tests for <module> module"
   ```

5. **Push and Open a Pull Request**:
   ```bash
   git push -u origin <your-module>-testing
   ```
   * Navigate to GitHub and click **Compare & pull request** against `master`.
   * A teammate must review the code before merging.

6. **Sync Local Main After Merges**:
   ```bash
   git checkout master
   git pull origin master
   ```

---

## 📊 Test Execution & Defect Tracking

We maintain both an interactive Excel sheet (`Test_Cases_Execution_Sheet.xlsx`) and CSV version (`Test_Cases_Execution_Sheet.csv`) documenting our two-cycle test runs:

* **Cycle 1 (Initial Execution)**: Tests run against initial code, recording real-world bugs and synchronization issues.
* **Defect Tracking**: Logging issues with unique Defect IDs (`DEF-001` through `DEF-005`), detailing Root Causes and Resolutions.
* **Cycle 2 (Retest)**: Retesting after implementing script and locator fixes, confirming all scenarios achieve **PASS**.

### Sample Defect Log:
* **DEF-001 (Logout Timing)**: React sidebar animation delayed DOM transition, causing `TimeoutException`. Resolved using `JavascriptExecutor` click and explicit `WebDriverWait`.
* **DEF-002 (Locator Text Mismatch)**: Exact error string matching broke due to dynamic prefixing. Resolved using CSS locator `[data-test='error']` and `.contains()` substring assertions.
* **DEF-003 (Numeric vs String Sorting)**: Product low-to-high price sorting failed because string comparison placed `$7.99` after `$29.99`. Resolved by parsing values to `Double`.

---

## ❓ Troubleshooting Guide

| Issue | Root Cause | Solution |
|---|---|---|
| Red error squiggles under `import org.openqa.selenium...` | Eclipse Build Path is missing external Selenium JARs | Right-click project ➔ **Build Path** ➔ **Add External JARs...** ➔ Select extracted Selenium `.jar` files. |
| `ElementClickInterceptedException` | Element is covered by an overlay or animation | Use `((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);` or wait for `elementToBeClickable()`. |
| `NoSuchElementException` | Element not yet rendered in the DOM | Replace hardcoded delays with explicit wait: `wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(...)))`. |
| Eclipse: *"Failed to find a Main Class in ...launcher.jar"* | Corrupted path resolution or missing JVM in `eclipse.ini` | Specify absolute path in `-startup` and add explicit `-vm` parameter pointing to `javaw.exe`. |

---

## 📄 License
This project is for educational and academic assessment purposes. Target application courtesy of [Sauce Labs](https://saucelabs.com/).
