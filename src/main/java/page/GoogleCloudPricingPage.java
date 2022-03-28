package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import page.AbstractPage;

public class GoogleCloudPricingPage extends AbstractPage {
    private static final String BASE_URL = "https://cloud.google.com/products/calculator";

    @FindBy(css = "iframe[src*=calculator]")
    public WebElement calculatorIFrame;

    @FindBy(id = "myFrame")
    public WebElement myFrame;

    @FindBy(css = "[ng-model$='quantity']")
    public WebElement inputNumberOfInstances;

    @FindBy(css = "md-select[ng-change*=Series]")
    public WebElement seriesSelectField;

    @FindBy(css = "md-option[value=n1]")
    public WebElement seriesSelectedOptions;

    @FindBy(css = "md-select[ng-change*=onInstanceChange]")
    public WebElement machineTypeSelectedField;

    @FindBy(css = "md-option[value$=CP-COMPUTEENGINE-VMIMAGE-N1-STANDARD-8]")
    public WebElement machineTypeSelect;

    @FindBy(xpath = "//*[@id=\"mainForm\"]/div[2]/div/md-card/md-card-content/div/div[1]/form/div[11]/div[1]/md-input-container/md-checkbox/div[2]")
    public WebElement addGPUCheckbox;

    @FindBy(css = "md-select[ng-model$=gpuCount]")
    public WebElement numberOfGPUDropdown;

    @FindBy(css = "div.md-clickable md-select-menu md-content md-option[value=\"1\"]")
    public WebElement chooseNumberOfGPU;

    @FindBy(css = "md-select[placeholder='GPU type']")
    public WebElement gPUTypeDropdown;

    @FindBy(css = "div.md-clickable md-select-menu md-content md-option[value=\"NVIDIA_TESLA_P100\"]")
    public WebElement chooseGPUType;

    @FindBy(css = "md-select[placeholder='Local SSD']")
    public WebElement localSSDDropdown;

    @FindBy(css = "div.md-clickable md-select-menu md-content md-option[value=\"2\"]")
    public WebElement chooseLocalSSDNumber;

    @FindBy(css = "md-select[placeholder=\"Datacenter location\"]")
    public WebElement datacenterLocationDropdown;

    @FindBy(css = "div.md-clickable md-select-menu md-content md-option[value=\"europe-west3\"]")
    public WebElement datacenterLocationChoice;

    @FindBy(css = "md-select[placeholder=\"Committed usage\"]")
    public WebElement committedUsageDropdown;

    @FindBy(css = "div.md-clickable md-select-menu md-content md-option[value=\"1\"]")
    public WebElement committedUsageChoice;

    @FindBy(xpath = "//button[@aria-label='Add to Estimate']")
    public WebElement addToEstimateButton;

    @FindBy(css = "#email_quote")
    public WebElement eMailEstimateForm;

    @FindBy(css = "input[ng-model$='email']")
    public WebElement eMailInputField;


    @FindBy(css = "b[class=ng-binding]")
    public WebElement totalCostFieldInComputeEngineForm;

    @FindBy(css = "button[aria-label='Send Email']")
    public WebElement sendEMail;

    public GoogleCloudPricingPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public GoogleCloudPricingPage openPage() {
        driver.get(BASE_URL);
        return this;
    }

}
