package com.qa.cart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.cart.Util.Constants;
import com.qa.cart.Util.Errors;
import com.qa.cart.Util.ExcelUtil;
import com.qa.cart.base.BaseTest;

public class RegistrationPageTest extends BaseTest {
	
	@BeforeClass
	public void regpagenavigation() {
		regpage =logpage.registorPageclick();
	}
	@DataProvider
	public Object[][] getRegData() {
		Object[][] data=ExcelUtil.getTestData(Constants.excel_sheet_name);
		return data;
	}
	
	public String  getRandomNumber() {
		Random randomGenrator = new Random();
		String email = "rsaomwui"+randomGenrator.nextInt(1000)+"@gmail.com";
		return email;
	}
	
	
	@Test(dataProvider = "getRegData")
	public void insertValesonRegPage(String firstname ,String lastname,
			String telephon, String password, String subscribe) {
		Assert.assertTrue(regpage.regilist(firstname, lastname, getRandomNumber(), 
				telephon, password, subscribe), Errors.regi_is_fail);
	}
	
	
	
	
	

}
