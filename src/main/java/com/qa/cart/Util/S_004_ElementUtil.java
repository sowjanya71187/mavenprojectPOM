package com.qa.cart.Util;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.cart.factory.DriverFactory;

public class S_004_ElementUtil {
	private WebDriver driver;
	private Actions act;
	private JavaScripsUtil jsUtil;
	 
	    public S_004_ElementUtil(WebDriver driver) {
	    	this.driver = driver;
	    	act = new Actions(this.driver);
	    	jsUtil = new JavaScripsUtil(this.driver);
	    }
		public WebElement getElment(By locator) {
			WebElement element =driver.findElement(locator);
			if(Boolean.parseBoolean(DriverFactory.highlight)){
				jsUtil.flash(element);
			}
	         return element;
		}
		public  List<WebElement> getElements(By locator) {
			return driver.findElements(locator);
		}
		
		public void dosendKeys(By locator,String keyvalues) {
			getElment(locator).clear();
			getElment(locator).sendKeys(keyvalues);
		}
		
		public void click(By locator) {
			getElment(locator).click();
		}
		
		public String doGetElementText(By locator) {
			return getElment(locator).getText();
			
		}
		public boolean doIsDesplayed(By locator) {
			return getElment(locator).isDisplayed();
			
		}
		
		
		public void doLinkClick(By locator,String textvaleu) {
	      List<WebElement> linkslist = getElements(locator);
			
			System.out.println(linkslist.size());
			
			for(int i = 0; i<linkslist.size();i++) {
				String text=linkslist.get(i).getText();
				
				System.out.println(text);
				if(text.equals(textvaleu))
					
			    {
					linkslist.get(i).click();
					break;
				}
			}
			
		}
		//*********************DroupDownUtilites******************
		public void doSelectByValue(By locator,String value) {
			Select select = new Select(getElment(locator));
			select.selectByValue(value);
		}
		public  void doSelectByIndex(By locator,int index) {
			Select select = new Select(getElment(locator));
			select.selectByIndex(index);
		}	
		public void doSelectByVisableText(By locator,String text) {
			Select select = new Select(getElment(locator));
			select.selectByVisibleText(text);
		}
		public  List<String> getDropDownOptionsList(By locator) {
			Select select = new Select(getElment(locator));
			List<String> optionsTextList = new ArrayList<String> ();
			List<WebElement>optionsList=select.getOptions();
			System.out.println(optionsList.size());
			for(WebElement e : optionsList) {
				optionsTextList.add(e.getText());
			}
			return optionsTextList;
		}
		public  void doSelectByTextOption(By locator , String text) {
			Select select = new Select(getElment(locator));
			List<WebElement> optionslist = select.getOptions();
			
			for(WebElement e:optionslist ) {
				if(e.getText().equals(text)) {
					e.click();
					break;
				}
			}
		}
		/**
		 * this method is used with out using select class
		 * @param locator
		 * @param text
		 */
		public  void doSelectDroupDownWithOutSelectClass(By locator , String text) {
			List<WebElement> optionslist = getElements(locator);
			
			for(WebElement e :optionslist ) {
				if(optionslist.equals(text)) {
					e.click();
					break;
				}
			}
		}
		//************************Actions Class**********************
		/**
		 * 
		 */
		public void  doMoveToElement(By locator) {
			
			act.moveToElement(getElment(locator)).perform();
			
		}
		public void  doMoveToElement(By locator1 ,By locat2 ) throws InterruptedException {
			
			act.moveToElement(getElment(locator1)).perform();
			act.moveToElement(getElment(locat2)).perform();
			
	   }
		
		public void  doMoveToElement(By locator1 ,By locator2 ,By locator3 ) throws InterruptedException {
			
			act.moveToElement(getElment(locator1)).perform();
			act.moveToElement(getElment(locator2)).perform();
			getElment(locator3).click();
			
	   }
		
		
		public  void doActionsclick(By locator) {
			
			act.click(getElment(locator)).perform();
		}
		
	    public void doActionsSendKeys(By locator , String values) {
	    	
	    	act.sendKeys(getElment(locator), values).perform();
	    	
	    }	
	   // **********************calenderUtil***************************
	public  void seletDate(String tag ,String day) {
			String xpath = "//"+tag+"[text()='"+day+"']";
			click(By.xpath(xpath));
		}
		public void dateSelect(By locator , String date) {
			boolean flag = false;
			List<WebElement> daysList=getElements(locator);
			for(WebElement e :daysList) {
				if(e.getText().equals(date)) {
					e.click();
					flag=true;
					break;
					
				}
			}
			if(!flag) {
				System.out.println("wrong date enetered:  "+date);
			}
		}
		 // **********************WaitUtils***************************  
		//hear it is asking by locator
		public  WebElement waitForPresenceOfElement(By locator ,int time ) {
			WebDriverWait wait = new WebDriverWait(driver, time);
			return	wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		 
			}
		/**
		 * An expectation for checking that an element, known to be present on the DOM
		 * of a page, is visible. Visibility means that the element is not only
		 * displayed but also has a height and width that is greater than 0.
		 * @return 
		 */
		// hear it is asking web element
		
		public  WebElement WaitforVisableOfElement(By locator ,int timeout) {
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			
			return wait.until(ExpectedConditions.visibilityOf(getElment(locator)));
		}
		public  Alert waitForAlertPresent(int time) {
			WebDriverWait wait = new WebDriverWait(driver ,time);
			return wait.until(ExpectedConditions.alertIsPresent());
			
			
		}
		public  String getAlertText(int time) {
			String text =waitForAlertPresent(time).getText();
			acceptTheAlert(time);
			return text;
		}
		public void acceptTheAlert(int time) {
			waitForAlertPresent(time).accept();
		}
		public  void dismissTheAlert(int time) {
			waitForAlertPresent(time).dismiss();
		}
		public  String waitForTitleContaines(int timeout,String excpctedtitle) {
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			wait.until(ExpectedConditions.titleContains(excpctedtitle));
			return driver.getTitle();
		}
		public  String waitForTitleIs(int timeout,String excpctedtitle) {
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			wait.until(ExpectedConditions.titleIs(excpctedtitle));
			return driver.getTitle();
		}
		public  Boolean waitForTitle(int timeout,String excpctedtitle) {
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			return wait.until(ExpectedConditions.titleIs(excpctedtitle));
			
		}
		public  List<WebElement> WaitForVisabulityOfElements(By locator ,int timeout) {
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		}
		public void printElementsText(By locator ,int timeout) {
			 WaitForVisabulityOfElements(locator, timeout)
			   .stream()
			          .forEach(ele-> System.out.println(ele.getText()));
		}
		/**
		 * An expectation for checking an element is visible and enabled such that you
		 * can click it
		 */
		public  WebElement waitForElementToBeClickable(By locator, int timeout) {
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			return wait.until(ExpectedConditions.elementToBeClickable(locator));

		}
		public  void ClickWhenReady(By locator ,int timeout) {
			waitForElementToBeClickable(locator , timeout).click();
			
		}
		public  String getElementAttribute(By locator ,int timeout,String name) {
			return waitForElementToBeClickable(locator , timeout).getAttribute(name);
			
		}
		/**
		 * An expectation for checking WebElement with given locator has specific text
		 * @param locator
		 * @param timeout
		 * @return
		 */
		public  Boolean waitForElementTextToBe(By locator, int timeout,String value) {
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			return wait.until(ExpectedConditions.textToBe(locator, value));
		}
		public   Boolean waitForUrlContaines( int timeout,String Urlvalue) {
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			return wait.until(ExpectedConditions.urlContains(Urlvalue));
		}
		public  Boolean waitForUrlToBe( int timeout,String Urlvalue) {
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			return wait.until(ExpectedConditions.urlToBe(Urlvalue));
		}
		
		/**
		 * an expection for checking if the given element is selected
		 * @param locator
		 * @param timeout
		 * @return
		 */
		public Boolean waitForElementToBeSelected( By locator, int timeout) {
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			return wait.until(ExpectedConditions.elementToBeSelected(locator));
		}
		/**
		 * An expectation for checking whether the given frame is available to switch to. 

	             If the frameis available it switches the given driver to the specified frame.

		 * @param locator
		 * @param timeout
		 * @return
		 */
		public WebDriver waitForframToBeSelected( By locator, int timeout) {
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
		}
		public  WebDriver waitForframToBeSelected( String name0rId, int timeout) {
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(name0rId));
		}
		public  WebDriver waitForframToBeSelected( int index, int timeout) {
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));
		}
		//*******************fluentWait**********************************************
		public  WebElement waitforElementWithFluentWait(By locator,int timeout ,int pollingtime) {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
	                .withTimeout(Duration.ofSeconds(timeout))
	                .pollingEvery(Duration.ofSeconds(pollingtime))
	                .ignoring(StaleElementReferenceException.class)
	                .ignoring(NoSuchElementException.class);
	             return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	             
			
		}
		
		

}
