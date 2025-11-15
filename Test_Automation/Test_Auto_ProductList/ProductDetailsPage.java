package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDetailsPage {

    WebDriver driver;

    public ProductDetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    // test TC 6
    By productTitle = By.className("inventory_details_name");

    public boolean isOnDetailsPage() {
        return driver.findElement(productTitle).isDisplayed();
    }

    public String getDetailsProductName() {
        return driver.findElement(By.className("inventory_details_name")).getText();
    }
    // test TC 9
//    By productTitle = By.className("inventory_details_name");
//
//    public boolean isOnDetailsPage() {
//        return driver.findElement(productTitle).isDisplayed();
//    }
//
//    public String getDetailsProductName() {
//        return driver.findElement(productTitle).getText();
//    }

}
