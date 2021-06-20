package com.qa.cart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.cart.Util.Constants;
import com.qa.cart.Util.S_004_ElementUtil;

public class AccountsPage {
	private WebDriver driver;
	private S_004_ElementUtil ele;
	
	//by locator
	By logo = By.cssSelector("#logo");
	By headers = By.cssSelector("div #content h2");
	By serchfiled = By.xpath("//div[@id='search']/input[@type='text']");
	By serchButten = By.xpath("//span[@class='input-group-btn']/button/i[@class='fa fa-search']");
	
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		ele =new S_004_ElementUtil(driver);
		
	}
	public String getAccPageTitle() {
		return ele.waitForTitleIs(5, Constants.Acc_login_Title);
	}
	
	public boolean logotest() {
		
		return ele.doIsDesplayed(logo);
	}
	public int headersCount() {
		return ele.getElements(headers).size();
	}
	public List<String> headerslist() {
		List<WebElement> headersList =ele.getElements(headers);
		List<String> addHead = new ArrayList<String>();
		for(WebElement e :headersList ) {
			addHead.add(e.getText());
		}
		return addHead;
	}
	
	public SerchResultPage doSerch(String productname) {
		ele.dosendKeys(serchfiled, productname);
		ele.click(serchButten);
		return new SerchResultPage(driver);
	}
	
	
	
	
	
	
	
	
	
	
	

}
