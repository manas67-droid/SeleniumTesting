package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.utils.DriverFactory;
import tests.utils.TestConfig;

import java.time.Duration;

/**
 * LoginValidTest - Verifies standard user can log in with valid credentials,
 * view the product catalog, and successfully log out.
 */
public class LoginValidTest {

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("Running: LoginValidTest (Valid Credentials Flow)");
        System.out.println("==================================================");

        ChromeDriver driver = DriverFactory.createDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TestConfig.EXPLICIT_WAIT_SECONDS));

        try {
            // 1. Navigate to Application Login Page
            System.out.println("[Step 1] Navigating to: " + TestConfig.BASE_URL);
            driver.get(TestConfig.BASE_URL);

            // 2. Locate form elements
            WebElement usernameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
            WebElement passwordInput = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.id("login-button"));

            // 3. Enter valid credentials and submit
            System.out.println("[Step 2] Entering valid credentials (User: " + TestConfig.VALID_USERNAME + ")");
            usernameInput.clear();
            usernameInput.sendKeys(TestConfig.VALID_USERNAME);
            passwordInput.clear();
            passwordInput.sendKeys(TestConfig.VALID_PASSWORD);

            System.out.println("[Step 3] Clicking login button");
            loginButton.click();

            // 4. Verify successful authentication and redirection
            System.out.println("[Step 4] Verifying redirection to Inventory dashboard...");
            wait.until(ExpectedConditions.urlContains("inventory.html"));
            String currentUrl = driver.getCurrentUrl();
            System.out.println("Current URL: " + currentUrl);
            if (!currentUrl.contains("inventory.html")) {
                throw new AssertionError("Verification Failed: Expected URL to contain 'inventory.html' but got: " + currentUrl);
            }

            WebElement pageTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("title")));
            String titleText = pageTitle.getText();
            System.out.println("Dashboard Title: " + titleText);
            if (!"Products".equalsIgnoreCase(titleText)) {
                throw new AssertionError("Verification Failed: Expected title 'Products' but got: " + titleText);
            }

            WebElement inventoryContainer = driver.findElement(By.className("inventory_list"));
            if (!inventoryContainer.isDisplayed()) {
                throw new AssertionError("Verification Failed: Inventory product list is not displayed.");
            }
            System.out.println("Product catalog verified successfully.");

            // 5. Test Logout Flow
            System.out.println("[Step 5] Initiating logout flow...");
            WebElement menuButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("react-burger-menu-btn")));
            menuButton.click();

            WebElement logoutLink = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("logout_sidebar_link")));
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", logoutLink);

            // 6. Verify return to login page
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-button")));
            System.out.println("Returned to login page successfully. URL: " + driver.getCurrentUrl());

            System.out.println("Result: PASSED - Valid login, dashboard validation, and logout completed successfully.");
        } catch (Exception e) {
            System.err.println("Result: FAILED - " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            driver.quit();
            System.out.println("Browser closed cleanly.\n");
        }
    }
}
