package xenCart.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{

	int count = 0;
	int maxTry = 1;
	
	@Override
	public boolean retry(ITestResult result) {
		
		if(count<maxTry)
		{
			count++;
			return true;
			//as long as it returning true it will keep retrying/re running the flaky test
			
		}
		
		return false; //when it return false it will stop retrying /re running
	}

	
	
	
}
