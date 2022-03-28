package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TempMailPage {


	@FindBy(id = "email")
	public WebElement eMailAddress;

	@FindBy(xpath = "//span[@title='Google Cloud Price Estimate']")
	public WebElement inboxCount;

	@FindBy(xpath = "//*[@id=\"mobilepadding\"]/td/h2")
	public WebElement estimateCosInMail;

	public TempMailPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
}
