package Test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import PO.BasePage;
import PO.LoginPage;
import PO.StuPunchCardPage;
import PO.StuSettingPage1;
import cases.StuPunchCardCase;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class StuPunchCardTest extends StuPunchCardCase {
	private String cardStartDate;
	private String cardEndDate;

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
	public void StuPunchCardTest() throws Exception {
		// 登录
		LoginPage loginPage = new LoginPage(driver);
		loginPage.implicateWait_Seconds(5);
		loginPage.enterUsername("12345678910");
		loginPage.enterPassword("Student1@");
		loginPage.clickLoginButton();

		Thread.sleep(1000);
		BasePage basePage = new BasePage(driver);
		basePage.turnToDailyPunchCard();

		StuPunchCardPage stuPunchCardPage = new StuPunchCardPage(driver);
		System.out.println(cardStartDate + "----" + cardEndDate);
		stuPunchCardPage.enterDate(cardStartDate, cardEndDate);
		stuPunchCardPage.submitPunchCard();
		// 判断是否弹出打卡成功
		Thread.sleep(500);
		String result = stuPunchCardPage.getPopUpMessages();

		System.out.println(result);
		Assert.assertEquals("打卡成功！", result);
		Thread.sleep(1000);
		
		stuPunchCardPage.turnToPunchRecord();
		Thread.sleep(500);
		String result2 = stuPunchCardPage.getPopUpMessages();
		Thread.sleep(1000);
		
		
		
		Thread.sleep(500);
	}

	@After
	public void close() {
		driver.quit();
	}

}
