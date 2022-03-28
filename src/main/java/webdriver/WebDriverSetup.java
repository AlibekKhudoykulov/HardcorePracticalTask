package webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
		}
	}

