package page;

import org.apache.commons.lang3.math.NumberUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;

import static waitmanager.WaitManager.waitForElementVisibility;

public class GoogleCloudPricingPage extends AbstractPage {
    private static final String BASE_URL = "https://cloud.google.com/products/calculator";
    private ArrayList<String> tabs;
    private String eMailAddress;
    private TempMailPage tempMailPage;


    final String MACHINE_TYPE = "md-option[value$=%s]";

    @FindBy(css = "iframe[src*=calculator]")
    private WebElement calculatorIFrame;

    @FindBy(id = "myFrame")
    private WebElement myFrame;

    @FindBy(css = "[ng-model$='quantity']")
    private WebElement inputNumberOfInstances;

    @FindBy(css = "md-select[ng-change*=Series]")
    private WebElement seriesSelectField;

    @FindBy(css = "md-option[value=n1]")
    private WebElement seriesSelectedOptions;

    @FindBy(css = "md-select[ng-change*=onInstanceChange]")
    private WebElement machineTypeSelectedField;

    @FindBy(css = "md-option[value$=CP-COMPUTEENGINE-VMIMAGE-N1-STANDARD-8]")
    private WebElement machineTypeSelect;

    @FindBy(xpath = "//*[@id=\"mainForm\"]/div[2]/div/md-card/md-card-content/div/div[1]/form/div[11]/div[1]/md-input-container/md-checkbox/div[2]")
    private WebElement addGPUCheckbox;

    @FindBy(css = "md-select[ng-model$=gpuCount]")
    private WebElement numberOfGPUDropdown;

    @FindBy(css = "div.md-clickable md-select-menu md-content md-option[value=\"1\"]")
    private WebElement chooseNumberOfGPU;

    @FindBy(css = "md-select[placeholder='GPU type']")
    private WebElement gPUTypeDropdown;

    @FindBy(css = "div.md-clickable md-select-menu md-content md-option[value=\"NVIDIA_TESLA_P100\"]")
    private WebElement chooseGPUType;

    @FindBy(css = "md-select[placeholder='Local SSD']")
    private WebElement localSSDDropdown;

    @FindBy(css = "div.md-clickable md-select-menu md-content md-option[value=\"2\"]")
    private WebElement chooseLocalSSDNumber;

    @FindBy(css = "md-select[placeholder=\"Datacenter location\"]")
    private WebElement datacenterLocationDropdown;

    @FindBy(css = "div.md-clickable md-select-menu md-content md-option[value=\"europe-west3\"]")
    private WebElement datacenterLocationChoice;

    @FindBy(css = "md-select[placeholder=\"Committed usage\"]")
    private WebElement committedUsageDropdown;

    @FindBy(css = "div.md-clickable md-select-menu md-content md-option[value=\"1\"]")
    private WebElement committedUsageChoice;

    @FindBy(xpath = "//button[@aria-label='Add to Estimate']")
    private WebElement addToEstimateButton;

    @FindBy(css = "#email_quote")
    private WebElement eMailEstimateForm;

    @FindBy(css = "input[ng-model$='email']")
    private WebElement eMailInputField;

    @FindBy(css = "md-list-item[ng-if*=initialInputs]")
    private WebElement vMClassFieldInComputeEngineForm;

    @FindBy(xpath = "//*[@id=\"compute\"]/md-list/md-list-item[5]/div[1]")
    private WebElement instanceTypeFieldInComputeEngineForm;

    @FindBy(xpath = "//*[@id=\"compute\"]/md-list/md-list-item[1]/div")
    private WebElement regionFieldInComputeEngineForm;

    @FindBy(css = "md-list-item[ng-if*=ssd]")
    private WebElement availableSSDFieldInComputeEngineForm;

    @FindBy(xpath = "//*[@id=\"compute\"]/md-list/md-list-item[3]")
    private WebElement commitmentFieldInComputeEngineForm;

    @FindBy(css = "b[class=ng-binding]")
    private WebElement totalCostFieldInComputeEngineForm;

    @FindBy(css = "button[aria-label='Send Email']")
    private WebElement sendEMail;

    public GoogleCloudPricingPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public GoogleCloudPricingPage openPage() {
        driver.get(BASE_URL);
        return this;
    }

    public GoogleCloudPricingPage switchToCalculatorFrame() {
        driver.switchTo().frame(calculatorIFrame)
                .switchTo().frame(myFrame);
        return this;
    }

    public GoogleCloudPricingPage inputNumberOfInstances(String number) {
        inputNumberOfInstances.click();
        inputNumberOfInstances.sendKeys(number);
        return this;
    }

    public GoogleCloudPricingPage selectSeries() {
        seriesSelectField.click();
        waitForElementVisibility(seriesSelectedOptions);
        seriesSelectedOptions.click();
        return this;
    }

    public GoogleCloudPricingPage selectMachineType(String machineType) {
        waitForElementVisibility(machineTypeSelectedField);
        machineTypeSelectedField.click();
        By machineTypeLocator=By.cssSelector(String.format(MACHINE_TYPE,machineType));
        driver.findElement(machineTypeLocator).click();
        return this;
    }

    public GoogleCloudPricingPage selectMachineType() {
        waitForElementVisibility(machineTypeSelectedField);
        machineTypeSelectedField.click();
        waitForElementVisibility(machineTypeSelect);
        machineTypeSelect.click();
        return this;
    }

    public GoogleCloudPricingPage addGPU() {
        waitForElementVisibility(addGPUCheckbox);
        addGPUCheckbox.click();
        waitForElementVisibility(gPUTypeDropdown);
        gPUTypeDropdown.click();
        waitForElementVisibility(chooseGPUType);
        chooseGPUType.click();
        numberOfGPUDropdown.click();
        waitForElementVisibility(chooseNumberOfGPU);
        chooseNumberOfGPU.click();

        return this;
    }

    public GoogleCloudPricingPage addSSD() {
        localSSDDropdown.click();
        waitForElementVisibility(chooseLocalSSDNumber);
        chooseLocalSSDNumber.click();
        return this;
    }

    public GoogleCloudPricingPage selectDatacenterLocation() {
        datacenterLocationDropdown.click();
        waitForElementVisibility(datacenterLocationChoice);
        datacenterLocationChoice.click();
        return this;
    }

    public GoogleCloudPricingPage selectCommittedUsage() {
        committedUsageDropdown.click();
        waitForElementVisibility(committedUsageChoice);
        committedUsageChoice.click();
        return this;
    }

    public void pressAddToEstimate() {
        addToEstimateButton.click();
    }

    public GoogleCloudPricingPage addTenMinutesEMailTab() {
        ((JavascriptExecutor) driver).executeScript("window.open('https://temp-mail.io/en','_blank')");
        tabs = new ArrayList<>(driver.getWindowHandles());
        return this;
    }

    public GoogleCloudPricingPage getMailAddressFromTenMinutesMailSite() {
        driver.switchTo().window(tabs.get(1));
        eMailAddress = getTenMinutesEMailAddressAsString();
        return this;
    }

    public GoogleCloudPricingPage inputMailAddressIntoEstimateForm() {
        driver.switchTo().window(tabs.get(0));
        driver.switchTo().frame(calculatorIFrame)
                .switchTo().frame(myFrame);
        eMailEstimateForm.click();
        eMailInputField.sendKeys(eMailAddress);
        sendEMail.click();
        return this;
    }

    public void getMailOnTenMinutesMailBox() {
        driver.switchTo().window(tabs.get(1));
        tempMailPage.inboxCount = new WebDriverWait(driver, 600)
                .until(ExpectedConditions.visibilityOf(tempMailPage.inboxCount));
        tempMailPage.inboxCount.click();
    }

    public String getTextFromMail() {
        waitForElementVisibility(tempMailPage.estimateCosInMail);
        return tempMailPage.estimateCosInMail.getText();
    }


    public String getTextFromVMClassField() {
        return vMClassFieldInComputeEngineForm.getText();
    }

    public String getTextFromInstanceTypeField() {
        return instanceTypeFieldInComputeEngineForm.getText();
    }

    public String getTextFromRegionField() {
        return regionFieldInComputeEngineForm.getText();
    }

    public String getTextFromAvailableSSDField() {
        return availableSSDFieldInComputeEngineForm.getText();
    }

    public String getTextFromCommitmentField() {
        return commitmentFieldInComputeEngineForm.getText();
    }

    public String getTotalEstimatedCostString() {
        return totalCostFieldInComputeEngineForm.getText();
    }

    public String getTotalEstimateCost() {
        return getCostFromString(getTotalEstimatedCostString());
    }

    public String getEstimateCostFromMail() {
        return getCostFromString(getTextFromMail());
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    private String getTenMinutesEMailAddressAsString() {
        tempMailPage = new TempMailPage(driver);
        new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(tempMailPage.eMailAddress));
        tempMailPage.eMailAddress.click();
        String resultMailAddress = "";
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable contents = clipboard.getContents(null);
        boolean hasStringText = (contents != null) && contents.isDataFlavorSupported(DataFlavor.stringFlavor);
        if (hasStringText) {
            try {
                resultMailAddress = (String) contents.getTransferData(DataFlavor.stringFlavor);
            } catch (UnsupportedFlavorException | IOException ex) {
                System.out.println(ex);
                ex.printStackTrace();
            }
        }
        return resultMailAddress;
    }

    private String getCostFromString(String string) {
        String[] wordsFromString = string.split(" ");
        for (String word : wordsFromString) {
            if (NumberUtils.isParsable(String.valueOf(word.charAt(0)))) {
                return word;
            }
        }
        return null;
    }

}
