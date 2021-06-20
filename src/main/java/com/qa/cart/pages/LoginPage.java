package com.qa.cart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.cart.Util.Constants;
import com.qa.cart.Util.S_004_ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	
   private WebDriver driver;
	
	private S_004_ElementUtil ele;
	
	//page obj = By locator
	private By username = By.id("input-email");
	private By password = By.id("input-password");
	private By login = By.xpath("//input[@value='Login']");
	private By forgot = By.xpath("//div[@class='form-group']/a[text()='Forgotten Password']");
	private By registor = By.linkText("Register");
	//crating page constructer
	public LoginPage(WebDriver driver) {
	    this.driver = driver;
		ele =new S_004_ElementUtil(driver);
	}
	@Step("getting login page title.....")
	public String getloginTiltle() {
		return ele.waitForTitleIs(5,Constants.login_Title );
	}
	@Step("cheking the forgotpassword link.....")
	public boolean isforgotlinkisExsist() {
		return ele.doIsDesplayed(forgot);
	}
	@Step("login with username: {0} and password: {1}")
	public AccountsPage doLogin(String un,String pw) {
		System.out.println("username is: "+un +"password is: "+pw);
		ele.dosendKeys(username, "trinadh.penmetsa85@gmail.com");
		ele.dosendKeys(password, "vallivalli@");
		ele.click(login);
		return new AccountsPage(driver);
		
	}
	@Step("Navigating to registerpage")
	public RegistrationPage registorPageclick() {
		ele.click(registor);
		return new RegistrationPage(driver);
		
		
	}
}
