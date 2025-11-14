package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.testng.Assert;
import org.testng.annotations.Test;

class ChangeQuantityTest extends BaseTest {

    @Test
    public void testChangeQuantity(By driver) {
        driver.findElement((SearchContext) By.id("username")).sendKeys("testUser");
        driver.findElement((SearchContext) By.id("password")).sendKeys("12345");
        driver.findElement((SearchContext) By.id("loginButton")).click();

        driver.findElement((SearchContext) By.id("addProductA")).click();
        driver.findElement((SearchContext) By.id("cartLink")).click();

        driver.findElement((SearchContext) By.name("quantity")).clear();
        driver.findElement((SearchContext) By.name("quantity")).sendKeys("3");
        driver.findElement((SearchContext) By.id("updateCart")).click();

        String totalPrice = driver.findElement((SearchContext) By.id("totalPrice")).getText();
        Assert.assertTrue(totalPrice.contains("3"), "Total price should update according to quantity.");
    }
}
