package com.flamexander.automation.practice.pom;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;


public class GoodsPage extends BaseActions{
    private static final By GOODS_LIST_TEXT = By.cssSelector("h1#productsList");
    private static final By GOODS_LIST = By.cssSelector("a.btn-primary");

    public GoodsPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public boolean isActive() {
        return isElementPresent(GOODS_LIST_TEXT);
    }

    public List<WebElement> getGoods() {
        return driver.findElements(GOODS_LIST);
    }
}
