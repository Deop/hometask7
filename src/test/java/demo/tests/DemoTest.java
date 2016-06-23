package demo.tests;

import demo.forms.TutSearchForm;
import webdriver.BaseTest;

public class DemoTest extends BaseTest {

	
	public void runTest() {

		logger.step(1);
		TutSearchForm tsf = new TutSearchForm();
		tsf.assertLogo();

		logger.step(2);
		tsf.searchFor("A1QA");

		logger.step(3);
		tsf.assertA1QAString();
	}
}
