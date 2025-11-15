package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {


        System.setProperty("webdriver.chrome.driver",
                "E:\\Commponent_automation_Testing\\ChromDriver_Selenium\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver;
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        List<By> elements = new ArrayList<By>();
        elements.add(By.xpath("//div[text()='Sauce Labs Backpack']"));
        elements.add(By.xpath("//div[text()='Sauce Labs Bike Light']"));
        elements.add(By.xpath("//div[text()='Sauce Labs Bolt T-Shirt']"));
        elements.add(By.xpath("//div[text()='Sauce Labs Fleece Jacket']"));
        elements.add(By.xpath("//div[text()='Sauce Labs Onesie']"));
        elements.add(By.xpath("//div[text()='Test.allTheThings() T-Shirt (Red)']"));

//        List<String> check = new ArrayList<>();
//        check.add("Sauce Labs Backpack");
//        check.add("Sauce Labs Bike Light");
//        check.add("Sauce Labs Bolt T-Shirt");
//        check.add("Sauce Labs Fleece Jacket");
//        check.add("Sauce Labs Onesie");
//        check.add("Test.allTheThings() T-Shirt (Red)");

       List<By> prices = new ArrayList<>();
       prices.add(By.xpath("//div[text()='$49.99']"));
       prices.add(By.xpath("//div[text()='$15.99']"));
       prices.add(By.xpath("//div[text()='$7.99']"));
       prices.add(By.xpath("//div[text()='$√-1']"));
       prices.add(By.xpath("//div[text()='$15.99']"));
       prices.add(By.xpath("//div[text()='$$29.99']"));

       List<String> pr = new ArrayList<>();
       pr.add("$29.99");
       pr.add("$9.99");
       pr.add("$15.99");
       pr.add("$49.99");
       pr.add("$7.99");
       pr.add("$15.99");






    }
}