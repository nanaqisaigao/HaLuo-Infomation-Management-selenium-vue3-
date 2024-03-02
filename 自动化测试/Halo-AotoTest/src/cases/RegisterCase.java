package cases;

import java.util.Arrays;
import java.util.Collection;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.NetworkMode;

import Utils.ExcelUtil;
import Utils.ExtentReportUtil;

@RunWith(Parameterized.class)
public class RegisterCase {
private static String path = "resources/RegisterData.xlsx";
	
	@Parameterized.Parameters
	public static Collection data() {
		Object[][] data = ExcelUtil.readExcel(path, 0);
		return Arrays.asList(data);
	}

	private static ExtentReports extentReports;

	@BeforeClass
	public static void beforClass() {
		String reportPath = "reports/registerReports.html";
		extentReports = new ExtentReports(reportPath, true, NetworkMode.OFFLINE);
	}

	@AfterClass
	public static void afterClass() {
		extentReports.close();
	}

	@Rule
	public ExtentReportUtil extentReportUtil = new ExtentReportUtil(extentReports);

}
