package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.testng.Assert;
import org.testng.annotations.Test;

class AddSingleProductTest extends BaseTest {

    @Test
    public void testAddSingleProductToCart(By driver) {
        // Login
        driver.findElement((SearchContext) By.id("username")).sendKeys("testUser");
        driver.findElement((SearchContext) By.id("password")).sendKeys("12345");
        driver.findElement((SearchContext) By.id("loginButton")).click();

        // Add product to cart
        driver.findElement((SearchContext) By.xpath("//button[contains(text(),'Add to cart')]")).click();

        // Open cart
        driver.findElement((SearchContext) By.id("cartLink")).click();

        // Check if 1 product exists
        int productsCount = driver.findElements((SearchContext) By.className("cart-item")).size();
        Assert.assertEquals(productsCount, 1, "Cart should contain exactly 1 product.");

        // Check total price exists
        String totalPrice = driver.findElement((SearchContext) By.id("totalPrice")).getText();
        Assert.assertTrue(totalPrice != null && !totalPrice.isEmpty(), "Total price should be displayed.");
    }
}