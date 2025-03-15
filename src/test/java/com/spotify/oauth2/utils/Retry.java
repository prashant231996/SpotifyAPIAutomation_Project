package com.spotify.oauth2.utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{
	
	public int retryCount=1;
	public int count=0;

	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		if(result.getStatus()==ITestResult.FAILURE)
		{
			if(count<retryCount)
			{
				result.setStatus(ITestResult.FAILURE);
				count++;
				return true;
			}
			else
			{
				result.setStatus(ITestResult.FAILURE);
				count=0;
				return false;
			}
		}
		return false;
	}

}
