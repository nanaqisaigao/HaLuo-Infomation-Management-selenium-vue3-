package Test;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import PO.BasePage;
import PO.LoginPage;
import PO.StuPunchCardPage;
import PO.StuSettingPage1;
import PO.StuWeeklyPaperAddPage;
import cases.SmokeCase;

public class testtesttesSmoke extends SmokeCase{


	@Test
	public void TesttestTest() throws Exception{
		System.setProperty("webdriver.chrome.driver", "D:\\StudyProgramInstall\\drivers\\google\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		String url = "http://localhost:8000/user/login";
		driver.get(url);
		driver.manage().window().maximize();
		
		LoginPage loginPage = new LoginPage(driver);
		loginPage.implicateWait_Seconds(5);
		loginPage.enterUsername("12345678910");
		loginPage.enterPassword("Student1@");
		loginPage.clickLoginButton();
		Thread.sleep(1000);
		System.out.print(loginPage.getPopupMessage());
		
		BasePage basePage = new BasePage(driver);
		basePage.turnToDailyPunchCard();
		Thread.sleep(500);
		basePage.turnToPunchRecord();
		Thread.sleep(500);
		basePage.turnToleisureTime();
		Thread.sleep(500);
		basePage.turnToAddWeeklyPaper();
		Thread.sleep(500);
		basePage.turnToMyWeeklyPaper();
		Thread.sleep(500);
		basePage.turnTOUpdatePersonalInfo();
		Thread.sleep(500);
		basePage.turnTOUpdatePassword();

/*		Thread.sleep(1000);
		BasePage basePage = new BasePage(driver);
		basePage.turnToAddWeeklyPaper();
		Thread.sleep(500);
		
		StuWeeklyPaperAddPage stuWeeklyPaperAddPage = new StuWeeklyPaperAddPage(driver);
	
		stuWeeklyPaperAddPage.enterWeeklyPaperDate("2024-01-01","2024-01-03");
		stuWeeklyPaperAddPage.enterWeeklyPaperMessage("sadsad","sadsad2","sadsad3");
		stuWeeklyPaperAddPage.delayedOrNot("1");
		
		stuWeeklyPaperAddPage.clickSubmit();
		Thread.sleep(800);
		String msg = stuWeeklyPaperAddPage.getPopupMessage();
//		
*/

	/*	BasePage basePage = new BasePage(driver);
		basePage.turnTOUpdatePersonalInfo();
		StuSettingPage1 settingPage1 = new StuSettingPage1(driver);
		settingPage1.selectWhatCanSelect("0", "0", "1", "1");
		
		Thread.sleep(3000);*/
		
		
//		
		Thread.sleep(500);
		driver.quit();
	}
}
