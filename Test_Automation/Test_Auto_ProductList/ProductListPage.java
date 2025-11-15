package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class ProductListPage {
    WebDriver driver;

    public ProductListPage(WebDriver driver) {
        this.driver = driver;
    }

    By productsTitle = By.className("title");
    By productNames = By.className("inventory_item_name");
    By productPrices = By.className("inventory_item_price");

    // TC-001
    public boolean isPageLoaded() {
        return driver.findElement(productsTitle).isDisplayed();
    }


    // TC-002
    public boolean allProductsHaveNameAndPrice() {
        List<WebElement> names = driver.findElements(productNames);
        List<WebElement> prices = driver.findElements(productPrices);

        if (names.size() == 0 || prices.size() == 0 || names.size() != prices.size()) {
            return false;
        }

        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).getText().isEmpty() || prices.get(i).getText().isEmpty()) {
                return false;
            }
        }

        return true;
    }


    // TC-003
    // Locator للـ Dropdown
    By sortDropdown = By.className("product_sort_container");

    // Method لاختيار Z to A
    public void sortZtoA() {
        Select select = new Select(driver.findElement(sortDropdown));
        select.selectByVisibleText("Name (Z to A)");

    }

    // للتحقق من الترتيب
    public boolean isSortedZtoA() {
        List<WebElement> productNames = driver.findElements(By.className("inventory_item_name"));

        // ناخد النصوص بس
        List<String> names = new ArrayList<>();
        for (WebElement p : productNames) {
            names.add(p.getText());
        }

        // نعمل نسخة منهم ونرتبها بالعكس
        List<String> sortedNames = new ArrayList<>(names);
        Collections.sort(sortedNames, Collections.reverseOrder());

        return names.equals(sortedNames);
    }

    // TC-004
    //Price (low to high)
    public void sortPriceLowToHigh() {
        Select select = new Select(driver.findElement(sortDropdown));
        select.selectByVisibleText("Price (low to high)");
    }

    public boolean isPriceSortedLowToHigh() {

        List<WebElement> pricesElements = driver.findElements(By.className("inventory_item_price"));

        List<Double> prices = new ArrayList<>();

        for (WebElement p : pricesElements) {
            String priceText = p.getText().replace("$", "");
            prices.add(Double.parseDouble(priceText));
        }

        List<Double> sortedPrices = new ArrayList<>(prices);
        Collections.sort(sortedPrices);

        return prices.equals(sortedPrices);
    }

    // TC-005
    public void sortAtoZ() {
        Select select = new Select(driver.findElement(sortDropdown));
        select.selectByVisibleText("Name (A to Z)");
    }
    public boolean isSortedAtoZ() {

        List<WebElement> productNames = driver.findElements(By.className("inventory_item_name"));

        List<String> names = new ArrayList<>();
        for (WebElement p : productNames) {
            names.add(p.getText());
        }

        List<String> sortedNames = new ArrayList<>(names);
        Collections.sort(sortedNames);

        return names.equals(sortedNames);
    }
    // TC-006
    By firstProduct = By.className("inventory_item_name");

    public void openFirstProduct() {
        driver.findElements(firstProduct).get(0).click();
    }
    public String getFirstProductName() {
        return driver.findElements(By.className("inventory_item_name")).get(0).getText();
    }
    // TC-007 -4
//    public void sortPriceLowToHigh() {
//        Select select = new Select(driver.findElement(sortDropdown));
//        select.selectByVisibleText("Price (low to high)");
//    }
//    public boolean isPriceSortedLowToHigh() {
//
//        List<WebElement> pricesElements = driver.findElements(By.className("inventory_item_price"));
//
//        List<Double> prices = new ArrayList<>();
//        for (WebElement p : pricesElements) {
//            String priceText = p.getText().replace("$", "");
//            prices.add(Double.parseDouble(priceText));
//        }
//
//        List<Double> sortedPrices = new ArrayList<>(prices);
//        Collections.sort(sortedPrices);
//
//        return prices.equals(sortedPrices);
//    }

    // TC-008
    public void sortPriceHighToLow() {
        Select select = new Select(driver.findElement(sortDropdown));
        select.selectByVisibleText("Price (high to low)");
    }

    public boolean isPriceSortedHighToLow() {
        List<WebElement> pricesElements = driver.findElements(By.className("inventory_item_price"));

        List<Double> prices = new ArrayList<>();
        for (WebElement p : pricesElements) {
            String priceText = p.getText().replace("$", "").trim();
            prices.add(Double.parseDouble(priceText));
        }

        List<Double> sortedPrices = new ArrayList<>(prices);
        // رتب من الأكبر للأصغر
        Collections.sort(sortedPrices, Collections.reverseOrder());

        return prices.equals(sortedPrices);
    }
    // TC-009 -6
//    public void openFirstProduct() {
//        driver.findElements(By.className("inventory_item_name")).get(0).click();
//    }
//
//    public String getFirstProductName() {
//        return driver.findElements(By.className("inventory_item_name")).get(0).getText();
//    }

    // TC-0010
    By addToCartButtons = By.className("btn_inventory");

    public void addFirstProductToCart() {
        driver.findElements(addToCartButtons).get(0).click();
    }

    By cartBadge = By.className("shopping_cart_badge");

    public int getCartCount() {
        try {
            String count = driver.findElement(cartBadge).getText();
            return Integer.parseInt(count);
        } catch (Exception e) {
            return 0; // لو مفيش Badge يبقى السلة = 0
        }
    }
}
