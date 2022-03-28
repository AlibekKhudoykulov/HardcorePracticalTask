package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class GoogleCloudHomePage extends AbstractPage {
    public static final String BASE_URL = " https://cloud.google.com/";

    @FindBy(xpath = "//input[@aria-label='Search']")
    public static WebElement searchButton;

    @FindBy(xpath = "//a[@href='https://cloud.google.com/products/calculator']")
    private static WebElement pricingCalculatorButton;


    public GoogleCloudHomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);

    }
    @Override
    public GoogleCloudHomePage openPage() {
        driver.get(BASE_URL);
        return this;
    }


}
