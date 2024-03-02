package Test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import PO.LoginPage;
import cases.LoginCase;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LoginTest extends LoginCase {
	private String username;
	private String password;

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
	public void LoginTest() throws Exception {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.implicateWait_Seconds(5);
		loginPage.enterUsername(username);
		loginPage.enterPassword(password);
		loginPage.clickLoginButton();

//		UserInfoPage userInfoPage = new UserInfoPage(driver);

		Thread.sleep(1000);
		String judge = loginPage.getPopupMessage();
		Assert.assertEquals("success", judge);

		Thread.sleep(500);
	}

	@After
	public void close() {
		driver.quit();
	}

	

}
