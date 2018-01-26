package gov.MSI;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import gov.MSI.resources.Browser;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MsiApplicationTests {
	@LocalServerPort
	private int testPort;

	private Browser browser;

	@Before
	public void setUp() throws Exception {
		browser = new Browser(String.format("http://localhost:%d", testPort));
	}

	@After
	public void tearDown() {
		browser.close();
	}

	@Test
	public void pageLoads() {
	    browser.navigateTo("/");
	    assertFalse(browser.find(".message").getText().contains("Hello, world!"));
	}

}
