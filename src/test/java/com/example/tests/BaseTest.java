package com.example.tests;

import com.example.config.ConfigReader;
import com.example.driver.DriverManager;
import com.example.listeners.TestListener;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

/**
 * Base Test class managing driver lifecycle setup and teardown for each test method.
 */
@Listeners(TestListener.class)
public abstract class BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        DriverManager.initDriver();
        WebDriver driver = DriverManager.getDriver();
        driver.get(ConfigReader.getUrl());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverManager.quitDriver();
    }

    protected WebDriver getDriver() {
        return DriverManager.getDriver();
    }
}
