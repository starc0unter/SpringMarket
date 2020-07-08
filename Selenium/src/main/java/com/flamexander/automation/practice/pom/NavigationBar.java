package com.flamexander.automation.practice.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NavigationBar extends BaseActions {
    private static final By GOODS_LINK = By.cssSelector("a#goods");
    private static final By HOME_LINK = By.cssSelector("a#home");
    private static final By CART_LINK = By.cssSelector("a#cart");
    private static final By LOGIN_INPUT = By.cssSelector("input#login");
    private static final By PASSWORD_INPUT = By.cssSelector("input#password");
    private static final By AUTHORIZE_BUTTON = By.cssSelector("button#submit");
    private static final By LOGOUT_BUTTON = By.cssSelector("input#logout");

    public NavigationBar(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void auth(String login, String password) {
        typeInField(LOGIN_INPUT, login);
        typeInField(PASSWORD_INPUT, password);
        driver.findElement(AUTHORIZE_BUTTON).click();
    }

    public void logout() {
        driver.findElement(LOGOUT_BUTTON).click();
    }

    public void openGoodsLink() {
        driver.findElement(GOODS_LINK).click();
    }

    public void openCartLink() {
        driver.findElement(CART_LINK).click();
    }

    public void openHomeLink() {
        driver.findElement(HOME_LINK).click();
    }

    public boolean hasLogout() {
        return isElementPresent(LOGOUT_BUTTON);
    }
}
