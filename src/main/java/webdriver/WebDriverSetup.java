package webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverSetup {

    private static WebDriver driver;

    private WebDriverSetup() {
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            setupWebDriver();
        }
        return driver;
    }

    private static void setupWebDriver() {
        if (null == driver) {
            if ("firefox".equals(System.getProperty("browser"))) {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            }
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }

    }
    public static void closeDriver(){
        driver.quit();
        driver = null;
    }
}

