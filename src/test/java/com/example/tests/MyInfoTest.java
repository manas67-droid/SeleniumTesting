package com.example.tests;

import com.example.config.ConfigReader;
import com.example.pages.LoginPage;
import com.example.pages.MyInfoPage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * TestNG test cases for OrangeHRM My Info Module.
 * Assigned to: Jyoti Gupta (Branch: myinfo-testing)
 */
public class MyInfoTest extends BaseTest {

    @Test(priority = 1, description = "Verify personal details section in My Info module")
    public void testViewPersonalDetails() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(ConfigReader.getUsername(), ConfigReader.getPassword());

        MyInfoPage myInfoPage = new MyInfoPage(getDriver());
        myInfoPage.navigateToMyInfo();

        Assert.assertTrue(myInfoPage.isPersonalDetailsDisplayed(), "Personal Details section should be displayed");
    }
}
