package Test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import PO.BasePage;
import PO.LoginPage;
import PO.StuSettingPage2;
import cases.StuSettingCase2;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class StuSettingTest2 extends StuSettingCase2 {

	private String pssNow;
	private String pssNew;
	private String pssNewTwo;

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
	public void StuSettingTest2() throws Exception{
		// 登录
		LoginPage loginPage = new LoginPage(driver);
		loginPage.implicateWait_Seconds(5);
		loginPage.enterUsername("12345678910");
		loginPage.enterPassword(pssNow);
		loginPage.clickLoginButton();
		/*
		 * 用Cookie也行 driver直接定位设置页面，再用Cookie Cookie Logincookie = new Cookie("","","/");
		 * driver.manage().addCookie(Logincookie);
		 */

		Thread.sleep(1000);
		BasePage basePage = new BasePage(driver);
		basePage.turnTOUpdatePassword();
		
		Thread.sleep(500);
		StuSettingPage2 settingPage2 = new StuSettingPage2(driver);
		settingPage2.changePassWord(pssNow, pssNew, pssNewTwo);	
		settingPage2.clickUpdate();
		Thread.sleep(1500);
		settingPage2.successedOrNot();
		
		Thread.sleep(800);
	}
	
	@After
	public void close() {
		driver.quit();
	}

}
