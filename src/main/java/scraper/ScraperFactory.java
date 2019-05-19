package scraper;

import com.google.common.base.Strings;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ScraperFactory {
    private static WebDriver driver;
    private static final String URL = "https://ps.sturgisps.org/public/home.html";

    static {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\MilkyMan\\Documents\\geckodriver.exe");
        if (driver == null) {
            driver = new FirefoxDriver();
        }
    }

    public static void setDriverUrl(String url) {
        driver.get(url);
    }

    public static WebDriver getDriver() {
        if (driver.getCurrentUrl().equals("about:blank")) {
            driver.get(URL);
            login();
        }
        return driver;
    }

    public static void login() {
        if (!Strings.isNullOrEmpty(driver.getCurrentUrl()) && "https://ps.sturgisps.org/public/home.html".equals(driver.getCurrentUrl()))
            driver.findElement(By.id("fieldAccount")).sendKeys("483l7b");
        driver.findElement(By.id("fieldPassword")).sendKeys("567");
        driver.findElement(By.id("btn-enter-sign-in")).click();
    }
}
