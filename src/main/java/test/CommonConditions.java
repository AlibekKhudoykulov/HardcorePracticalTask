package test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import util.TestListener;
import webdriver.WebDriverSetup;

@Listeners({TestListener.class})
public class CommonConditions {
    private Logger log = LogManager.getRootLogger();

    protected WebDriver driver;


    @BeforeMethod()
    public void setUp()
    {
        log.warn("testdata.user.name");
        driver = WebDriverSetup.getDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void stopBrowser()
    {
        WebDriverSetup.closeDriver();
    }
}
