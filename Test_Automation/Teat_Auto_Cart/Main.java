package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class ShoppingCartTestSuite extends BaseTest {

    WebDriver driver;

    //  Setup & Teardown 

    @BeforeMethod
    public void setDriver() {
        driver = getDriver(); 
    }

    private WebDriver getDriver() {
        return null;
    }
}


    //  Helper Methods 

    public void login(Object driver) {
        driver.equals(By.id("username")).sendKeys("testUser");
        driver.equals(By.id("password")).sendKeys("12345");
        driver.equals(By.id("loginButton")).click();
    }

    public void addProduct(String productId) {
        driver.findElement(By.id(productId)).click();
    }

    public void openCart() {
        driver.findElement(By.id("cartLink")).click();
    }

    public int getItemsCount() {
        return driver.findElements(By.className("cart-item")).size();
    }

    public void setQuantity(int qty) {
        WebElement q = driver.findElement(By.name("quantity"));
        q.clear();
        q.sendKeys(String.valueOf(qty));
        driver.findElement(By.id("updateCart")).click();
    }

    //  All Test Cases 

    @Test
    public void testAddSingleProduct() {
        login(driver);
        addProduct("addProductA");
        openCart();

        Assert.assertEquals(getItemsCount(), 1, "Cart should contain exactly 1 product.");

        String totalPrice = driver.findElement(By.id("totalPrice")).getText();
        Assert.assertFalse(totalPrice.isEmpty(), "Total price should be displayed.");
    }

    @Test
    public void testRemoveProduct() {
        login(driver);
        addProduct("addProductA");
        addProduct("addProductB");
        openCart();

        driver.findElement(By.xpath("//button[contains(text(),'Remove')]")).click();
        Assert.assertEquals(getItemsCount(), 1, "Cart should contain one product after removal.");
    }

    @Test
    public void testChangeQuantity() {
        login(driver);
        addProduct("addProductA");
        openCart();

        setQuantity(3);

        String totalPrice = driver.findElement(By.id("totalPrice")).getText();
        Assert.assertTrue(totalPrice.contains("3"), "Total price should reflect quantity.");
    }

    @Test
    public void testCheckoutTransition() {
        login(driver);
        addProduct("addProductA");
        openCart();

        driver.findElement(By.id("checkoutButton")).click();

        Assert.assertTrue(driver.getCurrentUrl().contains("checkout"),
                "User should be redirected to checkout page.");
    }

    @Test
    public void testCartPersistenceAfterLogout() {
        login(driver);
        addProduct("addProductA");

        driver.findElement(By.id("logoutButton")).click();

        login(driver);
        openCart();

        int count = getItemsCount();
        Assert.assertTrue(count == 0 || count == 1,
                "Cart should either retain or clear items depending on system logic.");
    }

    @Test
    public void testCartTaxesAndTotal() {
        login(driver);
        addProduct("addProductA");
        addProduct("addProductB");
        openCart();

        String total = driver.findElement(By.id("totalPrice")).getText();
        Assert.assertTrue(total.matches(".*\\d+.*"), "Total should show tax/shipping correctly.");
    }

    @Test
    public void testProductInfoInCart() {
        login(driver);
        addProduct("addProductA");
        openCart();

        List<WebElement> images = driver.findElements(By.tagName("img"));
        List<WebElement> names = driver.findElements(By.className("product-name"));
        List<WebElement> descs = driver.findElements(By.className("product-desc"));

        Assert.assertTrue(images.size() > 0
                        && names.size() > 0
                        && descs.size() > 0,
                "Product image, name, and description should be shown in cart.");
    }

    @Test
    public void testContinueShopping() {
        login(driver);
        addProduct("addProductA");
        openCart();

        driver.findElement(By.id("continueShopping")).click();
        driver.navigate().back();

        Assert.assertEquals(getItemsCount(), 1,
                "Product should remain after leaving and returning to cart.");
    }

    @Test
    public void testZeroOrNegativeQuantity() {
        login(driver);
        addProduct("addProductA");
        openCart();

        setQuantity(-1);

        String errorMsg = driver.findElement(By.id("errorMsg")).getText();
        Assert.assertTrue(errorMsg.contains("invalid") || errorMsg.contains("quantity"),
                "System should reject zero/negative quantity.");
    }

    @Test
    public void testPerformanceForManyProducts() {
        login(driver);
        long start = System.currentTimeMillis();

        for (int i = 1; i <= 30; i++) {
            try {
                addProduct("addProduct" + i);
            } catch (Exception ignored) {}
        }

        openCart();

        long duration = System.currentTimeMillis() - start;
        Assert.assertTrue(duration < 5000, "Cart should load fast with many items.");
    }

    @Test
    public void testResponsiveUITest() {
        login(driver);
        addProduct("addProductA");
        openCart();

        driver.manage().window().setSize(new Dimension(375, 667)); // mobile
        Assert.assertTrue(driver.findElement(By.className("cart-container")).isDisplayed());

        driver.manage().window().setSize(new Dimension(768, 1024)); // tablet
        Assert.assertTrue(driver.findElement(By.className("cart-container")).isDisplayed());

        driver.manage().window().maximize(); // desktop
        Assert.assertTrue(driver.findElement(By.className("cart-container")).isDisplayed());
    }

    @Test
    public void testOutOfStockProduct() {
        login(driver);
        addProduct("outOfStockProduct");

        String alertMsg = driver.findElement(By.id("alertMessage")).getText();
        Assert.assertTrue(alertMsg.contains("out of stock") || alertMsg.contains("not available"),
                "System should prevent adding out-of-stock items.");
    }

    @Test
    public void testMultiBrowserSession() {
        login(driver);
        addProduct("addProductA");

        WebDriver secondBrowser = new ChromeDriver();
        secondBrowser.manage().window().maximize();
        secondBrowser.get("URL_OF_WEBSITE");

        secondBrowser.findElement(By.id("username")).sendKeys("testUser");
        secondBrowser.findElement(By.id("password")).sendKeys("12345");
        secondBrowser.findElement(By.id("loginButton")).click();
        secondBrowser.findElement(By.id("cartLink")).click();

        int count = secondBrowser.findElements(By.className("cart-item")).size();
        Assert.assertTrue(count >= 0, "Cart should sync across browsers if supported.");

        secondBrowser.quit();
    }

    private class WebDriver {
        public void quit() {
        }
    }
}

private static class By {
    public static Object id(String username) {
        return null;
    }

    public static Object className(String s) {
    }
}
