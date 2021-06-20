package com.qa.cart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.cart.Util.Constants;
import com.qa.cart.Util.S_004_ElementUtil;

public class RegistrationPage {
	private WebDriver driver;
	private S_004_ElementUtil ele;
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By phon = By.id("input-telephone");
	private By pass = By.id("input-password");
	private By confirmpass = By.id("input-confirm");
	private By radioyes = By.xpath("(//label[@class='radio-inline'])[1]/input");
	private By radioNo = By.xpath("(//label[@class='radio-inline'])[2]/input");
	private By checkBox = By.xpath("//input[@type='checkbox']");
	private By submit = By.xpath("//input[@value= 'Continue']");	
	private By sucssmesg = By.cssSelector("div#content h1");
	private By logout =By.linkText("Logout");
	private By registor = By.linkText("Register");
	
	 
	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		ele =new S_004_ElementUtil(driver);
	}
	
	public Boolean regilist(String firstName,String lastName ,String email,String phon,
			String pass,String radiobutton ) {
		ele.dosendKeys(this.firstName, firstName);
		ele.dosendKeys(this.lastName, lastName);
		ele.dosendKeys(this.email, email);
		ele.dosendKeys(this.phon, phon);
		ele.dosendKeys(this.pass, pass);
		ele.dosendKeys(this.confirmpass, pass);
		if(radiobutton.equals("yes")) {
		ele.click(radioyes);
		}else {
		ele.click(radioNo);
		}
		ele.click(checkBox);
		ele.click(submit);
		String meg = ele.doGetElementText(sucssmesg);
		System.out.println("meg");
		if(meg.contains(Constants.reg_page_succmesg)) {
			ele.click(logout);
			ele.click(registor);
			return true;
		}else
			return false;
	}

}
