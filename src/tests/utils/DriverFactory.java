package tests.utils;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

/**
 * Reusable WebDriver factory for initializing ChromeDriver with standard configurations.
 */
public class DriverFactory {

    /**
     * Creates and initializes a ChromeDriver instance.
     * By default, runs in headless mode unless system property "headless" is set to "false".
     *
     * @return Configured ChromeDriver instance
     */
    public static ChromeDriver createDriver() {
        ChromeOptions options = new ChromeOptions();

        String headlessProp = System.getProperty("headless", "true");
        if (!"false".equalsIgnoreCase(headlessProp)) {
            options.addArguments("--headless=new");
        }

        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--remote-allow-origins=*");

        ChromeDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        return driver;
    }
}
