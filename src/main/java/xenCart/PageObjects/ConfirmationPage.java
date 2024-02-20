package xenCart.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import xenCart.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent {


WebDriver driver;   //this is current class instance variable
	
	public ConfirmationPage(WebDriver driver)   //constructor
	{
		super(driver);  //here we are sending driver object from this child class LandingPage to its parent class AbstractComponent with help of super(driver)
		//initialization
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
		
		
	}
	
	
	@FindBy(css= "h1.hero-primary")
	WebElement confirmationMessage;
	
	
//Action methods:-
	public String getConfirmationMessage()
	{
		
		return confirmationMessage.getText();
		
	}
			
			
	
	
}
