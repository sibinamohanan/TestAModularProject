package test;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

public class searchjob {

	static String browserName;
	static WebDriver driver;

	static ExtentTest test;
	static ExtentReports report;

	/**
	 * Description: Setting browser properities
	 * 
	 * @author Sibina
	 */
	@Parameters("browserName")
	@BeforeTest
	public void setAllConfig(String browserName) {

		// browser = "Chrome";

		// this condition block sets configuration for Chrome browser
		if (browserName.contains("chrome")) {

			WebDriverManager.chromedriver().setup();

			driver = new ChromeDriver();
		}

		if (browserName.contains("ie")) {

			WebDriverManager.iedriver().setup();

			driver = new InternetExplorerDriver();

		}
		driver.manage().window().maximize();

		report = new ExtentReports(System.getProperty("user.dir") + "\\ExtentReportResults.html");
		test = report.startTest("Started testing job portal");

	}

	@Test
	public void runTest() {
		driver.get("https://www.flipkart.com/");
		test.log(LogStatus.PASS, "Launched the site");
		WebDriverWait wait = new WebDriverWait(driver, 25);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@title='Flipkart']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='_2AkmmA _29YdH8']")));
		driver.findElement(By.xpath("//button[@class='_2AkmmA _29YdH8']")).click();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//input[@title='Search for products, brands and more']")));
		driver.findElement(By.xpath("//input[@title='Search for products, brands and more']")).sendKeys("hats");
		// driver.findElement(By.xpath("//li//div[contains(@data-tkid,'b87c22f2-3a0e-4b31-8b28-deced8709a61.hats
		// for men')]")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
		String url=driver.getCurrentUrl();
		if(url.contains("Fipkart")) {
			test.log(LogStatus.PASS, "Url contains flipkart");
		}
		String title= driver.getTitle();
		if(title.contains("Flipkart")) {
			test.log(LogStatus.PASS, "Title contains flipkart");
		}
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		test.log(LogStatus.PASS, "Clicked on search");
		/*		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("(//div//a[@title='Solid Umpire Cricket Sun Hat Cap'][contains(@href,'zacharias')])[1]")));
		assertEquals(driver
				.findElement(By
						.xpath("(//div//a[@title='Solid Umpire Cricket Sun Hat Cap'][contains(@href,'zacharias')])[1]"))
				.isDisplayed(), true);
		 */
	}


	@AfterTest 
	public void teardown() { 
		driver.close();
		driver.quit(); 
		report.endTest(test);
		report.flush(); }

}
