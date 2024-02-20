package xenCart.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import xenCart.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {

WebDriver driver;   //this is current class instance variable
	
	public CartPage(WebDriver driver)   //constructor
	{
		super(driver);  //here we are sending driver object from this child class LandingPage to its parent class AbstractComponent with help of super(driver)
		//initialization
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
		
		
	}
	
	@FindBy(css = "li.totalRow button") 
	WebElement checkOutEle;
	
	@FindBy(xpath = "//div[@class = 'cartSection']/h3")
	List<WebElement> cartProducts;
	
//Action methods:-
	
	public boolean verifyProductDisplay(String productName)
	{
		
		boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equals(productName));
		// anyMatch():- this method will simply if any name matching with the list of
		// product name then it will return boolean value
		
		return match;

	}
	
	
	public CheckOutPage goToCheckout()
	{
		
		checkOutEle.click();
		
		return new CheckOutPage(driver);
		
		
	}
	
	
	
	
	
	
	
}
