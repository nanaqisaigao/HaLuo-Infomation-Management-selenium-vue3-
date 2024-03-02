package PO;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class LoginPage extends BasePage{

	private By inputPhone = By.id("phone");
	private By inputPss = By.id("password");
	private By loginButton = By.className("ant-btn-primary") ;
	//判断成功框
	private By popup = By.className("ant-message-notice-content");
    

	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
   
	
	public void enterUsername(String phone) {
		WebElement element = driver.findElement(inputPhone);
		element.sendKeys(phone);
//		enterText_String(element, phone);
	}
	
	public void enterPassword(String pss) {
		WebElement element = driver.findElement(inputPss);
		element.sendKeys(pss);
//		enterText_String(element, pss);
	}
	
	
	public void clickLoginButton() {
		WebElement element = driver.findElement(loginButton);
		clickElement(element);
	}
	
	public String getPopupMessage() {
		WebElement element = driver.findElement(popup);
		return element.getText();
	}
	
}
