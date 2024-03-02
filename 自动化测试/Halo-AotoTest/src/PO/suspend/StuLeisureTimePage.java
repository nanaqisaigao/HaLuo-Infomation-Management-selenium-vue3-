package PO.suspend;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import PO.BasePage;

public class StuLeisureTimePage extends BasePage {

	public StuLeisureTimePage(WebDriver driver) {
		super(driver);
	}

//// 切换本周下周
//	private By weekNow = By.className("");
//	private By weekNext = By.className("");
//
//	public void chooseNowWeek() {
//		driver.findElement(weekNow).click();
//	}
//
//	public void chooseNextWeek() {
//		driver.findElement(weekNext).click();
//	}
//	
//	// 返回日期时间
//	private By WeekShow = By.className("");
//	public String getWeek(Integer index) {
//		List<WebElement> elements = driver.findElements(WeekShow);
//		return elements.get(index).getText();
//	}
//	
}
