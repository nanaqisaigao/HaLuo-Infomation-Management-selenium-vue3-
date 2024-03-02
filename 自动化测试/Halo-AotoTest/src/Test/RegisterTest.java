package Test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import PO.LoginPage;
import PO.RegisterPage;
import cases.RegisterCase;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RegisterTest extends RegisterCase{
		private String username;
		private String phone;
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
		public void RegisterTest() throws Exception {
			
			RegisterPage registerPage = new RegisterPage(driver);
			registerPage.implicateWait_Seconds(3);
			try {
			registerPage.clickRegister();
			registerPage.enterRegisterInfo(username, phone, password);
			Thread.sleep(500);
			registerPage.assertSuccessOrNot();
			}catch (Exception e) {
				Assert.assertTrue("未找到元素",false);
			}
			Thread.sleep(500);
		}
		@After
		public void close() {
			driver.quit();
		}

		
		
}
