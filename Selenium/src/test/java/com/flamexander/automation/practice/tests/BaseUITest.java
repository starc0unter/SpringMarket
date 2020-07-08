package com.flamexander.automation.practice.tests;

import com.flamexander.automation.practice.pom.CartPage;
import com.flamexander.automation.practice.pom.GoodsPage;
import com.flamexander.automation.practice.pom.NavigationBar;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.flamexander.automation.practice.pom.HomePage;

public class BaseUITest {
    WebDriver driver;
    WebDriverWait wait;
    HomePage homePage;
    NavigationBar navigationBar;
    GoodsPage goodsPage;
    CartPage cartPage;

    @BeforeMethod
    public void setUp() {
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        this.wait = new WebDriverWait(driver, 5);
        this.homePage = new HomePage(driver, wait);
        this.navigationBar = new NavigationBar(driver, wait);
        this.goodsPage = new GoodsPage(driver, wait);
        this.cartPage = new CartPage(driver, wait);
    }

    @AfterMethod
    public void shutdown() {
        driver.quit();
    }
}
