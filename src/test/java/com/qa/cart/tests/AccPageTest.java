package com.qa.cart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.cart.Util.Constants;
import com.qa.cart.Util.Errors;
import com.qa.cart.base.BaseTest;

public class AccPageTest extends BaseTest {
	SoftAssert sAssert =new  SoftAssert();
	@BeforeClass
	public void landonAccPage() {
		accpage =logpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	@Test(priority = 1)
	public void accPagetitletest() {
		String title=accpage.getAccPageTitle();
		Assert.assertEquals(title, Constants.Acc_login_Title , Errors.Acc_login_Title);
	}
	@Test(priority = 2)
	public void logotest() {
		Assert.assertTrue(accpage.logotest(), Errors.Acc_page_logo);
	}
	@Test(priority = 3)
	public void HeadersCountiscurrect() {
		Assert.assertEquals(accpage.headersCount(), Constants.Acc_login_Header_size);
	}
	@Test(priority = 4)
	public void Headersvalidation() {
		List<String> allheaders =accpage.headerslist();
		System.out.println(allheaders);
		Assert.assertEquals(allheaders, Constants.expectedheader());
	}
	@Test(priority = 5)
	public void serchTest() {
		serchpage =accpage.doSerch("macbook");
		Assert.assertTrue(serchpage.avelableList()>0, Errors.serch_page_productcount);
	}
	@Test(priority = 6)
	public void selectoneItemfromList() {
		serchpage =accpage.doSerch("macbook");
		propage = serchpage.pickselctedone("MacBook Pro");
		String actHeader =propage.getproductHeaderTest();
		sAssert.assertEquals(actHeader, "MacBook Pro" ,Errors.product_page_header );
		sAssert.assertEquals(propage.countofmacpro(), Constants.Pro_page_img_count);
		sAssert.assertAll();
	}

}
