package com.flamexander.automation.practice.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CartPage extends BaseActions {
    private static final By CART_TEXT = By.cssSelector("h1#cart");
    private static final By CART_LIST = By.cssSelector("td.table-cell");

    public CartPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public boolean isActive() {
        return isElementPresent(CART_TEXT);
    }

    public List<WebElement> getCart() {
        return driver.findElements(CART_LIST);
    }
}
