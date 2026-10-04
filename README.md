# Selenium Java Automation Testing - Login Module

A clean, maintainable Selenium WebDriver test automation suite built in Java, adhering to the standard manual setup and team workflow guide.

---

## 📋 Overview

This repository contains automated browser test cases for the **Login Module** targeting the [SauceDemo](https://www.saucedemo.com/) application, along with foundational sanity/smoke tests matching the **Java + Eclipse + Selenium + GitHub Setup Guide**.

### Test Suite Structure

```
ProjectName
├── .gitignore
├── README.md
└── src/
    └── tests/
        ├── FirstTest.java                  # Smoke test verifying WebDriver and browser communication
        ├── LoginValidTest.java             # Positive login scenario, inventory verification, and logout
        ├── LoginInvalidTest.java           # Negative login scenario verifying error alert and access denial
        ├── LoginEmptyCredentialsTest.java  # Input validation for missing username/password
        ├── LoginLockedOutTest.java         # Account status verification for locked-out user
        ├── AllLoginTestsSuite.java         # Test suite runner executing all tests sequentially with report
        └── utils/
            ├── DriverFactory.java          # Reusable ChromeDriver setup, options, and timeouts
            └── TestConfig.java             # Centralized application URLs, credentials, and settings
```

---

## 🛠️ Prerequisites & Environment Setup

### 1. Java JDK (JDK 21+)
Verify that Java and the Java compiler are available on your system:
```bash
java -version
javac -version
```

### 2. Google Chrome Browser
Make sure Google Chrome is installed. Selenium 4+ includes `selenium-manager` which automatically resolves and configures compatible ChromeDriver binaries.

### 3. Selenium Java Dependencies (Manual Setup)
1. Download `selenium-java-4.x.zip` from [Selenium Downloads](https://www.selenium.dev/downloads/).
2. Extract the archive into a directory on your system.
3. Include all core `.jar` files and library dependencies (skipping `*-sources.jar`, `LICENSE`, `NOTICE`, and `CHANGELOG`).
4. Ensure `selenium-remote-driver-4.x.jar` is included in the classpath.

---

## 💻 Eclipse Project Configuration

1. **Open Eclipse IDE**:
   - `File` -> `New` -> `Java Project`.
   - Name the project `SeleniumTesting` (or your repository project name).
   - Ensure the execution environment is set to Java 21+.
2. **Configure Build Path**:
   - Right-click project -> `Build Path` -> `Configure Build Path...`.
   - Select `Libraries` tab -> `Classpath` -> `Add External JARs...`.
   - Select all regular `.jar` files from the extracted Selenium folder.
   - Click `Apply and Close`.
3. **Execute in Eclipse**:
   - Right-click any test class (e.g., `LoginValidTest.java`) -> `Run As` -> `Java Application` (Shortcut: `Ctrl + F11`).
   - Or run `AllLoginTestsSuite.java` to run all test cases together.

---

## 🚀 Running Tests via Command Line

Set your Selenium JAR classpath and compile/run the test suite:

### Windows PowerShell:
```powershell
# 1. Build classpath from the extracted Selenium JARs directory
$jars = (Get-ChildItem "C:\path\to\selenium-java-4.x\*.jar" | Where-Object { $_.Name -notlike "*-sources.jar" } | ForEach-Object { $_.FullName }) -join ";"

# 2. Compile test classes into bin/
mkdir bin -Force
javac -cp $jars -d bin (Get-ChildItem "src\*.java" -Recurse | ForEach-Object { $_.FullName })

# 3. Execute the full test suite
java -cp "bin;$jars" tests.AllLoginTestsSuite
```

To run individual tests directly:
```powershell
java -cp "bin;$jars" tests.LoginValidTest
java -cp "bin;$jars" tests.LoginInvalidTest
```

*Note: Tests run headless by default for optimal performance. To watch the browser execute on your screen, add `-Dheadless=false`:*
```powershell
java -Dheadless=false -cp "bin;$jars" tests.LoginValidTest
```

---

## 🧪 Test Scenarios Covered

| Test Class | Objective | Expected Outcome |
|---|---|---|
| `FirstTest` | Baseline WebDriver & browser connectivity check | Navigates to target, retrieves title, closes browser cleanly |
| `LoginValidTest` | Valid credentials authentication flow (`standard_user`) | Navigates to `inventory.html`, verifies Products title, logs out |
| `LoginInvalidTest` | Invalid credentials negative flow (`invalid_user`) | Validates error message: *"Username and password do not match any user in this service"* |
| `LoginEmptyCredentialsTest` | Missing field input validation | Validates *"Username is required"* and *"Password is required"* |
| `LoginLockedOutTest` | Locked-out account access denial | Validates error message: *"Sorry, this user has been locked out."* |

---

## 🌿 Git & GitHub Team Workflow

1. **Create Feature Branch**:
   ```bash
   git checkout -b login-testing
   ```
2. **Review & Stage Changes** (Only commit test code, test data, and documentation — never binaries or `.class` files):
   ```bash
   git status
   git add src/ .gitignore README.md
   ```
3. **Commit with Descriptive Message**:
   ```bash
   git commit -m "Add Selenium tests for login module"
   ```
4. **Push Branch & Open Pull Request**:
   ```bash
   git push -u origin login-testing
   ```
