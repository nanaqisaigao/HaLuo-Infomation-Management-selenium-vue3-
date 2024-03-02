package PO;

import java.security.PublicKey;
import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import io.netty.buffer.ByteBufUtil;
import junit.framework.Assert;


public class StuWeeklyPaperAddPage extends BasePage{
	public StuWeeklyPaperAddPage(WebDriver driver) {
		super(driver);
	}
	
//	By weeklyPaperDate = By.className("ant-picker-input");
	By start = By.xpath("/html/body/div[1]/div/section/div[2]/main/div/div/div/form/div[1]/div/div[1]/input");
	By end = By.xpath("/html/body/div[1]/div/section/div[2]/main/div/div/div/form/div[1]/div/div[3]/input");
	
	
	By thisWeekContent = By.id("completedTasks");
	By thisWeekThink = By.id("thoughts");
	By nextWeekContent = By.id("plannedTasks");
	
	By radioButtons = By.xpath("/html/body/div[1]/div/section/div/main/div/div/div/form/div[5]/input");
	
	By submit = By.xpath("/html/body/div[1]/div/section/div/main/div/div/div/form/button");
	
	private By popup = By.className("ant-message-notice-content");
	

	
	
	/*
	 * 填写日期
	 * */
	public void enterWeeklyPaperDate(String Date1,String Date2) {
	/*	
	List<WebElement> elements = driver.findElements(weeklyPaperDate);
	//清除只读属性
	JavascriptExecutor js = (JavascriptExecutor)driver;
	String jsCode = "document.getElementById(\" \").removeAttribute('readonly')";
	js.executeScript(jsCode);
	implicateWait_Seconds(2);
	try {
	//输入日期
	WebElement date1 = elements.get(0);
	clickElement(date1);
	Thread.sleep(300);
	enterText_String(date1, Date1);	
//	date1.sendKeys(Date1);
	Thread.sleep(500);
	
	WebElement date2 = elements.get(1);
	clickElement(date2);
	Thread.sleep(300);
	enterText_String(date1, Date2);
	Thread.sleep(500);
	
	Actions actions = new Actions(driver);
	actions.keyDown(Keys.ENTER)
	.keyUp(Keys.ENTER)
	.perform();
	*/
		implicateWait_Seconds(3);
		try {
		driver.findElement(start).sendKeys(Date1);
		WebElement elementEnd = driver.findElement(end);
		elementEnd.sendKeys(Date2);
		elementEnd.sendKeys(Keys.ENTER);
	}catch (Exception e) {
		Assert.assertTrue("未找到元素",false);
	}
	
	}
	
	public void enterWeeklyPaperMessage(String taskOne,String inspiration,String taskTwo) throws InterruptedException {
		WebElement element1 = driver.findElement(thisWeekContent);
		WebElement element2 = driver.findElement(thisWeekThink);
		WebElement element3 = driver.findElement(nextWeekContent);
//		clickElement(element1);
		enterText_String(element1, taskOne);
		enterText_String(element2, inspiration);
		enterText_String(element3, taskTwo);
//		 String js = "var sum=document.getElementById('completedTasks'); sum.value='" + taskOne + "';";
//		((JavascriptExecutor)driver).executeScript(js);
	}
	public void delayedOrNot(String n) {
		if(n == "1") {
			driver.findElement(radioButtons).click();
		}
		
	}
	
	public void clickSubmit() {
		clickElement(driver.findElement(submit));
	}
	
	
	public String getPopupMessage() {
		WebElement element = driver.findElement(popup);
		return element.getText();
	}

}
