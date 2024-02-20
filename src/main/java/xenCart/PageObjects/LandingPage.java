package xenCart.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import xenCart.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

	WebDriver driver;   //this is current class instance variable
	
	public LandingPage(WebDriver driver)   //constructor
	{
		super(driver);  //here we are sending driver object from this child class LandingPage to its parent class AbstractComponent with help of super(driver)
		//initialization
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
		
		
	}
	
//page object WebElements:-	
	@FindBy(css = "input#userEmail")
	WebElement userEmail;
	

	@FindBy(css = "input#userPassword")
	WebElement userPassword;
	

	@FindBy(css = "input#login")
	WebElement loginButton;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;

//Actions Methods:-
	
	public ProductCatalogue loginApplication(String email, String password)
	{
		
		userEmail.sendKeys(email);
		
		userPassword.sendKeys(password);
		
		loginButton.click();
		
		ProductCatalogue productcatalogue = new ProductCatalogue(driver);
		return productcatalogue;
		
	}
	
	public void goTo()
	{
		
		driver.get("https://rahulshettyacademy.com/client/");
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
