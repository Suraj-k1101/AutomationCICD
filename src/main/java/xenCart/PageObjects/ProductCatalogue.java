package xenCart.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import xenCart.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {

	WebDriver driver;   //this is current class instance variable
	
	public ProductCatalogue(WebDriver driver)   //constructor
	{
		
		super(driver);  //here we are sending driver object from this child class LandingPage to its parent class AbstractComponent with help of super(driver)
		//initialization
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
		
		
	}
	
//page object WebElements:-	
	
	//List<WebElement> products = driver.findElements(By.cssSelector("div.mb-3"));
	
	@FindBy(css = "div.mb-3")
	List<WebElement> products;
	@FindBy(css = ".ng-animating")
	WebElement spinner;
	
	
	By productBy = By.cssSelector("div.mb-3");
	By addToCart = By.cssSelector("div.card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");
	

//Action methods:-
	public List<WebElement> getProductList()
	{
		waitForElementToAppear(productBy);
		return products;
		
		
	}
	
	
	public WebElement getProductByName(String productName)
	{
		
		// by using java Stream
				WebElement desiredProduct = getProductList().stream()
						.filter(product -> product.findElement(By.cssSelector("h5 b")).getText().equals(productName))
						.findFirst().orElse(null);
				
				return desiredProduct;
		
	}
	
	
	public void addProductToCart(String productName) throws InterruptedException
	{
		

		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(spinner);
		
		
	}

	
	
	
	
	
	
	
	
}
