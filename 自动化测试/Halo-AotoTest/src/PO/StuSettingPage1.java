package PO;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class StuSettingPage1 extends BasePage {

	public StuSettingPage1(WebDriver driver) {
		super(driver);
	}
	
	static int n ;
	
	By inputElements = By.className("ant-input-status-success");
	By selectElements = By.className("ant-select-selection-item");
	By selectElementsTwo = By.className("ant-select-item-option-content");

	By clickButton = By.className("ant-btn-primary");
	By popUp = By.xpath("/html/body/div[2]/div/div/div[1]/div/div");

	public void enterName(String name) {
		WebElement element = driver.findElements(inputElements).get(0);
		cleanElement(element);
		enterText_String(element, name);
	}

	public void selectWhatCanSelect(String indexOne, String indexTwo, String indexThree, String indexFour) throws InterruptedException
			 {
		List<WebElement> lists = driver.findElements(selectElements);

		clickElement(lists.get(0));
		try {
			List<WebElement> lists_1 = new WebDriverWait(driver, Duration.ofSeconds(1))
					.until(driver1 -> driver1.findElements(selectElementsTwo));
			clickElement(lists_1.get(Integer.valueOf(indexOne)));
			System.out.println(n);
			n = lists_1.size();
			System.out.println(n);
		} catch (Exception e) {
			Assert.assertTrue("下拉框无元素", false);
		}

		clickElement(lists.get(1));
		try {
			List<WebElement> lists_2 = new WebDriverWait(driver, Duration.ofSeconds(1))
					.until(driver1 -> driver1.findElements(selectElementsTwo));
			clickElement(lists_2.get(Integer.valueOf(indexTwo) + n));
			n = lists_2.size();
			System.out.println(n);
		} catch (Exception e) {
			cleanElement(lists.get(1));
			Assert.assertTrue("下拉框无元素", false);
		}
		Thread.sleep(1000);
		
		clickElement(lists.get(2));
		try {
		List<WebElement> lists_3 = driver.findElements(selectElementsTwo);
		System.out.println(lists_3.size());
		clickElement(lists_3.get(Integer.valueOf(indexThree)+n));
		n = lists_3.size();
		System.out.println(n);
		}catch (Exception e) {
			Assert.assertTrue("下拉框无元素", false);
		}
		
		clickElement(lists.get(3));
		try {
		List<WebElement> lists_4 = driver.findElements(selectElementsTwo);
		clickElement(lists_4.get(Integer.valueOf(indexFour)+n));
		}catch (Exception e) {
			Assert.assertTrue("下拉框无元素", false);
		}
	}

	public void clickUpdate() {
		driver.findElement(clickButton).click();
	}

	public void successedOrNot() {
		WebElement element = driver.findElement(popUp);
		System.out.println(element.getText());
		Assert.assertEquals("修改成功", element.getText());

	}

}
