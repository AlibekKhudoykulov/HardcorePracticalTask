package test;



import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import step.Steps;


import static webdriver.WebDriverSetup.getDriver;

public class HardcoreTest extends CommonConditions{
    private Steps steps;

    @BeforeTest
    public void setupBrowser() {
        steps = new Steps(getDriver());
        steps.openGoogleCloudPage();
    }

    @Test
    public void testHurtMePlenty() {
        steps.openPricingCalculator()
                .switchToCalculatorFrame()
                .inputNumberOfInstances("4")
                .selectSeries()
                .selectMachineType()
                .addGPU()
                .addSSD()
                .selectDatacenterLocation()
                .selectCommittedUsage()
                .selectCommittedUsage()
                .pressAddToEstimate();
        String totalCostEstimateCost = steps.getTotalEstimateCost();
        steps.addTenMinutesEMailTab()
                .getMailAddressFromTenMinutesMailSite()
                .inputMailAddressIntoEstimateForm()
                .getMailOnTenMinutesMailBox();
        String estimateCostFromMail = steps.getEstimateCostFromMail();

        Assert.assertEquals(totalCostEstimateCost, estimateCostFromMail);
    }

}
