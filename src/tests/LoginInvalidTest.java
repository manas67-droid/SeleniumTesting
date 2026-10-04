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
 * LoginInvalidTest - Verifies that authentication fails gracefully with invalid credentials,
 * the appropriate error notification is displayed, and access is denied.
 */
public class LoginInvalidTest {

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("Running: LoginInvalidTest (Invalid Credentials Flow)");
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

            // 3. Enter invalid credentials and submit
            System.out.println("[Step 2] Entering invalid credentials (User: " + TestConfig.INVALID_USERNAME + ")");
            usernameInput.clear();
            usernameInput.sendKeys(TestConfig.INVALID_USERNAME);
            passwordInput.clear();
            passwordInput.sendKeys(TestConfig.INVALID_PASSWORD);

            System.out.println("[Step 3] Clicking login button");
            loginButton.click();

            // 4. Verify error notification display
            System.out.println("[Step 4] Checking error message presentation...");
            WebElement errorContainer = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-test='error']"))
            );

            String errorMessage = errorContainer.getText();
            System.out.println("Captured Error Message: " + errorMessage);

            String expectedSnippet = "Username and password do not match any user in this service";
            if (!errorMessage.contains(expectedSnippet)) {
                throw new AssertionError("Verification Failed: Expected error containing '" +
                        expectedSnippet + "' but received: '" + errorMessage + "'");
            }

            // 5. Verify user remains on login page
            String currentUrl = driver.getCurrentUrl();
            if (currentUrl.contains("inventory.html")) {
                throw new AssertionError("Security Violation: User was navigated to inventory with invalid credentials!");
            }

            System.out.println("Result: PASSED - Invalid credentials rejected with appropriate error notice.");
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
