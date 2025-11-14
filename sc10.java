package org.example;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class sc10 {


    private WebDriver driver;

    public class ContinueShoppingTest extends BaseTest {

        @Test
        public void testContinueShoppingAndReturn() {
            driver.findElement(By.id("username")).sendKeys("testUser");
            driver.findElement(By.id("password")).sendKeys("12345");
            driver.findElement(By.id("loginButton")).click();

            driver.findElement(By.id("addProductA")).click();
            driver.findElement(By.id("cartLink")).click();

            driver.findElement(By.id("continueShopping")).click();
            driver.navigate().back(); // الرجوع للسلة

            int count = driver.findElements(By.className("cart-item")).size();
            Assert.assertEquals(count, 1, "Cart should retain product after navigating away and returning.");
        }
    }
}
