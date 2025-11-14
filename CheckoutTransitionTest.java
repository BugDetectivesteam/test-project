package org.example;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTransitionTest extends BaseTest {

    @Test
    public void testCheckoutTransition(WebDriver driver) {
        driver.findElement(By.id("username")).sendKeys("testUser");
        driver.findElement(By.id("password")).sendKeys("12345");
        driver.findElement(By.id("loginButton")).click();

        driver.findElement(By.id("addProductA")).click();
        driver.findElement(By.id("cartLink")).click();

        driver.findElement(By.id("checkoutButton")).click();

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("checkout"), "User should be redirected to checkout page.");
    }
}
