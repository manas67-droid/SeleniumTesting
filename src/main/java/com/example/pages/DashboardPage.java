package com.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Page Object representing OrangeHRM's post-authentication Dashboard.
 */
public class DashboardPage extends BasePage {

    // Web Locators
    private final By headerTitle = By.xpath("//h6[contains(@class,'oxd-topbar-header-breadcrumb') or contains(.,'Dashboard')]");
    private final By userDropdown = By.cssSelector(".oxd-userdropdown-tab");
    private final By logoutMenuItem = By.xpath("//a[contains(@href,'logout') or text()='Logout']");

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public boolean isDashboardDisplayed() {
        try {
            wait.until(ExpectedConditions.urlContains("/dashboard"));
            return waitForVisibility(headerTitle).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getHeaderText() {
        return getText(headerTitle);
    }

    public void clickUserDropdown() {
        click(userDropdown);
    }

    public LoginPage logout() {
        clickUserDropdown();
        try {
            click(logoutMenuItem);
        } catch (Exception e) {
            jsClick(logoutMenuItem);
        }
        return new LoginPage(driver);
    }
}
