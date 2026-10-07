package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object for OrangeHRM My Info module.
 * Assigned to: Jyoti Gupta (Branch: myinfo-testing)
 */
public class MyInfoPage extends BasePage {

    private final By myInfoMenu = By.xpath("//span[text()='My Info']");
    private final By personalDetailsHeader = By.xpath("//h6[text()='Personal Details']");

    public MyInfoPage(WebDriver driver) {
        super(driver);
    }

    public MyInfoPage navigateToMyInfo() {
        click(myInfoMenu);
        return this;
    }

    public boolean isPersonalDetailsDisplayed() {
        return isDisplayed(personalDetailsHeader);
    }
}
