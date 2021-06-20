package com.qa.cart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	
	
	/**
	 * this class is already avelable in java which is used read properties
	    properties file
	 */
	Properties prop;
	/**
	 * this variable is used overHear for call to another class easyly
	 */
	public static String highlight;
	OptionsManager opnmang;
	public static ThreadLocal<WebDriver> tDriver = new ThreadLocal<>();
	/**
	 * this method is init driver with the given value
	 * @param browser
	 * @return webDriver
	 */
	
	public WebDriver inti_driver(Properties prop) {
		String browser = prop.getProperty("browser").trim();
		highlight =prop.getProperty("highlight");
		opnmang = new OptionsManager(prop);
		System.out.println("Browser name is "+browser); 
		if(browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			//driver = new ChromeDriver(opnmang.getChromeOptions());
			tDriver.set(new ChromeDriver(opnmang.getChromeOptions()));
		}else if(browser.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
		     //driver = new FirefoxDriver(opnmang.getfirefoxOptions());
			tDriver.set(new FirefoxDriver(opnmang.getfirefoxOptions()));
		}else {
			System.out.println("given browser is roung"+browser);
		}
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().get(prop.getProperty("url").trim());
		return getDriver();
	}
	
	public static synchronized WebDriver getDriver() {
		return tDriver.get();
	}
/**
 * this file returns properties from config.properties
 * @return properties
 */
	public Properties init_prop() {
		prop = new Properties();
		FileInputStream ip =null;
	String env =System.getProperty("env");
	if(env==null) {
		try {
			ip = new FileInputStream("./src/test/resources/config/config.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	else {
		try {
		switch (env) {
		case "qa":
			ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
			break;
      case "dev":
    	  ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
			break;
        case "stage":
        	ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
	   break;

		default:
			System.out.println("please pass the right env");
			break;
		}
		
	}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	
	}
	try {
		prop.load(ip);
	} catch (IOException e) {
		
		e.printStackTrace();
	}
	return prop;
	}
	/**
	 * take screenShort Method
	 */
	public String getScreenshot() {
		File srcFile =((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		//File srcFile = new File(src);
				String path =  System.getProperty("user.dir")+"/screenshots/"+System.currentTimeMillis()+".png";
				File destination = new File(path);
				try {
					FileUtils.copyFile(srcFile, destination);
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				return path;
				
		
	}

	}
