package scraper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ScraperUtils {
    public static final String SCHOOL_URL = "https://ps.sturgisps.org/public/home.html";
    public static final String GEKO_DRIVER_NAME = "webdriver.gecko.driver";
    public static final String GEKO_DRIVER_PATH = "C:\\Users\\MilkyMan\\Documents\\geckodriver.exe";

    public static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {

        }
    }

    public static List<WebElement> getClassRowWebElements(WebDriver driver) {
        List<WebElement> element = driver.findElements(By.className("linkDescList"));
        return element.get(0).findElements(By.tagName("tr"));
    }
}
