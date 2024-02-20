package xenCart.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import xenCart.AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent {

WebDriver driver;   //this is current class instance variable
	
	public CheckOutPage(WebDriver driver)   //constructor
	{
		super(driver);  //here we are sending driver object from this child class LandingPage to its parent class AbstractComponent with help of super(driver)
		//initialization
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
		
		
	}
	
	
	@FindBy(css = "input[placeholder='Select Country']") 
	WebElement country;
	
	@FindBy(xpath = "//a[normalize-space()='Place Order']")
	WebElement submit;
	
	@FindBy(css = "button.ta-item:nth-of-type(2)")
	WebElement selectCountry;
	
	By results = By.cssSelector("section.ta-results");
	
//Action Methods:-
	public void selectCountry(String countryName)
	{
		
		Actions a = new Actions(driver);

		a.sendKeys(country, countryName).build().perform();

		waitForElementToAppear(results); // for visibility of auto suggestive list
																										
		selectCountry.click(); // for clicking on India option
																					
		
	}
	
	public ConfirmationPage submitOrder() throws InterruptedException
	{

		JavascriptExecutor je = (JavascriptExecutor)driver;
		je.executeScript("window.scrollBy(1085, 653)");
		Thread.sleep(2000);
		
		submit.click();
		return new ConfirmationPage(driver);
		
	}
	
	
	
	
	
	
		
	
}
