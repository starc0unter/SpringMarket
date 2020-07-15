package com.flamexander.automation.practice.tests;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MarketTest extends BaseUITest{
    @Test
    public void purchaseGoods() {
        driver.get("http://127.0.0.1:8181/");
        //авторизуемся
        navigationBar.auth("admin", "123");
        //покупаем все товары на 1 странице
        navigationBar.openGoodsLink();
        Assert.assertTrue(goodsPage.isActive());
        final int expectedCartSize = addGoodsToCart();
        //проверяем, что все они попали в корзину
        navigationBar.openCartLink();
        Assert.assertTrue(cartPage.isActive());
        final List<WebElement> cart = cartPage.getCart();
        Assert.assertEquals(expectedCartSize, cart.size());
        //возвращаемся на главную
        navigationBar.openHomeLink();
        Assert.assertTrue(homePage.isActive());
    }

    private int addGoodsToCart() {
        int currentProduct = 0;
        final List<WebElement> goods = goodsPage.getGoods();
        do {
            final List<WebElement> temp = goodsPage.getGoods();
            temp.get(currentProduct++).click();
        } while (currentProduct < goods.size());
        return goods.size();
    }
}
