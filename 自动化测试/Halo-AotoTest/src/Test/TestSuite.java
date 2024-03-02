package Test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	testtesttesSmoke.class,
	LoginTest.class,
	StuPunchCardTest.class,
	StuWeeklyPaperAddTest.class,
	StuSettingTest1.class,
	StuSettingTest2.class,
})


public class TestSuite {

}
