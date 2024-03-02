package Test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import PO.BasePage;
import PO.LoginPage;
import PO.StuWeeklyPaperAddPage;
import cases.StuWeeklyPaperAddCase;
import junit.framework.Assert;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class StuWeeklyPaperAddTest extends StuWeeklyPaperAddCase {

	private String Date1;
	private String Date2;
	private String taskOne;
	private String inspiration;
	private String taskTwo;
	private String n; // 1表示选有延期

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
	public void StuWeeklyPapaerAddTest() throws Exception {
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
		basePage.turnToAddWeeklyPaper();
		Thread.sleep(500);
		
		StuWeeklyPaperAddPage stuWeeklyPaperAddPage = new StuWeeklyPaperAddPage(driver);
		
		stuWeeklyPaperAddPage.enterWeeklyPaperDate(Date1,Date2);
		stuWeeklyPaperAddPage.enterWeeklyPaperMessage(taskOne, inspiration, taskTwo);
		stuWeeklyPaperAddPage.delayedOrNot(n);
		
		stuWeeklyPaperAddPage.clickSubmit();
		Thread.sleep(800);
		String msg = stuWeeklyPaperAddPage.getPopupMessage();
		Assert.assertEquals("提交成功！", msg);
		Thread.sleep(1000);
		stuWeeklyPaperAddPage.turnToMyWeeklyPaper();
		Thread.sleep(2000);
		Assert.assertEquals("获取周报列表成功", stuWeeklyPaperAddPage.getPopupMessage());
		Thread.sleep(800);

	}
	
	@After
	public void close() {
		driver.quit();
	}

}