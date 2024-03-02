package PO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import junit.framework.Assert;

//密码更改成功判断未写

public class StuSettingPage2 extends BasePage{

	public StuSettingPage2(WebDriver driver) {
		super(driver);
	}
	By inputPssNow = By.xpath("/html/body/div[1]/div/section/div/main/div/div/div/form/div[1]/div/div[2]/div/div/span/input");
	By inputPssNew = By.xpath("/html/body/div[1]/div/section/div/main/div/div/div/form/div[2]/div/div[2]/div/div/span/input");
	By inputPssNewTwo = By.xpath("/html/body/div[1]/div/section/div/main/div/div/div/form/div[3]/div/div[2]/div/div/span/input");
	By clickButton = By.xpath("/html/body/div[1]/div/section/div/main/div/div/div/form/div[4]/div/div/div/div/button");
	
	By popUp = By.xpath("/html/body/div[2]/div/div/div[1]/div/div");
	
	public void changePassWord(String pssNow,String pssNew,String pssNewTwo) {
		driver.findElement(inputPssNow).sendKeys(pssNow);
		driver.findElement(inputPssNew).sendKeys(pssNew);
		driver.findElement(inputPssNewTwo).sendKeys(pssNewTwo);
	}
	
	public void clickUpdate() {
		WebElement element = driver.findElement(clickButton);
		clickElement(element);
	}
	
	public void successedOrNot() {
		WebElement element = driver.findElement(popUp);
		System.out.println(element.getText());
		Assert.assertEquals("success", element.getText());

	}
	
	
}
