package com.flamexander.automation.practice.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BaseActions {
    private static final By HOME_PAGE = By.cssSelector("h1#homePage");

    public HomePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public boolean isActive() {
        return isElementPresent(HOME_PAGE);
    }
}
