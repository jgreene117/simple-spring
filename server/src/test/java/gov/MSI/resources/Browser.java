package gov.MSI.resources;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Browser  {
	private WebDriver driver;
	private String baseUrl;

	public Browser(String baseUrl) {
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--headless");
		chromeOptions.addArguments("--disable-gpu");
		chromeOptions.addArguments("--no-sandbox");

		System.setProperty("webdriver.chrome.driver","chromedriver");
		driver = new ChromeDriver(chromeOptions);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		this.baseUrl = baseUrl;
	}

	public WebElement find(String cssSelector) {
		try {
			return driver.findElement(By.cssSelector(cssSelector));
		} catch (NoSuchElementException e) {
			throw new NoCssSelectorMatchException(cssSelector, driver.getPageSource(), e);
		}
	}

	public void navigateTo(String path) {
		driver.navigate().to(baseUrl + path);
	}

	public void takeScreenshot(String fileName) throws IOException {
		TakesScreenshot screen = (TakesScreenshot) driver;
		byte[] srcFile = screen.getScreenshotAs(OutputType.BYTES);
		Files.write(Paths.get(fileName), srcFile);
	}

	public void close() {
		driver.close();
	}

	public WebDriver.TargetLocator switchTo()  {
		return driver.switchTo();
	}

	public List<WebElement> findElements(By by) {
		return driver.findElements(by);
	}

	public WebElement waitForElement(By by) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement element = wait.until(
				ExpectedConditions.visibilityOfElementLocated(by));
		return element;
	}

	private class NoCssSelectorMatchException extends RuntimeException {
		private static final long serialVersionUID = 1L;

		NoCssSelectorMatchException(String cssSelector, String pageSource, Throwable cause) {
			super("Could not find CSS selector:" + cssSelector + " in content: " + pageSource, cause);
		}
	}

	public void refresh() {
		driver.navigate().refresh();
	}
}
