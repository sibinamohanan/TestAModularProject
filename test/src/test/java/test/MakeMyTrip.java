package test;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MakeMyTrip {

	static WebDriver driver;
	static String browserName;

	@BeforeTest
	public void setAllConfig() {

		browserName = "chrome";

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

	}

	@Test
	public void runTest() {
		driver.get("https://www.makemytrip.com/");

		WebDriverWait wait = new WebDriverWait(driver, 45);
		
		/*if(driver.findElement(By.xpath("//i[@class='wewidgeticon we_close']")).isDisplayed()){
			
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@class='wewidgeticon we_close']")));
		driver.findElement(By.xpath("//i[@class='wewidgeticon we_close']")).click();
		}*/
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='DEL, Delhi Airport India']")));
		wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='DEL, Delhi Airport India']")));
		driver.findElement(By.xpath("//span[text()='DEL, Delhi Airport India']")).click();
		if(driver.findElement(By.xpath("//i[@class='wewidgeticon we_close']")).isDisplayed()){
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@class='wewidgeticon we_close']")));
			driver.findElement(By.xpath("//i[@class='wewidgeticon we_close']")).click();
			}
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[text()='Bangkok, Thailand']")));
		driver.findElement(By.xpath("//p[text()='Bangkok, Thailand']")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='To']")));
		driver.findElement(By.xpath("//input[@placeholder='To']")).click();
		driver.findElement(By.xpath("//input[@placeholder='To']")).sendKeys("cok");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='Kochi, India']")));
		driver.findElement(By.xpath("//p[text()='Kochi, India']")).click();

		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//span[@class='DayPicker-NavButton DayPicker-NavButton--next']")));
		driver.findElement(By.xpath("//span[@class='DayPicker-NavButton DayPicker-NavButton--next']")).click();
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//span[@class='DayPicker-NavButton DayPicker-NavButton--next']")));
		driver.findElement(By.xpath("//span[@class='DayPicker-NavButton DayPicker-NavButton--next']")).click();

		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//div[contains(@aria-label,'Sun Sep 13 2020')]/..//p[text()='15']")));
		driver.findElement(By.xpath("//div[contains(@aria-label,'Sun Sep 13 2020')]/..//p[text()='15']")).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Search']")));
		driver.findElement(By.xpath("//a[text()='Search']")).click();

		List<WebElement> elms = driver
				.findElements(By.xpath("//p[contains(text(),'1 stop')]/preceding::span[number(.) < 8000]"));

		System.out.println("elms size:" + elms.size());

		// scan all elements and get some value.
		for (WebElement elm : elms) {
			String amount = elm.getText();
			System.out.println(amount);

		}

	}

}
