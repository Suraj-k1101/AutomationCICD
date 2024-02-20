package xenCart.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	
	public static ExtentReports getReportObject()
	{
		//ExtentReports and ExtentSparkReporter :- this two classes helps us to generate extent reports
				//ExtentReports is the main class and ExtentSparkReporter is a supportive class which helps us to set configuration
				//and we have to create the objects of ExtentReports and ExtentSparkReporter
				
				String path = System.getProperty("user.dir")+"\\reports\\index.html";
				
				ExtentSparkReporter reporter = new ExtentSparkReporter(path);
				reporter.config().setReportName("Web Automation Results");
				reporter.config().setDocumentTitle("Test Results");
				
				
				ExtentReports extent = new ExtentReports();
				extent.attachReporter(reporter); //we have put ExtentSparkReporter object here 
				extent.setSystemInfo("Tester", "Suraj Kamble");
				return extent;
		
		
		
	}
	
	
}
