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
 * LoginLockedOutTest - Verifies that attempting to log in as a locked-out user
 * results in a distinct lock-out error message and denies entry.
 */
public class LoginLockedOutTest {

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("Running: LoginLockedOutTest (Locked-Out Account Validation)");
        System.out.println("==================================================");

        ChromeDriver driver = DriverFactory.createDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TestConfig.EXPLICIT_WAIT_SECONDS));

        try {
            System.out.println("[Step 1] Navigating to: " + TestConfig.BASE_URL);
            driver.get(TestConfig.BASE_URL);

            WebElement usernameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
            WebElement passwordInput = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.id("login-button"));

            System.out.println("[Step 2] Entering locked out user credentials (User: " + TestConfig.LOCKED_OUT_USERNAME + ")");
            usernameInput.clear();
            usernameInput.sendKeys(TestConfig.LOCKED_OUT_USERNAME);
            passwordInput.clear();
            passwordInput.sendKeys(TestConfig.VALID_PASSWORD);

            System.out.println("[Step 3] Submitting login request");
            loginButton.click();

            System.out.println("[Step 4] Validating lockout notice...");
            WebElement errorContainer = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-test='error']"))
            );

            String errorMessage = errorContainer.getText();
            System.out.println("Captured Error Message: " + errorMessage);

            String expectedSnippet = "Sorry, this user has been locked out.";
            if (!errorMessage.contains(expectedSnippet)) {
                throw new AssertionError("Verification Failed: Expected lock-out error containing '" +
                        expectedSnippet + "' but received: '" + errorMessage + "'");
            }

            System.out.println("Result: PASSED - Account lockout enforced correctly.");
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
