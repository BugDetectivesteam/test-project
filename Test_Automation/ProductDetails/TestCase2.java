import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.Assert;

//import org.junit.AfterClass;
//import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class TestCase2 {
    private WebDriver driver;


    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver",
                "E:\\Commponent_automation_Testing\\ChromDriver_Selenium\\chromedriver-win64\\chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("locked_out_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
    }
    @Test
    public void productDetailsTest() {

        List<WebElement> elements = driver.findElements(By.cssSelector("inventory_item_name "));

        for (WebElement element : elements)
        {
           element.click();


            // product title in detail page
            WebElement item = driver.findElement(By.className("inventory_item_name "));
            Assert.assertEquals(item.getText(), "Sauce Labs Backpack");

            // check price
            WebElement priceEl = driver.findElement(By.cssSelector(".inventory_details_price"));
            Assert.assertEquals(priceEl.getText(), "$29.99");

            // check description
            WebElement descEl = driver.findElement(By.cssSelector(".inventory_details_desc.large_size"));
            String expectedDesc = "carry.allTheThings() with the sleek, streamlined Sly Pack that melds " +
                    "uncompromising style with unequaled laptop and tablet protection.";
            Assert.assertEquals(descEl.getText(), expectedDesc);

            // check image displayed
            WebElement img = driver.findElement(By.cssSelector(".inventory_details_img"));
            Assert.assertTrue(img.isDisplayed());

            // check logo
            WebElement logo = driver.findElement(By.cssSelector(".app_logo"));
            Assert.assertTrue(logo.isDisplayed());

            driver.navigate().back();
            elements = driver.findElements(By.className("inventory_item_name"));
        }
        }

        @AfterClass
        public void tearDown () {
            driver.quit();
        }



}
