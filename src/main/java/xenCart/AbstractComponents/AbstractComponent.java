package xenCart.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import xenCart.PageObjects.CartPage;

public class AbstractComponent {
//here our all reusable generic methods we have declared in this class
	
	WebDriver driver;   //instance variable of class
	
	public AbstractComponent(WebDriver driver) {  //constructor
		// TODO Auto-generated constructor stub
		//so here to catch the variables from child classes with the help of super() so we have to create constructor here in parent class
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
		
	}
	
	@FindBy(css = "[routerlink*='cart']")
	WebElement cartHeader;

	public void waitForElementToAppear(By errorMessage)
	{
		

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated((By) errorMessage));

		
	}
	
	
	
	
	public CartPage goToCartPage()
	{
		
		cartHeader.click();
		CartPage cartpage = new CartPage(driver);
		return cartpage;
		
	}
	
	public void waitForElementToDisappear(WebElement ele) throws InterruptedException
	{
		//Thread.sleep(1000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(ele));
		
	}
	
}
