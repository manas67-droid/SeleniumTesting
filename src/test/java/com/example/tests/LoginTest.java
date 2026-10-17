package com.example.tests;

import com.example.config.ConfigReader;
import com.example.pages.DashboardPage;
import com.example.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TestNG test suite validating OrangeHRM Login and Authentication workflows.
 */
public class LoginTest extends BaseTest {

    @Test(priority = 1, description = "Verify successful login with valid credentials and session logout")
    public void testValidLogin() {
        LoginPage loginPage = new LoginPage(getDriver());
        Assert.assertTrue(loginPage.isLoginPageLoaded(), "Login page should be loaded");

        DashboardPage dashboardPage = loginPage.login(ConfigReader.getUsername(), ConfigReader.getPassword());

        Assert.assertTrue(dashboardPage.isDashboardDisplayed(), "Dashboard should be displayed after successful login");
        Assert.assertTrue(dashboardPage.getHeaderText().toLowerCase().contains("dashboard"),
                "Dashboard header should indicate Dashboard module");

        LoginPage postLogoutPage = dashboardPage.logout();
        Assert.assertTrue(postLogoutPage.isLoginPageLoaded(), "Should return to login page after logout");
    }

    @Test(priority = 2, description = "Verify login rejection with invalid credentials")
    public void testInvalidLogin() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login("InvalidUser", "WrongPassword123");

        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error alert banner should be displayed");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Invalid credentials"),
                "Error message should read 'Invalid credentials'");
    }

    @Test(priority = 3, description = "Verify field validation when username is omitted")
    public void testEmptyUsername() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.enterPassword("admin123");
        loginPage.clickLogin();

        Assert.assertTrue(loginPage.isFieldErrorMessageDisplayed(), "Required validation message should appear");
        Assert.assertTrue(loginPage.getFieldErrorMessage().contains("Required"),
                "Field error should display 'Required'");
    }

    @Test(priority = 4, description = "Verify field validation when password is omitted")
    public void testEmptyPassword() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.enterUsername("Admin");
        loginPage.clickLogin();

        Assert.assertTrue(loginPage.isFieldErrorMessageDisplayed(), "Required validation message should appear");
        Assert.assertTrue(loginPage.getFieldErrorMessage().contains("Required"),
                "Field error should display 'Required'");
    }

    @Test(priority = 5, description = "Verify navigation to Forgot Password recovery page")
    public void testForgotPasswordNavigation() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.clickForgotPassword();

        Assert.assertTrue(getDriver().getCurrentUrl().contains("requestPasswordResetCode"),
                "Should navigate to password reset request page");
    }
}
