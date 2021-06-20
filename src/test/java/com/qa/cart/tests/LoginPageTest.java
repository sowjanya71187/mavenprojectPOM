package com.qa.cart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.cart.Util.Constants;
import com.qa.cart.base.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
@Story("US-101: design the login page for demo opencart app with login, title and forgot pwd link")
@Epic("Epic-100: design login page feature")
public class LoginPageTest extends BaseTest{
	
	
	@Description("Login page title test...")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=1)
	public void loginpagetitlevari() {
		String title = logpage.getloginTiltle();
		Assert.assertEquals(title, Constants.login_Title);
	}
	@Description("forgotpass link test...")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=2)
	public void forgotlinkvari() {
		boolean isexist=logpage.isforgotlinkisExsist();
		Assert.assertTrue(isexist, "forgot link is exist");
	}
	@Description("login test with correct credentials...")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority=3)
	public void logintopage() {
		accpage=logpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(accpage.getAccPageTitle() , Constants.Acc_login_Title);
		
	}

}
