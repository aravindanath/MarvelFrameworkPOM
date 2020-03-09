package testScripts;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageRepo.BaseClass;

public class BaseTest {
	protected WebDriver driver;
	
	@BeforeClass
	public void setup() throws IOException {
		String browser = BaseClass.getValue("browser");
		if (browser.equalsIgnoreCase("chrome")) {
			ChromeOptions ops = new ChromeOptions();
			ops.addArguments("--disable-notifications");
			ops.addArguments("--incognito");
			ops.setAcceptInsecureCerts(true);
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(ops);
			driver.get(BaseClass.getValue("url"));
		} else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions fio = new FirefoxOptions();
			fio.setAcceptInsecureCerts(true);
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new FirefoxDriver();
		}
		driver.manage().window().fullscreen();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

	}

	@AfterClass
	public void teardown() throws InterruptedException {
		Thread.sleep(2000);
		driver.close();
	}


}
