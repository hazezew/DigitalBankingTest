package TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestMenu {
	WebDriver driver;
	
	@BeforeClass
	public void start() {
		System.setProperty("webdriver.chrome.driver", "D:\\GCS\\Selenium Webdrivers\\Version 74\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	@Test //handling hover over menu 
	public void checkMenu() throws InterruptedException {		
		driver.get("https://www.stima-sacco.com/");		
		WebElement menuNews=driver.findElement(By.linkText("News"));
		Actions hover=new Actions(driver);
		hover.moveToElement(menuNews).perform();
		
		Thread.sleep(3000);
		driver.findElement(By.linkText("Gallery")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.stima-sacco.com/index.php/news/gallery");		
	}
	@Test //Handling duplicate web element
	public void checkInfiBean() throws InterruptedException {
		driver.get("https://www.infibeam.com/MyAccount.action");
		driver.findElement(By.id("new-account-btn")).click();
		
		driver.findElement(By.id("password")).sendKeys("mypassword");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//form[@name='registration-form']")).findElement(By.id("password")).sendKeys("yourpassword");
		Thread.sleep(2000);	
	}
	@Test //Working with Frames
	public void checkFrames() throws InterruptedException {
		driver.get("https://seleniumhq.github.io/selenium/docs/api/java/index.html");
		driver.switchTo().frame("classFrame");
		driver.findElement(By.linkText("org.openqa.selenium.chrome")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "https://seleniumhq.github.io/selenium/docs/api/java/index.html");
		driver.switchTo().defaultContent();
		driver.switchTo().frame(1);
		driver.findElement(By.linkText("AbstractHttpCommandCodec.CommandSpec")).click();
		Thread.sleep(2000);
	}
	
	@AfterClass
	public void close() {
		driver.close();
	}
}
