package com.flamexander.automation.practice.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseUITest {
    @Test
    public void loginLogout() {
        driver.get("http://127.0.0.1:8181/");
        navigationBar.auth("admin", "123");
        Assert.assertTrue(navigationBar.hasLogout());

        navigationBar.logout();
        Assert.assertFalse(navigationBar.hasLogout());
    }
}
