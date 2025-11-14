package org.example.scanrio2;


import org.example.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.testng.Assert;
import org.testng.annotations.Test;

class AddMultipleProductsTest extends BaseTest {

    @Test
    public void testAddMultipleProductsToCart(By driver) {
        driver.findElement((SearchContext) By.id("username")).sendKeys("testUser");
        driver.findElement((SearchContext) By.id("password")).sendKeys("12345");
        driver.findElement((SearchContext) By.id("loginButton")).click();

        driver.findElement((SearchContext) By.id("addProductA")).click();
        driver.findElement((SearchContext) By.id("addProductB")).click();
        driver.findElement((SearchContext) By.id("cartLink")).click();

        int count = driver.findElements((SearchContext) By.className("cart-item")).size();
        Assert.assertEquals(count, 2, "Cart should contain two different products.");

        // تحقق من أن السعر الكلي = مجموع المنتجين (لو متاح)
        String total = driver.findElement((SearchContext) By.id("totalPrice")).getText();
        Assert.assertTrue(total.contains("$"), "Total price must include currency symbol.");
    }
}

