package tests.utils;

/**
 * Centralized test configuration and test data.
 */
public class TestConfig {
    // Target Application URL (SauceDemo - standard Selenium automation demo application)
    public static final String BASE_URL = "https://www.saucedemo.com/";
    public static final String INVENTORY_URL = "https://www.saucedemo.com/inventory.html";

    // Test Credentials
    public static final String VALID_USERNAME = "standard_user";
    public static final String VALID_PASSWORD = "secret_sauce";

    public static final String INVALID_USERNAME = "invalid_user";
    public static final String INVALID_PASSWORD = "wrong_password";

    public static final String LOCKED_OUT_USERNAME = "locked_out_user";

    // Timeouts
    public static final int EXPLICIT_WAIT_SECONDS = 10;
}
