package com.qa.cart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.cart.Util.Constants;
import com.qa.cart.Util.S_004_ElementUtil;

public class SerchResultPage {
	private WebDriver driver;
	
	private S_004_ElementUtil ele;
	
	//By locators
	By avelablelist = By.cssSelector("div .product-layout div.product-thumb");
	By textofele = By.cssSelector("div .product-thumb div.caption h4 a");
	
	public SerchResultPage(WebDriver driver){
		this.driver = driver;
		ele =new S_004_ElementUtil(driver);
	}
	
	public void serchresultTitle() {
		ele.waitForTitleIs(5, Constants.serch_page_Title);
	}
	public int avelableList() {
		return ele.getElements(avelablelist).size();
	}
	public productPage pickselctedone(String productName) {
		List<WebElement> alllist=ele.getElements(textofele);
		for(WebElement e :alllist ) {
			if(e.getText().equals(productName)){
				e.click();
				break;
			}
			
		}
		return new productPage(driver);
	}
	
	

}
