package PO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import junit.framework.Assert;

public class RegisterPage extends BasePage{
	
	public RegisterPage(WebDriver driver) {
		super(driver);
	}
	
	private By btnRegister =  By.xpath("/html/body/div[1]/div/div/div/div/div/div[2]/form/div[1]/div[1]/div[1]/div/div[2]/div");
	private By userName = By.id("username");
	private By phone = By.id("phone");
	private By password = By.id("password");
	private By btnSubmit = By.xpath("/html/body/div/div/div/div/div/div/div[2]/form/button");
	private By popUp = By.className("ant-message-custom-content");
	
	public void clickRegister() {
		driver.findElement(btnRegister).click();
	}
	public void enterRegisterInfo(String username,String phone1,String password1) throws InterruptedException {
		enterText_String(driver.findElement(userName), username);
		enterText_String(driver.findElement(phone), phone1);
		enterText_String(driver.findElement(password), password1);
		Thread.sleep(500);
		driver.findElement(btnSubmit).click();
	}
	
	public void assertSuccessOrNot() {
		Assert.assertEquals("success", driver.findElement(popUp).getText());
	}

}
