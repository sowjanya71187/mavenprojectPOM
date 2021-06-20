package com.qa.cart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.cart.factory.DriverFactory;
import com.qa.cart.pages.AccountsPage;
import com.qa.cart.pages.LoginPage;
import com.qa.cart.pages.RegistrationPage;
import com.qa.cart.pages.SerchResultPage;
import com.qa.cart.pages.productPage;

public class BaseTest {
	private WebDriver driver;
	//theew r in public becouse we have to call by logintest
	public Properties prop;
	 DriverFactory df;
	public LoginPage logpage;
	public AccountsPage accpage;
	public SerchResultPage serchpage;
	public productPage propage;
	public RegistrationPage regpage;
	
	 	@BeforeTest
	 	public void setup() {
	 		df = new DriverFactory();
	 		prop =df.init_prop();
	 		driver =df.inti_driver(prop);
	 		logpage = new LoginPage(driver);
	 		
	 		
	 	}
	 	@AfterTest
	 	public void tearDown() {
	 		driver.quit();
	 	}

}
