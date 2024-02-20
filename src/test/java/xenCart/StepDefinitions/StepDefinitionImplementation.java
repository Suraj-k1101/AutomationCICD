package xenCart.StepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import xenCart.PageObjects.CartPage;
import xenCart.PageObjects.CheckOutPage;
import xenCart.PageObjects.ConfirmationPage;
import xenCart.PageObjects.LandingPage;
import xenCart.PageObjects.ProductCatalogue;
import xenCart.TestComponents.BaseTest;

public class StepDefinitionImplementation extends BaseTest {

	public LandingPage landingpage ;
	public ProductCatalogue productcatalogue;
	public CartPage cartpage;
	public ConfirmationPage confirmationpage;
	
	@Given("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_page() throws IOException
	{
		
		landingpage = launchApplication();  //this launchApplication() is returning landingpage object of LandingPage class in our BaseTest class 
		
	}
	
	//(.+):- this regex will only work if data is driving from under Examples keyword only
	@Given ("^Logged in with username (.+) and password (.+)$")  // (.+) :- this is regular expression we are using for generic purpose no matter what dynamic data comes from feature file it will match it
	public void logged_in_username_and_password(String username, String password)
	{
		
		productcatalogue =landingpage.loginApplication(username,password);
		
	}
	
	
	@When ("I add product (.+) to Cart$")
	public void i_add_product_to_cart(String productName) throws InterruptedException
	{
		
		List<WebElement> products = productcatalogue.getProductList();
		productcatalogue.addProductToCart(productName);
		
	}
	
	
	@When("^Checkout (.+) and submit the order$")
	public void checkout_submit_order(String productName) throws InterruptedException
	{
		cartpage = productcatalogue.goToCartPage();
		boolean match = cartpage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		cartpage.goToCartPage();
		CheckOutPage checkoutpage = cartpage.goToCheckout();
		checkoutpage.selectCountry("India");
		confirmationpage = checkoutpage.submitOrder();
		
	}
	
		
		
	@Then ("{string} message is displayed on ConfirmationPage")  //{string} we put like this because some run time static variable which is string is coming "THANKYOU FOR THE ORDER." thats why we give {string}
    public void message_displayed_confirmationPage(String string)
    {
		
		String confirmationMessage  = confirmationpage.getConfirmationMessage();
		Assert.assertEquals(confirmationMessage, string);
		driver.close();

		
    }
		
	
	
	
	
	
	
	
	
}
