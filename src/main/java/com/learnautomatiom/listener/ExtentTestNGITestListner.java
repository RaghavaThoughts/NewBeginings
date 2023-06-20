package com.learnautomatiom.listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import io.netty.handler.codec.http.HttpContentEncoder.Result;

public class ExtentTestNGITestListner implements ITestListener {
	
	ExtentReports extent=ExtentManager.getInstance();
	ExtentTest extentTest;
	
	
	 public void onTestSuccess(ITestResult result) {
		 extentTest.pass("Test Passed");
		   System.out.println("LOG:PASS - TestPassed!"+result.getMethod().getMethodName());
		  }
	 
	 
	  public void onTestFailure(ITestResult result) {
		  extentTest.fail("Test Failed"+result.getThrowable().getMessage(),MediaEntityBuilder.createScreenCaptureFromBase64String("", "screenshot of failed Test").build());
		    System.out.println("LOG:FAIL - Testfailed"+result.getMethod().getMethodName());
		    System.out.println("Exception Trace"+result.getThrowable().getMessage());
		  }
	  
	  public void onTestStart(ITestResult result) {
		  extentTest=extent.createTest(result.getMethod().getMethodName());
		 System.out.println("LOG: INFO -Test Started!!!"+result.getMethod().getMethodName());
		  }
	  
	  public void onFinish(ITestContext context) {
		  extent.flush();
		  System.out.println("LOG: END -TestEnd!!!"+context.getName());
		   
		  }
	  
	  public void onTestSkipped(ITestResult result) {
		  extentTest.skip("Test skipped");
		  System.out.println("LOG:Skip - Testskipped"+result.getMethod().getMethodName());
		    System.out.println("Exception Trace"+result.getThrowable().getMessage());
		}


}
