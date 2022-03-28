package step;

import org.apache.commons.lang3.math.NumberUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.GoogleCloudHomePage;
import page.GoogleCloudPricingPage;
import page.GoogleCloudResultPage;
import page.TempMailPage;


import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;

import static waitmanager.WaitManager.waitForElementVisibility;

public class Steps {

	private final WebDriver webDriver;
	private TempMailPage tempMailPage;
	private GoogleCloudHomePage googleCloudHomePage;
	private GoogleCloudPricingPage googleCloudPricingPage;
	private ArrayList<String> tabs;
	private String eMailAddress;

	public Steps(WebDriver webDriver) {
		this.webDriver = webDriver;
		webDriver.manage().window().maximize();
		eMailAddress = "";
	}

	public void openGoogleCloudPage() {
		googleCloudHomePage = new GoogleCloudHomePage(webDriver);
		webDriver.get("https://cloud.google.com/");
	}

	public Steps openPricingCalculator() {
		GoogleCloudHomePage.searchButton.sendKeys("Google Cloud Platform Pricing Calculator");
		GoogleCloudHomePage.searchButton.submit();
		GoogleCloudResultPage googleCloudResultPage = new GoogleCloudResultPage(webDriver);
		waitForElementVisibility(googleCloudResultPage.searchResult);
		googleCloudResultPage.searchResult.click();
		return this;
	}

	public Steps switchToCalculatorFrame() {
		googleCloudPricingPage = new GoogleCloudPricingPage(webDriver);
		webDriver.switchTo().frame(googleCloudPricingPage.calculatorIFrame)
				.switchTo().frame(googleCloudPricingPage.myFrame);
		return this;
	}

	public Steps inputNumberOfInstances(String number) {
		googleCloudPricingPage.inputNumberOfInstances.click();
		googleCloudPricingPage.inputNumberOfInstances.sendKeys(number);
		return this;
	}

	public Steps selectSeries() {
		googleCloudPricingPage.seriesSelectField.click();
		waitForElementVisibility(googleCloudPricingPage.seriesSelectedOptions);
		googleCloudPricingPage.seriesSelectedOptions.click();
		return this;
	}

	public Steps selectMachineType() {
		waitForElementVisibility(googleCloudPricingPage.machineTypeSelectedField);
		googleCloudPricingPage.machineTypeSelectedField.click();
		waitForElementVisibility(googleCloudPricingPage.machineTypeSelect);
		googleCloudPricingPage.machineTypeSelect.click();
		return this;
	}

	public Steps addGPU() {
		waitForElementVisibility(googleCloudPricingPage.addGPUCheckbox);
		googleCloudPricingPage.addGPUCheckbox.click();
		waitForElementVisibility(googleCloudPricingPage.gPUTypeDropdown);
		googleCloudPricingPage.gPUTypeDropdown.click();
		waitForElementVisibility(googleCloudPricingPage.chooseGPUType);
		googleCloudPricingPage.chooseGPUType.click();
		googleCloudPricingPage.numberOfGPUDropdown.click();
		waitForElementVisibility(googleCloudPricingPage.chooseNumberOfGPU);
		googleCloudPricingPage.chooseNumberOfGPU.click();

		return this;
	}

	public Steps addSSD() {
		googleCloudPricingPage.localSSDDropdown.click();
		waitForElementVisibility(googleCloudPricingPage.chooseLocalSSDNumber);
		googleCloudPricingPage.chooseLocalSSDNumber.click();
		return this;
	}

	public Steps selectDatacenterLocation() {
		googleCloudPricingPage.datacenterLocationDropdown.click();
		waitForElementVisibility(googleCloudPricingPage.datacenterLocationChoice);
		googleCloudPricingPage.datacenterLocationChoice.click();
		return this;
	}

	public Steps selectCommittedUsage() {
		googleCloudPricingPage.committedUsageDropdown.click();
		waitForElementVisibility(googleCloudPricingPage.committedUsageChoice);
		googleCloudPricingPage.committedUsageChoice.click();
		return this;
	}

	public void pressAddToEstimate() {
		googleCloudPricingPage.addToEstimateButton.click();
	}

	public Steps addTenMinutesEMailTab() {
		((JavascriptExecutor) webDriver).executeScript("window.open('https://temp-mail.io/en','_blank')");
		tabs = new ArrayList<>(webDriver.getWindowHandles());
		return this;
	}

	public Steps getMailAddressFromTenMinutesMailSite() {
		webDriver.switchTo().window(tabs.get(1));
		eMailAddress = getTenMinutesEMailAddressAsString();
		return this;
	}

	public Steps inputMailAddressIntoEstimateForm() {
		webDriver.switchTo().window(tabs.get(0));
		webDriver.switchTo().frame(googleCloudPricingPage.calculatorIFrame)
				.switchTo().frame(googleCloudPricingPage.myFrame);
		googleCloudPricingPage.eMailEstimateForm.click();
		googleCloudPricingPage.eMailInputField.sendKeys(eMailAddress);
		googleCloudPricingPage.sendEMail.click();
		return this;
	}

	public void getMailOnTenMinutesMailBox() {
		webDriver.switchTo().window(tabs.get(1));
		tempMailPage.inboxCount = new WebDriverWait(webDriver, 600)
				.until(ExpectedConditions.visibilityOf(tempMailPage.inboxCount));
		tempMailPage.inboxCount.click();
	}

	public String getTextFromMail() {
		waitForElementVisibility(tempMailPage.estimateCosInMail);
		return tempMailPage.estimateCosInMail.getText();
	}



	public String getTotalEstimatedCostString() {
		return googleCloudPricingPage.totalCostFieldInComputeEngineForm.getText();
	}

	public String getTotalEstimateCost() {
		return getCostFromString(getTotalEstimatedCostString());
	}

	public String getEstimateCostFromMail() {
		return getCostFromString(getTextFromMail());
	}

	public void quitDriver() {
		if (webDriver != null) {
			webDriver.quit();
		}
	}

	private String getTenMinutesEMailAddressAsString() {
		tempMailPage = new TempMailPage(webDriver);
		new WebDriverWait(webDriver, 15).until(ExpectedConditions.elementToBeClickable(tempMailPage.eMailAddress));
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
