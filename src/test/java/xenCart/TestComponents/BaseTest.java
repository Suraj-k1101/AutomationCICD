package xenCart.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import xenCart.PageObjects.LandingPage;

public class BaseTest {

	public WebDriver driver;   //global variable
	public LandingPage landingpage;
	
	@Test
	public WebDriver initializeDriver() throws IOException
	{
		//By using Properties class :-if you have .properties file then with the help of Properties class you can access all the data from that file
		  //create object of Properties class
		Properties prop = new Properties();
		
		//create File class object:-
		File file = new File(System.getProperty("user.dir")+"//src//main//java//xenCart//Resources//GlobalData.properties");
		
		//create object FileInputStream class:-
		FileInputStream fis = new FileInputStream(file);
		prop.load(fis);
		
		//for running (change the value of browser in run time)browser values through Maven in the command prompt(we use here ternary operator or we can use if-else also):- 
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
		
		
		//prop.getProperty("browser");
		
		if(browserName.contains("chrome"))
		{
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			
			if(browserName.contains("headless"))
			{	
			  options.addArguments("headless");
			}
			
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900)); //this will be help us to run in a full screen
			//this above step is optional when you see any flakiness like element is not visible the we can add this above step
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			
			
		}
		else if(browserName.equalsIgnoreCase("edge"))
		{
			
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			
			
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
		
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException 
	{
		
		TakesScreenshot ts = (TakesScreenshot)driver;
		File sourse = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"//reports//" +testCaseName+ ".png");
		FileUtils.copyFile(sourse, file);
		return System.getProperty("user.dir")+"//reports//" +testCaseName+ ".png";
		
	}
	
	@BeforeMethod
	public LandingPage launchApplication() throws IOException
	{
		//here he initialize driver first by calling initializeDriver() method in this method so here we will get driver
		driver = initializeDriver();
		
		landingpage = new LandingPage(driver);
		landingpage.goTo();
		
		return landingpage;
		
		
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.close();
		
	}
	
	
	
}
	
	
