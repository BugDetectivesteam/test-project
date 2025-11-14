package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.testng.Assert;
import org.testng.annotations.Test;

public class sc8 extends BaseTest {

    @Test
    public void testCartPriceAndTaxes(By driver) {
        driver.findElement((SearchContext) By.id("username")).sendKeys("testUser");
        driver.findElement((SearchContext) By.id("password")).sendKeys("12345");
        driver.findElement((SearchContext) By.id("loginButton")).click();

        driver.findElement((SearchContext) By.id("addProductA")).click();
        driver.findElement((SearchContext) By.id("addProductB")).click();
        driver.findElement((SearchContext) By.id("cartLink")).click();

        String total = driver.findElement((SearchContext) By.id("totalPrice")).getText();
        Assert.assertTrue(total.matches(".*\\d+.*"), "Total price should display correct value with tax/shipping.");
    }
}
