package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static waitmanager.WaitManager.waitForElementVisibility;

public class GoogleCloudHomePage extends AbstractPage {
    public static final String BASE_URL = " https://cloud.google.com/";

    @FindBy(xpath = "//input[@aria-label='Search']")
    public WebElement searchButton;


    public GoogleCloudHomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);

    }

    @Override
    public GoogleCloudHomePage openPage() {
        driver.get(BASE_URL);
        return this;
    }


    public GoogleCloudHomePage openPricingCalculator() {
        searchButton.sendKeys("Google Cloud Platform Pricing Calculator");
        searchButton.submit();
        GoogleCloudResultPage googleCloudResultPage = new GoogleCloudResultPage(driver);
        waitForElementVisibility(googleCloudResultPage.searchResult);
        googleCloudResultPage.searchResult.click();
        return this;
    }

}
