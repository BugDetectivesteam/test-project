import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;
import java.time.Duration;
import java.util.List;
public class test {
    public class Main {

        static WebDriver driver;
        static String driverPath = "D:\\Apps\\chromedriver-win64\\chromedriver.exe";
        // Setup & TearDown
        public static void setup() {
            File f = new File(driverPath);
            if (!f.exists() || !f.isFile()) {
                System.err.println("ERROR: chromedriver not found at: " + driverPath);
                throw new RuntimeException("chromedriver not found");
            }
            System.setProperty("webdriver.chrome.driver", driverPath);
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        }
        public static void tearDown() {
            if (driver != null) {
                driver.quit();
                driver = null;
            }
        }
        // Helpers
        private static void openHome() {
            driver.get("https://www.saucedemo.com/");
        }

        // returns true if element exists in DOM and isDisplayed()
        private static boolean elementExists(By by) {
            List<WebElement> elems = driver.findElements(by);
            return elems.size() > 0 && elems.get(0).isDisplayed();
        }

        private static String elementText(By by) {
            List<WebElement> elems = driver.findElements(by);
            if (elems.size() == 0) return "";
            return elems.get(0).getText().trim();
        }


        private static String getElementLabel(By by) {
            List<WebElement> elems = driver.findElements(by);
            if (elems.size() == 0) return "";
            WebElement e = elems.get(0);
            String tag = e.getTagName().toLowerCase();

            if ("input".equals(tag) || "button".equals(tag)) {
                String val = e.getAttribute("value");
                if (val != null && !val.trim().isEmpty()) return val.trim();
            }


            String txt = e.getAttribute("value"); // sometimes value exists even on non-inputs
            if (txt != null && !txt.trim().isEmpty()) return txt.trim();

            txt = e.getText();
            return txt == null ? "" : txt.trim();
        }
        // Tests
        public static void tc001_homepageLoads() {
            openHome();
            String url = driver.getCurrentUrl();
            System.out.println("TC001 - Homepage loads: " + (url != null && url.contains("saucedemo.com") ? "PASS" : "FAIL (" + url + ")"));
        }

        public static void tc002_logoDisplayed() {
            openHome();
            boolean ok = elementExists(By.className("login_logo"));
            System.out.println("TC002 - Logo displayed: " + (ok ? "PASS" : "FAIL"));
        }

        public static void tc003_titleCorrect() {
            openHome();
            String title = driver.getTitle();
            System.out.println("TC003 - Title is 'Swag Labs': " + ("Swag Labs".equals(title) ? "PASS" : "FAIL (\"" + title + "\")"));
        }

        public static void tc004_loginFormVisible() {
            openHome();
            boolean u = elementExists(By.id("user-name"));
            boolean p = elementExists(By.id("password"));
            boolean b = elementExists(By.id("login-button"));
            System.out.println("TC004 - Login form visible: " + ((u && p && b) ? "PASS" : "FAIL"));
        }

        public static void tc005_backButton() {
            openHome();
            driver.get("https://example.com/");
            driver.navigate().back();
            boolean ok = driver.getCurrentUrl().contains("saucedemo.com");
            System.out.println("TC005 - Back button returns: " + (ok ? "PASS" : "FAIL"));
        }

        public static void tc006_usernamePlaceholder() {
            openHome();
            List<WebElement> e = driver.findElements(By.id("user-name"));
            String ph = (e.size() > 0) ? e.get(0).getAttribute("placeholder") : "";
            System.out.println("TC006 - Username placeholder: " + ((ph != null && ph.length() > 0) ? "PASS (" + ph + ")" : "FAIL"));
        }

        public static void tc007_passwordPlaceholder() {
            openHome();
            List<WebElement> e = driver.findElements(By.id("password"));
            String ph = (e.size() > 0) ? e.get(0).getAttribute("placeholder") : "";
            System.out.println("TC007 - Password placeholder: " + ((ph != null && ph.length() > 0) ? "PASS (" + ph + ")" : "FAIL"));
        }

        public static void tc008_loginButtonEnabled() {
            openHome();
            List<WebElement> e = driver.findElements(By.id("login-button"));
            boolean enabled = e.size() > 0 && e.get(0).isEnabled();
            System.out.println("TC008 - Login button enabled: " + (enabled ? "PASS" : "FAIL"));
        }


        public static void tc009_loginButtonText() {
            openHome();
            String text = getElementLabel(By.id("login-button"));
            System.out.println("TC009 - Login button text: " + ("Login".equalsIgnoreCase(text) ? "PASS" : "FAIL (\"" + text + "\")"));
        }

        public static void tc010_botImage() {
            openHome();
            // manual check note printed, automated check attempts by selector too
            boolean exists = elementExists(By.cssSelector(".bot_column img")) || elementExists(By.cssSelector(".bot_column"));
            System.out.println("TC010 - Bot image (visual): " + (exists ? "PASS" : "FAIL (manual verify)"));
        }

        public static void tc011_copyRight() {
            openHome();
            // page doesn't really have footer; try known selector then decide
            boolean has = elementExists(By.cssSelector(".login_footer"));
            System.out.println("TC011 - Copyright/footer present: " + (has ? "PASS" : "FAIL"));
        }

        public static void tc012_acceptedUsernames() {
            openHome();
            String txt = elementText(By.className("login_credentials_wrap"));
            boolean ok = txt.toLowerCase().contains("standard_user");
            System.out.println("TC012 - Accepted usernames: " + (ok ? "PASS" : "FAIL"));
        }

        public static void tc013_passwordForAllUsers() {
            openHome();
            String txt = elementText(By.className("login_password"));
            boolean ok = txt.toLowerCase().contains("secret_sauce");
            System.out.println("TC013 - Password for all users: " + (ok ? "PASS" : "FAIL"));
        }

        public static void tc014_formAlignment() {
            openHome();
            List<WebElement> u = driver.findElements(By.id("user-name"));
            List<WebElement> p = driver.findElements(By.id("password"));
            List<WebElement> b = driver.findElements(By.id("login-button"));
            if (u.size() > 0 && p.size() > 0 && b.size() > 0) {
                int dxUP = Math.abs(u.get(0).getLocation().getX() - p.get(0).getLocation().getX());
                int dxUB = Math.abs(u.get(0).getLocation().getX() - b.get(0).getLocation().getX());
                boolean ok = dxUP <= 20 && dxUB <= 80;
                System.out.println("TC014 - Form alignment: " + (ok ? "PASS" : "FAIL"));
            } else {
                System.out.println("TC014 - Form alignment: FAIL (elements missing)");
            }
        }

        public static void tc015_homepageURL() {
            openHome();
            String cur = driver.getCurrentUrl();
            System.out.println("TC015 - Homepage URL: " + (cur.contains("saucedemo.com") ? "PASS" : "FAIL"));
        }

        public static void tc016_faviconDisplayed() {
            openHome();
            List<WebElement> icons = driver.findElements(By.cssSelector("link[rel*='icon']"));
            System.out.println("TC016 - Favicon present: " + (icons.size() > 0 ? "PASS" : "FAIL"));
        }

        public static void tc017_footerPosition() {
            openHome();
            boolean footer = elementExists(By.cssSelector(".login_footer"));
            System.out.println("TC017 - Footer position: " + (footer ? "PASS" : "FAIL (no footer)"));
        }

        public static void tc018_textReadability() {
            openHome();
            String logo = elementText(By.className("login_logo"));
            String ph = "";
            List<WebElement> u = driver.findElements(By.id("user-name"));
            if (u.size() > 0) ph = u.get(0).getAttribute("placeholder");
            boolean ok = logo.length() > 0 && ph != null && ph.length() > 0;
            System.out.println("TC018 - Text readability: " + (ok ? "PASS" : "FAIL"));
        }

        public static void tc019_slowNetwork() {
            System.out.println("TC019 - Slow network: MANUAL CHECK (use DevTools throttling)");
        }
        // MAIN - run tests 1..19

        public static void main(String[] args) {
            try {
                setup();

                tc001_homepageLoads();
                tc002_logoDisplayed();
                tc003_titleCorrect();
                tc004_loginFormVisible();
                tc005_backButton();
                tc006_usernamePlaceholder();
                tc007_passwordPlaceholder();
                tc008_loginButtonEnabled();
                tc009_loginButtonText();
                tc010_botImage();
                tc011_copyRight();
                tc012_acceptedUsernames();
                tc013_passwordForAllUsers();
                tc014_formAlignment();
                tc015_homepageURL();
                tc016_faviconDisplayed();
                tc017_footerPosition();
                tc018_textReadability();
                tc019_slowNetwork();

            } finally {
                tearDown();
            }
        }
    }
}
