package cases;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.NetworkMode;

import Utils.ExtentReportUtil;

public class SmokeCase {
	private static ExtentReports extentReports;

	@BeforeClass
	public static void beforClass() {
		String reportPath = "reports/SmokeReports.html";
		extentReports = new ExtentReports(reportPath, true, NetworkMode.OFFLINE);
	}

	@AfterClass
	public static void afterClass() {
		extentReports.close();
	}

	@Rule
	public ExtentReportUtil extentReportUtil = new ExtentReportUtil(extentReports);
}
