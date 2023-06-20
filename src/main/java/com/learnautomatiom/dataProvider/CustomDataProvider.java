package com.learnautomatiom.dataProvider;

import org.testng.annotations.DataProvider;

public class CustomDataProvider {
	
	@DataProvider(name="users")	
	public static Object[][] getLoginData(){		
		Object[][] arr=ExcelReader.getDataFromSheet("Users");
		return arr;
	}

}
