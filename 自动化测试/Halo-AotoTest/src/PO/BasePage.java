package PO;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BasePage {
	WebDriver driver;

	public BasePage(WebDriver driver) {
		this.driver = driver;
	}
	
	/*
	 * 一些基础操作的封装
	 */

	// 隐式等待
	public void implicateWait_Seconds(Integer x) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(x));
	}
	//显示等待
	public WebElement explicitWait_Seconds(By by,Integer x) {
		WebElement foo = new WebDriverWait(driver, Duration.ofSeconds(x))
				.until(driver -> driver.findElement(by));
		return foo;
	}
	//全屏拍照
	public void screenShot() throws IOException {
		File screenShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File destinationFile = new File("./screen");
		FileUtils.copyFileToDirectory(screenShot, destinationFile);
	}
	//获得当前页面标题
	public String getCurrentPageTitle() {
		return driver.getTitle();
	}
	
	//判断一个页面元素是否显示在当前页面
	public void showOrNot(WebElement element) {
		Assert.assertTrue("元素不在当前页面", element.isDisplayed());
	}
	
	// 在文本框输入字符
	public void enterText_String(WebElement element,String text) {
		   try {
		        if (element.isEnabled()) {
		            element.clear();
		            element.sendKeys(text);
		        }
		    } catch (Exception e) {
		       System.out.println(e.getMessage());
		    }	
	}
	
	//清空输入框
	public void cleanElement(WebElement element) {
		try {
	        if (element.isEnabled()) {
	            element.clear();
	        }
	    } catch (Exception e) {
	       System.out.println(e.getMessage());
	    }	
	}
	
	//鼠标左键点击
	public void clickElement(WebElement element) {
		try {
	        if (element.isEnabled()) {
	            element.click();
	        }
	    } catch (Exception e) {
	       System.out.println(e.getMessage());
	    }	
	}
	
	/*
	 * 进入各个模块
	 */	
	
	//时间管理模块定位
	private By UnfoldTimeManage = By.xpath("/html/body/div[1]/div/section/aside/div/div[1]/ul/li[2]/div/span");
	private By dailyPunchCard = By.xpath("/html/body/div[1]/div/section/aside/div/div[1]/ul/li[2]/ul/li[1]/span");
	private By punchCardRecord = By.xpath("/html/body/div[1]/div/section/aside/div/div[1]/ul/li[2]/ul/li[2]/span");
	private By leisureTimeFrame = By.xpath("/html/body/div[1]/div/section/aside/div/div[1]/ul/li[2]/ul/li[3]/span");
	//周报管理模块定位
	private By UnfoldWeeklyPaperManage = By.xpath("/html/body/div[1]/div/section/aside/div/div[1]/ul/li[3]/div/span");
	private By paperAdd = By.xpath("/html/body/div[1]/div/section/aside/div/div[1]/ul/li[3]/ul/li[1]");
	private By myWeeklyPaper = By.xpath("/html/body/div[1]/div/section/aside/div/div[1]/ul/li[3]/ul/li[2]");
	//个人信息模块定位
	private By UnfoldPersonalInformation = By.xpath("/html/body/div[1]/div/section/aside/div/div[1]/ul/li[4]/div/span");
	private By changeInfoMessage = By.xpath("/html/body/div[1]/div/section/aside/div/div[1]/ul/li[4]/ul/li[1]");
	private By changeInfoPassword = By.xpath("/html/body/div[1]/div/section/aside/div/div[1]/ul/li[4]/ul/li[2]");
	
	//打卡模块 每日打卡
		public void turnToDailyPunchCard() {
			implicateWait_Seconds(3);
			try {
			clickElement(driver.findElement(UnfoldTimeManage));
			}catch (Exception e) {
				 e.printStackTrace();
				 System.out.println("未找到元素");
			}
			try {
			clickElement(explicitWait_Seconds(dailyPunchCard, 3));
			}catch (Exception e) {
				 e.printStackTrace();
				 System.out.println("父元素未展开");
				 clickElement(driver.findElement(UnfoldTimeManage));
				 clickElement(explicitWait_Seconds(dailyPunchCard, 3));
			}
			System.out.println(getCurrentPageTitle());
			Assert.assertEquals("每日打卡 - 武科大&哈啰校企合作管理系统", getCurrentPageTitle());
		}
	//打卡模块 打卡记录
		public void turnToPunchRecord() throws IOException {
			implicateWait_Seconds(3);
			try {
			clickElement(driver.findElement(UnfoldTimeManage));
			}catch (Exception e) {
				 e.printStackTrace();
				 screenShot();
				 System.out.println("未找到元素");
			}
			try {
			clickElement(explicitWait_Seconds(punchCardRecord, 3));
			}catch (Exception e) {
				 e.printStackTrace();
				 screenShot();
				 System.out.println("父元素未展开");
				 clickElement(driver.findElement(UnfoldTimeManage));
				 clickElement(explicitWait_Seconds(punchCardRecord, 3));
			}
			System.out.println(getCurrentPageTitle());
			Assert.assertEquals("打卡记录 - 武科大&哈啰校企合作管理系统", getCurrentPageTitle());
		}
	//打卡模块 空闲时段
		public void turnToleisureTime()throws IOException{
			implicateWait_Seconds(3);
			try {
			clickElement(driver.findElement(UnfoldTimeManage));
			}catch (Exception e) {
				 e.printStackTrace();
				 screenShot();
				 System.out.println("未找到元素");
			}
			try {
			clickElement(explicitWait_Seconds(leisureTimeFrame, 3));
			}catch (Exception e) {
				 e.printStackTrace();
				 screenShot();
				 System.out.println("父元素未展开");
				 clickElement(driver.findElement(UnfoldTimeManage));
				 clickElement(explicitWait_Seconds(leisureTimeFrame, 3));
			}
			System.out.println(getCurrentPageTitle());
			Assert.assertEquals("空闲时段 - 武科大&哈啰校企合作管理系统", getCurrentPageTitle());
		}
		//周报管理模块 增加周报
		public void turnToAddWeeklyPaper()throws IOException{
			implicateWait_Seconds(3);
			try {
			clickElement(driver.findElement(UnfoldWeeklyPaperManage));
			}catch (Exception e) {
				 e.printStackTrace();
				 screenShot();
				 System.out.println("未找到元素");
			}
			try {
			clickElement(explicitWait_Seconds(paperAdd, 3));
			}catch (Exception e) {
				 e.printStackTrace();
				 screenShot();
				 System.out.println("父元素未展开");
				 clickElement(driver.findElement(UnfoldWeeklyPaperManage));
				 clickElement(explicitWait_Seconds(paperAdd, 3));
			}
			System.out.println(getCurrentPageTitle());
			Assert.assertEquals("添加周报 - 武科大&哈啰校企合作管理系统", getCurrentPageTitle());
		}
		//周报管理模块 我的周报
		public void turnToMyWeeklyPaper()throws IOException{
			implicateWait_Seconds(3);
			try {
			clickElement(driver.findElement(UnfoldWeeklyPaperManage));
			}catch (Exception e) {
				 e.printStackTrace();
				 screenShot();
				 System.out.println("未找到元素");
			}
			try {
			clickElement(explicitWait_Seconds(myWeeklyPaper, 3));
			}catch (Exception e) {
				 e.printStackTrace();
				 screenShot();
				 System.out.println("父元素未展开");
				 clickElement(driver.findElement(UnfoldWeeklyPaperManage));
				 clickElement(explicitWait_Seconds(myWeeklyPaper, 3));
			}
			System.out.println(getCurrentPageTitle());
			Assert.assertEquals("我的周报 - 武科大&哈啰校企合作管理系统", getCurrentPageTitle());
		}
		//个人信息模块  修改个人信息
		public void turnTOUpdatePersonalInfo() throws IOException {
			implicateWait_Seconds(3);
			try {
			clickElement(driver.findElement(UnfoldPersonalInformation));
			}catch (Exception e) {
				 e.printStackTrace();
				 screenShot();
				 System.out.println("未找到元素");
			}
			try {
			clickElement(explicitWait_Seconds(changeInfoMessage, 3));
			}catch (Exception e) {
				 e.printStackTrace();
				 screenShot();
				 System.out.println("父元素未展开");
				 clickElement(driver.findElement(UnfoldPersonalInformation));
				 clickElement(explicitWait_Seconds(changeInfoMessage, 3));
			}
			System.out.println(getCurrentPageTitle());
			Assert.assertEquals("修改个人信息 - 武科大&哈啰校企合作管理系统", getCurrentPageTitle());
		}	
		//个人信息模块  修改密码
		public void turnTOUpdatePassword() throws IOException {
			implicateWait_Seconds(3);
			try {
			clickElement(driver.findElement(UnfoldPersonalInformation));
			}catch (Exception e) {
				 e.printStackTrace();
				 screenShot();
				 System.out.println("未找到元素");
			}
			try {
			clickElement(explicitWait_Seconds(changeInfoPassword, 3));
			}catch (Exception e) {
				 e.printStackTrace();
				 screenShot();
				 System.out.println("父元素未展开");
				 clickElement(driver.findElement(UnfoldPersonalInformation));
				 clickElement(explicitWait_Seconds(changeInfoPassword, 3));
			}
			System.out.println(getCurrentPageTitle());
			Assert.assertEquals("修改密码 - 武科大&哈啰校企合作管理系统", getCurrentPageTitle());
		}	
		
	/*
	 *分页测试 
	 * */
	private By previousPage = By.className("");
	private By nextPage = By.className("");
	private By eachPage = By.className(""); //  n页/条
	private By inputJumpToPage = By.className("");
	private By pageIndex = By.className("");     //看具体情况而定
	//点击上一页
	public void clickPreviousPage() {
		driver.findElement(previousPage).click();
	}
	
	//点击下一页
	public void clickNextPage() {
		driver.findElement(nextPage);
	}
	
	
	//跳转至第几页
	public void inputJumpToPageThenJump(Integer x) {
		WebElement input = driver.findElement(inputJumpToPage);
		input.sendKeys("x");
		input.sendKeys(Keys.ENTER);
	}

	// 返回当前是第几页
	public String returnNowPage() {
		WebElement nowPageElement = driver.findElement(pageIndex);
		return nowPageElement.getText();
	}
	
	
	

	
	
	

}
