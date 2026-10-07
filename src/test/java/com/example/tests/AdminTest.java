package com.example.tests;

import com.example.config.ConfigReader;
import com.example.pages.AdminPage;
import com.example.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TestNG test cases for OrangeHRM Admin Module.
 * Assigned to: Arpita Singh (Branch: admin-testing)
 */
public class AdminTest extends BaseTest {

    @Test(priority = 1, description = "Verify searching system users in Admin module")
    public void testSearchSystemUser() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(ConfigReader.getUsername(), ConfigReader.getPassword());

        AdminPage adminPage = new AdminPage(getDriver());
        adminPage.navigateToAdmin();
        adminPage.searchUser("Admin");

        Assert.assertTrue(adminPage.areRecordsDisplayed(), "Admin user record should be found and displayed");
    }
}
