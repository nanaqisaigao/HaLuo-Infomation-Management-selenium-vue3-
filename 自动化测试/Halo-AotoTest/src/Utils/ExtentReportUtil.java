package Utils;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class ExtentReportUtil extends TestWatcher{
	private ExtentReports extentReports;

	public ExtentReportUtil(ExtentReports extentReports) {
		this.extentReports = extentReports;
	}

	private void flushReports(ExtentReports extentReports, ExtentTest test) {
		extentReports.endTest(test);
		extentReports.flush();

	}
	@Override
    protected void succeeded(Description description) {
        ExtentTest test = extentReports.startTest(description.getDisplayName(), "-");
        test.log(LogStatus.PASS, "-");
        flushReports(extentReports, test);
    }

    @Override
    protected void failed(Throwable e, Description description) {
        ExtentTest test = extentReports.startTest(description.getDisplayName(), "Test failed");

        test.log(LogStatus.FAIL, e);
        flushReports(extentReports, test);

    }
}
