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
 * LoginEmptyCredentialsTest - Verifies validation errors when submitting blank or partial credentials.
 */
public class LoginEmptyCredentialsTest {

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("Running: LoginEmptyCredentialsTest (Input Validation Flow)");
        System.out.println("==================================================");

        ChromeDriver driver = DriverFactory.createDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TestConfig.EXPLICIT_WAIT_SECONDS));

        try {
            driver.get(TestConfig.BASE_URL);

            WebElement usernameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
            WebElement passwordInput = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.id("login-button"));

            // Scenario A: Both fields empty
            System.out.println("[Scenario A] Submitting completely empty login form...");
            loginButton.click();

            WebElement errorContainer = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-test='error']"))
            );
            String errorText = errorContainer.getText();
            System.out.println("Error Captured: " + errorText);
            if (!errorText.contains("Username is required")) {
                throw new AssertionError("Expected 'Username is required' but got: " + errorText);
            }

            // Scenario B: Username entered, Password omitted
            System.out.println("[Scenario B] Submitting with username provided but empty password...");
            driver.get(TestConfig.BASE_URL);
            usernameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
            passwordInput = driver.findElement(By.id("password"));
            loginButton = driver.findElement(By.id("login-button"));

            usernameInput.sendKeys(TestConfig.VALID_USERNAME);
            passwordInput.clear();
            loginButton.click();

            errorContainer = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-test='error']"))
            );
            errorText = errorContainer.getText();
            System.out.println("Error Captured: " + errorText);
            if (!errorText.contains("Password is required")) {
                throw new AssertionError("Expected 'Password is required' but got: " + errorText);
            }

            System.out.println("Result: PASSED - Empty credentials handled properly with required field validations.");
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
