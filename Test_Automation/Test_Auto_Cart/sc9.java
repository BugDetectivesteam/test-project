package org.example;


import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class sc9 extends BaseTest {

    @Test
    public void testProductInfoInCart(By driver) {
        driver.findElement((SearchContext) By.id("username")).sendKeys("testUser");
        driver.findElement((SearchContext) By.id("password")).sendKeys("12345");
        driver.findElement((SearchContext) By.id("loginButton")).click();

        driver.findElement((SearchContext) By.id("addProductA")).click();
        driver.findElement((SearchContext) By.id("cartLink")).click();

        List<WebElement> images = driver.findElements((SearchContext) By.tagName("img"));
        List<WebElement> names = driver.findElements((SearchContext) By.className("product-name"));
        List<WebElement> descs = driver.findElements((SearchContext) By.className("product-desc"));

        Assert.assertTrue(images.size() > 0 && names.size() > 0 && descs.size() > 0,
                "Product image, name, and description should appear in cart.");
    }
}
