package com.example.driver;

import com.example.config.ConfigReader;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

/**
 * ThreadLocal-based WebDriver manager providing thread-safe WebDriver instances
 * for parallel or sequential TestNG test executions.
 */
public class DriverManager {

    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    private DriverManager() {
        // Prevent direct instantiation
    }

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    public static void setDriver(WebDriver driver) {
        driverThreadLocal.set(driver);
    }

    public static void initDriver() {
        if (driverThreadLocal.get() == null) {
            String browser = ConfigReader.getBrowser().toLowerCase();

            if ("chrome".equals(browser)) {
                ChromeOptions options = new ChromeOptions();

                options.setPageLoadStrategy(PageLoadStrategy.NONE);
                options.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");
                options.addArguments("--disable-gpu");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--remote-allow-origins=*");
                options.addArguments("--window-size=1920,1080");

                if (ConfigReader.isHeadless()) {
                    options.addArguments("--headless=new");
                }

                WebDriver driver = new ChromeDriver(options);
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigReader.getImplicitWait()));
                driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
                driver.manage().window().maximize();

                setDriver(driver);
            } else {
                throw new IllegalArgumentException("Unsupported browser: " + browser);
            }
        }
    }

    public static void quitDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                System.err.println("Error quitting driver: " + e.getMessage());
            } finally {
                driverThreadLocal.remove();
            }
        }
    }
}
