package Tests;

import Pages.ProductDetailsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import Pages.LoginPage;
import Pages.ProductListPage;
import java.util.List;

public class TestProductList {

    public static void main(String[] args) {

//         TC001_VerifyProductListLoads();
//         TC002_VerifyAllProductsHaveNameAndPrice();
//          TC003_VerifySortingZtoA();
//            TC004_VerifyPriceLowToHigh();
//           TC005_VerifySortingAtoZ();
//                TC006_VerifyNavigationToProductDetails();
//                TC007_VerifySortingPriceLowToHigh_ProblemUser();
//        TC008_VerifySortingPriceHighToLow_ProblemUser();
//        TC009_VerifyProductDetailsNavigation();
//        TC010_VerifyAddToCartButtonWorks();
    }

    // ==============================
    // Test Case 1
    // ==============================
    public static void TC001_VerifyProductListLoads() {

        System.setProperty("webdriver.chrome.driver", "E:\\chromeDriver\\chromedriver-win64\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");

        // Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        // Verify product list page
        ProductListPage productListPage = new ProductListPage(driver);
        if(productListPage.isPageLoaded()) {
            System.out.println("TC001: Product list displayed correctly - PASS");
        } else {
            System.out.println("TC001: Product list not displayed - FAIL");
        }

        driver.quit();
    }

    // ==============================
    // Test Case 2
    // ==============================
    public static void TC002_VerifyAllProductsHaveNameAndPrice() {

        System.setProperty("webdriver.chrome.driver", "E:\\chromeDriver\\chromedriver-win64\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");

        // Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        // Verify name & price
        ProductListPage productListPage = new ProductListPage(driver);
        if(productListPage.allProductsHaveNameAndPrice()) {
            System.out.println("TC002: All products have name and price - PASS");
        } else {
            System.out.println("TC002: Some products missing name or price - FAIL");
        }

        driver.quit();
    }

    // ==============================
    // Test Case 3
    // ==============================
    public static void TC003_VerifySortingZtoA() {

        System.setProperty("webdriver.chrome.driver", "E:\\chromeDriver\\chromedriver-win64\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");

        // Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("problem_user", "secret_sauce");

        // Page object
        ProductListPage productListPage = new ProductListPage(driver);

        // نختار Z to A
        productListPage.sortZtoA();

        // verification
        if (productListPage.isSortedZtoA()) {
            System.out.println("TC003: Products sorted Z → A correctly - PASS");
        } else {
            System.out.println("TC003: Products NOT sorted correctly - FAIL");
        }

        driver.quit();
    }

    // ==============================
    // Test Case 4
    // ==============================
    public static void TC004_VerifyPriceLowToHigh() {

        System.setProperty("webdriver.chrome.driver", "E:\\chromeDriver\\chromedriver-win64\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");

        // Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        // Product list page
        ProductListPage productListPage = new ProductListPage(driver);

        // اختار Price Low → High
        productListPage.sortPriceLowToHigh();

        // verification
        if (productListPage.isPriceSortedLowToHigh()) {
            System.out.println("TC004: Prices sorted Low → High correctly - PASS");
        } else {
            System.out.println("TC004: Prices NOT sorted correctly - FAIL");
        }

        driver.quit();
    }

    // ==============================
    // Test Case 5
    // ==============================
    public static void TC005_VerifySortingAtoZ() {

        System.setProperty("webdriver.chrome.driver", "E:\\chromeDriver\\chromedriver-win64\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");

        // Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        // Page object
        ProductListPage productListPage = new ProductListPage(driver);

        // اختار A → Z
        productListPage.sortAtoZ();

        // verification
        if (productListPage.isSortedAtoZ()) {
            System.out.println("TC005: Products sorted A → Z correctly - PASS");
        } else {
            System.out.println("TC005: Products NOT sorted correctly - FAIL");
        }

        driver.quit();
    }

    // ==============================
    // Test Case 6
    // ==============================
    public static void TC006_VerifyNavigationToProductDetails() {

        System.setProperty("webdriver.chrome.driver", "E:\\chromeDriver\\chromedriver-win64\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");

        // Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("problem_user", "secret_sauce");

        // Product list page
        ProductListPage productListPage = new ProductListPage(driver);

        // Get first product name
        String expectedName = productListPage.getFirstProductName();

        // Click first product
        productListPage.openFirstProduct();

        // Details page
        ProductDetailsPage detailsPage = new ProductDetailsPage(driver);

        // Get name from details page
        String actualName = detailsPage.getDetailsProductName();

        if (expectedName.equals(actualName)) {
            System.out.println("TC006: Navigation correct - PASS");
        } else {
            System.out.println("TC006: Navigation failed or wrong product - FAIL");
        }

        driver.quit();
    }


    // ==============================
    // Test Case 7
    // ==============================
    public static void TC007_VerifySortingPriceLowToHigh_ProblemUser() {

        System.setProperty("webdriver.chrome.driver", "E:\\chromeDriver\\chromedriver-win64\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");

        // Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("problem_user", "secret_sauce");

        // Product list page
        ProductListPage productListPage = new ProductListPage(driver);

        // اختار Price (Low → High)
        productListPage.sortPriceLowToHigh();

        // verification
        if (productListPage.isPriceSortedLowToHigh()) {
            System.out.println("TC007: Prices sorted correctly (unexpected) - PASS");
        } else {
            System.out.println("TC007: Sorting incorrect (expected FAIL) - FAIL");
        }

        driver.quit();
    }

    // ==============================
    // Test Case 8
    // ==============================
    public static void TC008_VerifySortingPriceHighToLow_ProblemUser() {

        System.setProperty("webdriver.chrome.driver", "E:\\chromeDriver\\chromedriver-win64\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");

        // Login as problem_user
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("problem_user", "secret_sauce");

        // Product list page
        ProductListPage productListPage = new ProductListPage(driver);

        // Select Price (high to low)
        productListPage.sortPriceHighToLow();

        // verification
        if (productListPage.isPriceSortedHighToLow()) {
            System.out.println("TC008: Prices sorted High → Low correctly (unexpected) - PASS");
        } else {
            System.out.println("TC008: Prices NOT sorted correctly (expected FAIL) - FAIL");
        }

        driver.quit();
    }

    // ==============================
    // Test Case 9
    // ==============================
    public static void TC009_VerifyProductDetailsNavigation() {

        System.setProperty("webdriver.chrome.driver", "E:\\chromeDriver\\chromedriver-win64\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");

        // Login with standard_user
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        // Product list page
        ProductListPage productListPage = new ProductListPage(driver);

        // Get first product name
        String expectedName = productListPage.getFirstProductName();

        // Click first product
        productListPage.openFirstProduct();

        // Details page
        ProductDetailsPage detailsPage = new ProductDetailsPage(driver);

        // Get details product name
        String actualName = detailsPage.getDetailsProductName();

        if (expectedName.equals(actualName)) {
            System.out.println("TC009: Navigation to product details works correctly - PASS");
        } else {
            System.out.println("TC009: Wrong product opened - FAIL");
        }

        driver.quit();
    }

    // ==============================
    // Test Case 10
    // ==============================
    public static void TC010_VerifyAddToCartButtonWorks() {

        System.setProperty("webdriver.chrome.driver", "E:\\chromeDriver\\chromedriver-win64\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");

        // Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        // Product list page
        ProductListPage productListPage = new ProductListPage(driver);

        // Before add to cart
        int before = productListPage.getCartCount();

        // Add first product
        productListPage.addFirstProductToCart();

        // After add to cart
        int after = productListPage.getCartCount();

        if (after == before + 1) {
            System.out.println("TC010: Cart count increased correctly - PASS");
        } else {
            System.out.println("TC010: Cart count NOT updated correctly - FAIL");
        }

        driver.quit();
    }

    }