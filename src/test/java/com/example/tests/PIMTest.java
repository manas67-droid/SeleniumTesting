package com.example.tests;

import com.example.config.ConfigReader;
import com.example.pages.LoginPage;
import com.example.pages.PIMPage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TestNG test cases for OrangeHRM PIM Module.
 * Assigned to: Aryan Chaudhary (Branch: pim-testing)
 */
public class PIMTest extends BaseTest {

    @Test(priority = 1, description = "Verify employee search in PIM module")
    public void testSearchEmployee() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(ConfigReader.getUsername(), ConfigReader.getPassword());

        PIMPage pimPage = new PIMPage(getDriver());
        pimPage.navigateToPIM();
        pimPage.searchEmployee("John");

        Assert.assertTrue(pimPage.areEmployeeRecordsDisplayed(), "Employee search results table should be rendered");
    }
}
