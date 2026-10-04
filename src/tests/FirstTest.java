package tests;

import org.openqa.selenium.chrome.ChromeDriver;
import tests.utils.DriverFactory;

/**
 * FirstTest - Smoke verification test based on Section 6 of the setup guide.
 * Verifies that the ChromeDriver binary and browser communication work as expected.
 */
public class FirstTest {

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("Running: FirstTest (Browser Smoke Test)");
        System.out.println("==================================================");

        ChromeDriver driver = DriverFactory.createDriver();
        try {
            driver.get("https://example.com");
            String title = driver.getTitle();
            System.out.println("Page Title: " + title);

            if (title == null || !title.toLowerCase().contains("example")) {
                throw new AssertionError("Verification Failed: Unexpected page title -> " + title);
            }

            System.out.println("Result: PASSED - Browser launched and successfully navigated to target URL.");
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
