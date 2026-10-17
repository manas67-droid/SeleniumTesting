package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object for OrangeHRM Admin / User Management module.
 * Assigned to: Arpita Singh (Branch: admin-testing)
 */
public class AdminPage extends BasePage {

    private final By adminMenu = By.xpath("//span[text()='Admin']");
    private final By usernameSearchInput = By.xpath("//label[text()='Username']/parent::div/following-sibling::div//input");
    private final By searchButton = By.cssSelector("button[type='submit']");
    private final By tableCard = By.cssSelector(".oxd-table-card");
    private final By recordsFoundLabel = By.cssSelector(".orangehrm-horizontal-padding");

    public AdminPage(WebDriver driver) {
        super(driver);
    }

    public AdminPage navigateToAdmin() {
        click(adminMenu);
        return this;
    }

    public AdminPage searchUser(String username) {
        sendKeys(usernameSearchInput, username);
        click(searchButton);
        return this;
    }

    public boolean areRecordsDisplayed() {
        return isDisplayed(tableCard) || isDisplayed(recordsFoundLabel);
    }
}
