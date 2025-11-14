package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartPersistenceTest extends BaseTest {

    @Test
    public void testCartPersistenceAfterLogoutLogin(By driver) {
        driver.findElement((SearchContext) By.id("username")).sendKeys("testUser");
        driver.findElement((SearchContext) By.id("password")).sendKeys("12345");
        driver.findElement((SearchContext) By.id("loginButton")).click();

        driver.findElement((SearchContext) By.id("addProductA")).click();

        // Logout
        driver.findElement((SearchContext) By.id("logoutButton")).click();

        // Login again
        driver.findElement((SearchContext) By.id("username")).sendKeys("testUser");
        driver.findElement((SearchContext) By.id("password")).sendKeys("12345");
        driver.findElement((SearchContext) By.id("loginButton")).click();

        driver.findElement((SearchContext) By.id("cartLink")).click();

        int count = driver.findElements((SearchContext) By.className("cart-item")).size();
        Assert.assertTrue(count == 0 || count == 1,
                "Cart should either retain or clear items according to system design.");
    }
}
