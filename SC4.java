package org.example;


import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.testng.Assert;
import org.testng.annotations.Test;

class RemoveProductTest extends BaseTest {

    @Test
    public void testRemoveProductFromCart(By driver) {
        // Login & add products
        driver.findElement((SearchContext) By.id("username")).sendKeys("testUser");
        driver.findElement((SearchContext) By.id("password")).sendKeys("12345");
        driver.findElement((SearchContext) By.id("loginButton")).click();

        driver.findElement((SearchContext) By.id("addProductA")).click();
        driver.findElement((SearchContext) By.id("addProductB")).click();
        driver.findElement((SearchContext) By.id("cartLink")).click();

        // Remove one product
        driver.findElement((SearchContext) By.xpath("//button[contains(text(),'Remove')]")).click();

        // Verify one product left
        int count = driver.findElements((SearchContext) By.className("cart-item")).size();
        Assert.assertEquals(count, 1, "Cart should contain one product after removing one.");
    }
}
