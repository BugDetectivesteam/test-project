import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {

    // --- Helper methods ---
    public static WebDriver setup() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        return driver;
    }

    public static void tearDown(WebDriver driver) {
        if (driver != null) {
            driver.quit();
        }
    }

    public static void login(WebDriver driver, String username, String password) {
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
    }

   public static void TC101_loginValidUser() {
        WebDriver driver = setup();
        try {
            login(driver, "standard_user", "secret_sauce");
            boolean loggedIn = driver.findElement(By.className("inventory_list")).isDisplayed();
            System.out.println("TC101 - Login with valid standard user: " + (loggedIn ? "PASS" : "FAIL"));
        } catch (Exception e) {
            System.out.println("TC101 - Login with valid standard user: FAIL (" + e.getMessage() + ")");
        } finally {
            tearDown(driver);
        }
    }

    public static void TC102_loginLockedOutUser() {
        WebDriver driver = setup();
        try {
            login(driver, "locked_out_user", "secret_sauce");
            boolean error = driver.findElement(By.cssSelector("[data-test='error']")).isDisplayed();
            System.out.println("TC102 - Login with locked out user: " + (error ? "PASS" : "FAIL"));
        } catch (Exception e) {
            System.out.println("TC102 - Login with locked out user: FAIL (" + e.getMessage() + ")");
        } finally {
            tearDown(driver);
        }
    }

    public static void TC103_loginSpecialUsers() {
        String[] users = {"problem_user", "performance_glitch_user", "visual_user", "error_user"};
        for (String user : users) {
            WebDriver driver = setup();
            try {
                login(driver, user, "secret_sauce");
                boolean loggedIn = driver.findElement(By.id("logout_sidebar_link")).isDisplayed();
                System.out.println("TC103 - Login with special user (" + user + "): " + (loggedIn ? "PASS" : "FAIL"));
            } catch (Exception e) {
                System.out.println("TC103 - Login with special user (" + user + "): FAIL (" + e.getMessage() + ")");
            } finally {
                tearDown(driver);
            }
        }
    }

    public static void TC104_loginInvalidCredentials() {
        WebDriver driver = setup();
        try {
            login(driver, "invalid_user", "wrong_password");
            boolean error = driver.findElement(By.cssSelector("[data-test='error']")).isDisplayed();
            System.out.println("TC104 - Login with invalid credentials: " + (error ? "PASS" : "FAIL"));
        } catch (Exception e) {
            System.out.println("TC104 - Login with invalid credentials: FAIL (" + e.getMessage() + ")");
        } finally {
            tearDown(driver);
        }
    }
    public static void TC105_loginEmptyFields() {
        WebDriver driver = setup();
        try {
            driver.findElement(By.id("login-button")).click();
            boolean errorDisplayed = driver.findElement(By.cssSelector("[data-test='error']")).isDisplayed();
            System.out.println("TC105 - Login with empty fields: " + (errorDisplayed ? "PASS" : "FAIL"));
        } catch (Exception e) {
            System.out.println("TC105 - Login with empty fields: FAIL (" + e.getMessage() + ")");
        } finally {
            tearDown(driver);
        }
    }


    public static void main(String[] args) {
        TC101_loginValidUser();
        TC102_loginLockedOutUser();
        TC103_loginSpecialUsers();
        TC104_loginInvalidCredentials();
        TC105_loginEmptyFields();

    }
}
