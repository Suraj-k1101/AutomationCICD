package xenCart.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import xenCart.PageObjects.CartPage;
import xenCart.PageObjects.CheckOutPage;
import xenCart.PageObjects.ConfirmationPage;
import xenCart.PageObjects.LandingPage;
import xenCart.PageObjects.ProductCatalogue;
import xenCart.TestComponents.BaseTest;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SubmitOrderTest extends BaseTest {

	
	@Test
    public void submitOrder() throws IOException, InterruptedException
    {
		String productName = "ZARA COAT 3";

		ProductCatalogue productcatalogue =landingpage.loginApplication("kamblesuraj1101@gmail.com", "Suraj1101");
		List<WebElement> products = productcatalogue.getProductList();
		productcatalogue.addProductToCart(productName);
		CartPage cartpage = productcatalogue.goToCartPage();
		
		boolean match = cartpage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		cartpage.goToCartPage();
		CheckOutPage checkoutpage = cartpage.goToCheckout();
		checkoutpage.selectCountry("India");
		ConfirmationPage confirmationpage = checkoutpage.submitOrder();
		String confirmationMessage  = confirmationpage.getConfirmationMessage();

		Assert.assertEquals(confirmationMessage, "THANKYOU FOR THE ORDER.");

//or		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("Thankyou for the order."));

		

	}

}
