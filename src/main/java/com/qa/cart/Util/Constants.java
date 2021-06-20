package com.qa.cart.Util;

import java.util.ArrayList;
import java.util.List;

public class Constants {
	
	public static final String login_Title = "Account Login";
	public static final String Acc_login_Title = "My Account";
	public static final int  Acc_login_Header_size = 5;
	public static final String serch_page_Title ="Search - macbook";
	public static final int  Pro_page_img_count= 3;
	public static final String reg_page_succmesg = "Your Account Has Been Created!";
	//*************************excelSheet data******************
	public static final String excel_sheet_name = "register";
	
	public static final List<String> expectedheader(){
		
		List<String> addvalues = new ArrayList<String>();
		addvalues.add("My Account");
		addvalues.add("My Orders");
		addvalues.add("My Affiliate Account");
		addvalues.add("Newsletter");
		return addvalues;
		
	}
	

}
