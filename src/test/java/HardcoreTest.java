import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.GoogleCloudHomePage;
import page.GoogleCloudPricingPage;
import webdriver.WebDriverSetup;

public class HardcoreTest {
    private WebDriver driver;
    private GoogleCloudPricingPage googleCloudPricingPage;


    @BeforeTest(description = "Open browser")
    public void setupBrowser() {
        driver = WebDriverSetup.getDriver();
        googleCloudPricingPage = new GoogleCloudPricingPage(driver);
    }

    @Test(description = "Check that all prices are correct")
    public void testHurtMePlenty() {
        new GoogleCloudHomePage(driver)
                .openPage()
                .openPricingCalculator();

        googleCloudPricingPage
                .switchToCalculatorFrame()
                .inputNumberOfInstances("4")
                .selectSeries()
                .selectMachineType("CP-COMPUTEENGINE-VMIMAGE-N1-STANDARD-8")
                .addGPU()
                .addSSD()
                .selectDatacenterLocation()
                .selectCommittedUsage()
                .selectCommittedUsage()
                .pressAddToEstimate();

        String totalCostEstimateCost = googleCloudPricingPage.getTotalEstimateCost();
        googleCloudPricingPage.addTenMinutesEMailTab()
                .getMailAddressFromTenMinutesMailSite()
                .inputMailAddressIntoEstimateForm()
                .getMailOnTenMinutesMailBox();
        String estimateCostFromMail = googleCloudPricingPage.getEstimateCostFromMail();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(
                totalCostEstimateCost,
                estimateCostFromMail,
                "Total cost estimate is not equal to estimate cost from mail");

        softAssert.assertAll();
    }

    @AfterTest(description = "Close browser")
    void browserTeardown() {
        driver.quit();
    }
}
