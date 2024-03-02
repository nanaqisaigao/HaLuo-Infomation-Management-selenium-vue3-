package PO;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import junit.framework.Assert;

//打卡成功判断未写

public class StuPunchCardPage extends BasePage{
	
	public StuPunchCardPage(WebDriver driver) {
		super(driver);
	}
//	private By one = By.xpath("/html/body/div[1]/div/section/div[2]/main/div/div/div/div/div[1]/div/div[2]");
	private By PunchCardDate = By.className("ant-picker-input");
	private By two = By.xpath("/html/body/div[3]/div/div/div/div[2]/div[2]/ul/li/button");
	private By inputone = By.xpath("/html/body/div[1]/div/section/div[2]/main/div/div/div/div/div[1]/div/div[2]/div[1]/input");
	private By inputTwo = By.xpath("/html/body/div[1]/div/section/div[2]/main/div/div/div/div/div[1]/div/div[2]/div[3]/input");
	
	private By submit = By.xpath("/html/body/div[1]/div/section/div[2]/main/div/div/div/div/div[2]/button");
	private By popUp = By.className("ant-message-notice-content");
	
	public void enterPCStartDate(String cardStartDate) throws InterruptedException {
		List<WebElement> elements = driver.findElements(PunchCardDate);
		WebElement element = elements.get(0);
		clickElement(element);
		Thread.sleep(300);
		enterText_String(driver.findElement(inputone), cardStartDate);
		Thread.sleep(300);
	}
	
	public void enterPCEndDate(String cardEndDate) throws InterruptedException {
		List<WebElement> elements = driver.findElements(PunchCardDate);
		WebElement element = elements.get(1);
		clickElement(element);
		Thread.sleep(300);
		enterText_String(driver.findElement(inputTwo), cardEndDate);
		Thread.sleep(300);
	}
	
	public void enterDate(String cardStartDate,String cardEndDate) {
		try {
		enterPCStartDate(cardStartDate);
		enterPCEndDate(cardEndDate);
		clickElement(driver.findElement(two));
		}catch (Exception e) {
			Assert.assertTrue(false);
		}
	}
	
	public void submitPunchCard() {
		clickElement(driver.findElement(submit));
	}
	
	
	
	public String getPopUpMessages() {
		String msg = driver.findElement(popUp).getText();
		String[]arr = msg.split(" ");
		String goal = arr[0].trim();
		return goal;
		
	}

}
