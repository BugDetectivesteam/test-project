package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

class CartPage {
    WebDriver driver;

    By cartItems = By.className("cart-item");
    By qtyField = By.name("quantity");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public int getItemsCount() {
        return driver.findElements(cartItems).size();
    }

    public int getQuantity() {
        try {
            return Integer.parseInt(driver.findElement(qtyField).getAttribute("value"));
        } catch (Exception e) {
            return 1;
        }
    }
}
