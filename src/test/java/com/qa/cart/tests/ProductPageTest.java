package com.qa.cart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.cart.base.BaseTest;

public class ProductPageTest extends BaseTest {
	SoftAssert sfAssert = new SoftAssert();
	@BeforeClass
	public void landonAccPage() {
		accpage =logpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	@Test
	public void addtheproducttoCart() {
		serchpage =accpage.doSerch("MacBook");
		propage	= serchpage.pickselctedone("MacBook Pro");
		Map<String, String> proinfo =propage.getProductInfo();
		sfAssert.assertEquals(proinfo.get("name"), "MacBook Pro");
		sfAssert.assertEquals(proinfo.get("Availability"), "Out Of Stock");
		sfAssert.assertEquals(proinfo.get("price"), "$2,000.00");
		sfAssert.assertEquals(proinfo.get("Reward Points"), "800");
		sfAssert.assertAll();
	}
	@Test
	public void addtocart() {
		
		propage.selectingTheQnty("1");
		propage.asstoCart();
		
	}
//	@Test
//	public void checkthecourt() {
//		propage.navigatetocart();
//		
//	}

}
