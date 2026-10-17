package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object representing OrangeHRM's Login Page.
 */
public class LoginPage extends BasePage {

    // Web Locators
    private final By usernameInput = By.name("username");
    private final By passwordInput = By.name("password");
    private final By loginButton = By.cssSelector("button[type='submit']");
    private final By errorAlertBanner = By.cssSelector(".oxd-alert-content-text");
    private final By fieldErrorMessage = By.cssSelector(".oxd-input-field-error-message");
    private final By forgotPasswordLink = By.cssSelector(".orangehrm-login-forgot-header");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoginPageLoaded() {
        try {
            waitForVisibility(usernameInput);
            waitForVisibility(loginButton);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public LoginPage enterUsername(String username) {
        sendKeys(usernameInput, username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        sendKeys(passwordInput, password);
        return this;
    }

    public void clickLogin() {
        click(loginButton);
    }

    public DashboardPage login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
        return new DashboardPage(driver);
    }

    public String getErrorMessage() {
        return getText(errorAlertBanner);
    }

    public boolean isErrorMessageDisplayed() {
        return isDisplayed(errorAlertBanner);
    }

    public String getFieldErrorMessage() {
        return getText(fieldErrorMessage);
    }

    public boolean isFieldErrorMessageDisplayed() {
        return isDisplayed(fieldErrorMessage);
    }

    public void clickForgotPassword() {
        click(forgotPasswordLink);
    }
}
