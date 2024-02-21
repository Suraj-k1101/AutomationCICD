package xenCart.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import xenCart.PageObjects.LandingPage;

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

public class StandAloneTest {

	public static void main(String[] args) {
     //New Comments are Added for demo CICD Test Purpose
		String productName = "ZARA COAT 3";

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/client/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		LandingPage landingpage = new LandingPage(driver);
		driver.findElement(By.cssSelector("input#userEmail")).sendKeys("kamblesuraj1101@gmail.com");
		driver.findElement(By.cssSelector("input#userPassword")).sendKeys("Suraj1101");
		driver.findElement(By.cssSelector("input#login")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.mb-3")));

		List<WebElement> products = driver.findElements(By.cssSelector("div.mb-3"));

		// by using java Stream
		WebElement desiredProduct = products.stream()
				.filter(product -> product.findElement(By.cssSelector("h5 b")).getText().equals(productName))
				.findFirst().orElse(null);

		desiredProduct.findElement(By.cssSelector("div.card-body button:last-of-type")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

		List<WebElement> cartProducts = driver.findElements(By.xpath("//div[@class = 'cartSection']/h3"));
		// by using java stream concept

		boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equals(productName));
		// anyMatch():- this method will simply if any name matching with the list of
		// product name then it will return boolean value

		Assert.assertTrue(match);

		driver.findElement(By.cssSelector("li.totalRow button")).click();

		// handling auto suggestive dropdown:-
		// here we are using Actions class

		Actions a = new Actions(driver);

		WebElement countryDropdown = driver.findElement(By.cssSelector("input[placeholder='Select Country']"));
		a.sendKeys(countryDropdown, "India").build().perform();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("section.ta-results"))); // for
																											// visibility
																											// of auto
																											// suggestive
																											// list
		
		

		driver.findElement(By.cssSelector("button.ta-item:nth-of-type(2)")).click(); // for clicking on India option
																						// from AutoSuggestive dropdown

       wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btnn.action__submit.ng-star-inserted")));
       a.moveToElement(driver.findElement(By.cssSelector(".btnn.action__submit.ng-star-inserted"))).click().build().perform();
		
		

		String confirmationMessage = driver.findElement(By.cssSelector("h1.hero-primary")).getText();
		Assert.assertEquals(confirmationMessage, "Thankyou for the order.");

//or		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("Thankyou for the order."));

		driver.close();

	}

}
