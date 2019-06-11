package TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestCases.ExceptionHandler.class)
public class LoginTest {
	public WebElement username;
	public WebElement password;
	public WebElement submit;
	public WebElement div;
	public WebDriver driver;
	public String url;
	public String errorMessage;
	
	public LoginTest() {
		System.setProperty("webdriver.chrome.driver", "D:\\GCS\\Selenium Webdrivers\\Version 74\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://devops-deve.kifiya.et:8082/mfs-admin/logout");
		
		username=driver.findElement(By.id("username"));		
		password=driver.findElement(By.id("password"));		
		submit=driver.findElement(By.xpath("//button[@class='form-control btn-block btn-lg primary-button btn']"));
	}
	public void initialize() {
		driver.navigate().to("http://devops-deve.kifiya.et:8082/mfs-admin/logout");
		username=driver.findElement(By.id("username"));		
		password=driver.findElement(By.id("password"));		
		submit=driver.findElement(By.xpath("//button[@class='form-control btn-block btn-lg primary-button btn']"));
	}
	
	@Test (priority = 1)
	public void emptyUserName() throws InterruptedException {
		Thread.sleep(2000);
		username.clear();
		password.sendKeys("system");
		submit.click();
		url=driver.getCurrentUrl();
		Assert.assertEquals(url, "http://devops-deve.kifiya.et:8082/mfs-admin/login");
	}
	@Test (priority = 2)
	public void emptyPassword() throws InterruptedException {
		Thread.sleep(2000);
		username.sendKeys("mfs");
		password.clear();
		submit.click();
		url=driver.getCurrentUrl();
		Assert.assertEquals(url, "http://devops-deve.kifiya.et:8082/mfs-admin/login");
		
	}
	@Test (priority = 3)
	public void emptyUsernameEmptyPassword() throws InterruptedException{
		Thread.sleep(2000);
		username.clear();
		password.clear();
		submit.click();
		url=driver.getCurrentUrl();
		Assert.assertEquals(url, "http://devops-deve.kifiya.et:8082/mfs-admin/login");
		
	}
	@Test (priority = 4)
	public void validUsernameInvalidPassword() throws InterruptedException {
		Thread.sleep(2000);
		username.sendKeys("mfs");
		password.sendKeys("system1");
		submit.click();
		errorMessage=driver.findElement(By.xpath("//div[@class='alert alert-dismissable alert-danger']/span")).getText();
		boolean errorMatches=errorMessage.contains("Unable to reach authentication server.");
		
		Assert.assertEquals(errorMatches, true);
	}
	@Test (priority = 5)
	public void invalidUsernameValidPassword() throws InterruptedException{		
		Thread.sleep(2000);
		this.initialize();
		username.sendKeys("mfs1");
		password.sendKeys("system");
		submit.click();
		errorMessage=driver.findElement(By.xpath("//div[@class='alert alert-dismissable alert-danger']/span")).getText();
		boolean errorMatches=errorMessage.contains("Unable to reach authentication server.");
		Assert.assertEquals(errorMatches, true);
	}
	@Test (priority = 6)
	public void invalidUsernameInvalidPassword() throws InterruptedException {
		Thread.sleep(2000);
		
		this.initialize();
		
		username.sendKeys("mfs1");
		password.sendKeys("system1");
		submit.click();
		url=driver.getCurrentUrl();
		Assert.assertEquals(url, "http://devops-deve.kifiya.et:8082/mfs-admin/authenticate");
		
		//Remove error message DIV after one second as it squeezes other web elements to disappear
		Thread.sleep(1000);
		div=driver.findElement(By.xpath("//div[@class='alert alert-dismissable alert-danger']"));
				
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].parentNode.removeChild(arguments[0])", div);
	}
	@Test (priority = 7)
	public void validUsernameValidPassword() throws InterruptedException {
		Thread.sleep(2000);
		this.initialize();
		
		username.sendKeys("mfs");
		password.sendKeys("system");
		submit.click();
		url=driver.getCurrentUrl();
		
		Assert.assertEquals(url, "http://devops-deve.kifiya.et:8082/mfs-admin/dashboard?loggedInUserName=mfs&appicationVersion=Build+Version%3A+1.1.0");
	}
	@Test (priority = 8, dependsOnMethods = {"validUsernameValidPassword"})
	public void logout() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.xpath("//img[@class='profile-pic']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"main-wrapper\"]/div[1]/nav/div[2]/ul[2]/li[3]/div/ul/li[2]/a")).click();
		url=driver.getCurrentUrl();
		Assert.assertEquals(url, "http://devops-deve.kifiya.et:8082/mfs-admin/logout");
		driver.close();		
	}
}
