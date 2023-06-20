package com.learnautomatiom.listener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
	
	public static ExtentReports extent;	
	
	public static ExtentReports getInstance() {
		
		if(extent==null) {
			extent=createInstance();
			return extent;
		}else {
			return extent;
		}		
	}
	
	
	public static ExtentReports createInstance() {
		ExtentSparkReporter sparkReporter=new ExtentSparkReporter(System.getProperty("user.dir")+"/Reports/AutomationReport.html");
		sparkReporter.config().setDocumentTitle("Automation Report");
		sparkReporter.config().setReportName("Sprint 1 Report");
		
		ExtentReports extent=new ExtentReports();
		
		extent.attachReporter(sparkReporter);
		
		return extent;
		
	}

}
