package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object for OrangeHRM PIM (Employee Management) module.
 * Assigned to: Aryan Chaudhary (Branch: pim-testing)
 */
public class PIMPage extends BasePage {

    private final By pimMenu = By.xpath("//span[text()='PIM']");
    private final By employeeNameInput = By.xpath("//label[text()='Employee Name']/parent::div/following-sibling::div//input");
    private final By searchButton = By.cssSelector("button[type='submit']");
    private final By tableCard = By.cssSelector(".oxd-table-card");

    public PIMPage(WebDriver driver) {
        super(driver);
    }

    public PIMPage navigateToPIM() {
        click(pimMenu);
        return this;
    }

    public PIMPage searchEmployee(String name) {
        sendKeys(employeeNameInput, name);
        click(searchButton);
        return this;
    }

    public boolean areEmployeeRecordsDisplayed() {
        return isDisplayed(tableCard);
    }
}
