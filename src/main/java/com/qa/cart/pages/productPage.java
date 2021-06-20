package com.qa.cart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.cart.Util.S_004_ElementUtil;

public class productPage {
	private WebDriver driver;
	private S_004_ElementUtil ele;
	private By productHeader = By.cssSelector("div#content h1");
	private By imageslist =By.cssSelector("ul.thumbnails li.image-additional a");
	private By proinfo = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By pricelist = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	private By addingqnty = By.cssSelector("#input-quantity");
	private By addtocart = By.xpath("//button[@id='button-cart']");
	private By successmesg = By.xpath("//div[@class='alert alert-success alert-dismissible']");
	private By navicart = By.xpath("//button[@type='button']/span");
	private By viewCart = By.xpath("//p[@class='text-right']/a");
	public productPage(WebDriver driver) {
		this.driver = driver;
		ele =new S_004_ElementUtil(driver);
	}
	
	public String getproductHeaderTest() {
		return ele.doGetElementText(productHeader);
	}
	public int countofmacpro() {
		return ele.getElements(imageslist).size();
	}
	
	public Map<String, String> getProductInfo() {
		Map<String ,String> add = new HashMap<String , String>();
		add.put("name", getproductHeaderTest());
		List<WebElement> infopro = ele.getElements(proinfo);
		for(WebElement e :infopro ) {
		
			add.put(e.getText().split(":")[0].trim(), e.getText().split(":")[1].trim());
		}
		List<WebElement> priceList =	ele.getElements(pricelist);
		add.put("price", priceList.get(0).getText().trim());
		add.put("exTestprice", priceList.get(1).getText().split(":")[1].trim());
		return add;
	}
	
	public void selectingTheQnty(String qnty) {
		ele.dosendKeys(addingqnty, qnty);
	}
	public void asstoCart() {
		ele.click(addtocart);
	}
	
	
	public CheckOutPage navigatetocart() {
		ele.doMoveToElement(navicart);
		ele.doActionsclick(navicart);
		ele.doMoveToElement(navicart);
		ele.doActionsclick(viewCart);
		return new CheckOutPage(driver);
	}
	
}
