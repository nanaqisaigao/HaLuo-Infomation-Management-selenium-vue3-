package Test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import PO.BasePage;
import PO.LoginPage;
import PO.StuSettingPage1;

import cases.StuSettingCase1;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class StuSettingTest1 extends StuSettingCase1 {
	private String name;
	private String indexOne;
	private String indexTwo;
	private String indexThree;
	private String indexFour;

	static WebDriver driver = null;

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "D:\\StudyProgramInstall\\drivers\\google\\chromedriver.exe");

		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(chromeOptions);

		driver.manage().window().maximize();
		String loginUrl = "http://localhost:8000/user/login";
		driver.get(loginUrl);
	}

	@Test
	public void StuSettingTest1() throws Exception {
		// 登录
		LoginPage loginPage = new LoginPage(driver);
		loginPage.implicateWait_Seconds(5);
		loginPage.enterUsername("12345678910");
		loginPage.enterPassword("Student1@");
		loginPage.clickLoginButton();
		/*
		 * 用Cookie也行 driver直接定位设置页面，再用Cookie Cookie Logincookie = new Cookie("","","/");
		 * driver.manage().addCookie(Logincookie);
		 */

		Thread.sleep(1000);
		BasePage basePage = new BasePage(driver);
		basePage.turnTOUpdatePersonalInfo();
		
		Thread.sleep(500);
		StuSettingPage1 settingPage1 = new StuSettingPage1(driver);
		Thread.sleep(500);
		settingPage1.enterName(name);
		Thread.sleep(1000);
		settingPage1.selectWhatCanSelect(indexOne, indexTwo, indexThree, indexFour);
		Thread.sleep(1000);
		settingPage1.clickUpdate();
		Thread.sleep(1000);
		settingPage1.successedOrNot();
		Thread.sleep(1000);
		
	}

	@After
	public void close() {
		driver.quit();
	}

}
