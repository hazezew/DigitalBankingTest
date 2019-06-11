package TestCases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Listeners;

@Listeners(TestCases.ExceptionHandler.class)
public class AdminLogin {
	public static WebDriver driver;
	public String loginPage;
	public String afterLoginPage;

	public AdminLogin() {
		System.setProperty("webdriver.chrome.driver", "D:\\GCS\\Selenium Webdrivers\\Version 74\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://devops-deve.kifiya.et:8082/mfs-admin/logout");
		this.loginPage = driver.getCurrentUrl();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	}

	public boolean login() throws Exception {
		driver.findElement(By.id("username")).sendKeys("mfs");
		Thread.sleep(200);
		driver.findElement(By.id("password")).sendKeys("system");
		Thread.sleep(200);
		driver.findElement(By.tagName("button")).click();
		afterLoginPage = driver.getCurrentUrl();

		try {
			if (afterLoginPage.equals("http://devops-deve.kifiya.et:8082/mfs-admin/authenticate")
					|| afterLoginPage.equals(loginPage)) {
				System.out.println("Admin Login Unsuccessful - Failed");
				return false;
			}

			else {

				System.out.println("Admin Login Successful - Passed");
				return true;
			}
		} catch (Exception e) {
			System.out.println("The URL entered does not open: Please check; Opening Admin Login page: Failed");
		}
		return false;
	}

	public void openRegisterAgent() throws InterruptedException {
		driver.findElement(By.linkText("Manage Agents")).click();
		Thread.sleep(1000);
		driver.findElement(By.linkText("Register")).click();
		
		if (driver.getCurrentUrl() == "http://devops-deve.kifiya.et:8082/mfs-admin/agents/signUpAgents") {
			System.out.println("Agent Management Link: Pass");
			System.out.println("Register Agent Link: Pass");
		} else {
			System.out.println("Agent Management Link: Pass");
			System.out.println("Register Agent Link: Pass");
		}
	}

	public boolean agentRegisteration() throws InterruptedException {
		String urlAfterCompleteData="http://devops-deve.kifiya.et:8082/mfs-admin/agents/confirmAgentSignup";
		WebElement firstName = driver.findElement(By.id("firstName"));
		WebElement fatherName = driver.findElement(By.id("fatherName"));
		WebElement grandFathersName = driver.findElement(By.id("grandFathersName"));
		WebElement phoneNumber = driver.findElement(By.id("phoneNumber"));
		WebElement birthDate = driver.findElement(By.id("dob"));
		int age=18;
		String day="16";
		
		Select gender=new Select(driver.findElement(By.id("gender")));
		Select agentType=new Select(driver.findElement(By.id("agent-type")));
		Select businessType=new Select(driver.findElement(By.id("type")));
		Select region=new Select(driver.findElement(By.id("region")));
		Select subcity=new Select(driver.findElement(By.id("subcity")));
		Select woreda=new Select(driver.findElement(By.id("woreda")));
		Select kebele=new Select(driver.findElement(By.name("addressId")));
		WebElement houseNumber=driver.findElement(By.id("houseNumber"));
		Select neighbourhood=new Select(driver.findElement(By.xpath("//select[@name='neighbourhood']")));
		WebElement primaryAddress=driver.findElement(By.id("primaryAddress"));
		Select identificationType=new Select(driver.findElement(By.id("identificationType")));
		WebElement uploadID=driver.findElement(By.id("idFile"));
		WebElement licenceFile=driver.findElement(By.id("licenceFile"));
		WebElement tinNumber=driver.findElement(By.id("tinNumber"));
		WebElement submit=driver.findElement(By.xpath("//button[@class='btn btn-warning m-b-10 m-l-5']"));
		
		
		firstName.sendKeys("Rediat");
		Thread.sleep(200);
		fatherName.sendKeys("Tsegaberehan");
		Thread.sleep(200);
		grandFathersName.sendKeys("Yemane");
		Thread.sleep(200);
		phoneNumber.sendKeys("0911300247");
		Thread.sleep(200);

		birthDate.click();
		Thread.sleep(2000);

		for(int i=0;i<age;i++) {
			driver.findElement(By.xpath("//a[@class='dtp-select-year-before']")).click();
			Thread.sleep(200);
		}
		driver.findElement(By.linkText(day)).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//button[@class='dtp-btn-ok btn btn-flat btn-sm']")).click();
		Thread.sleep(200);
		gender.selectByVisibleText("MALE");
		Thread.sleep(200);
		agentType.selectByVisibleText("AIRTIME");
		Thread.sleep(200);
		businessType.selectByVisibleText("KIOSK");
		Thread.sleep(200);
		region.selectByVisibleText("Addis Ababa");
		Thread.sleep(200);
		subcity.selectByVisibleText("Bole");
		Thread.sleep(200);
		woreda.selectByVisibleText("Woreda 01");
		Thread.sleep(200);
		kebele.selectByIndex(1);
		Thread.sleep(200);
		houseNumber.sendKeys("New");
		Thread.sleep(200);
		neighbourhood.selectByIndex(1);
		Thread.sleep(200);
		primaryAddress.click();
		Thread.sleep(200);
		identificationType.selectByIndex(1);
		Thread.sleep(200);
		uploadID.sendKeys("D:\\GCS\\LOGO\\Kifiya New.png");
		Thread.sleep(200);
		licenceFile.sendKeys("D:\\GCS\\LOGO\\Kifiya New.png");
		Thread.sleep(200);
		tinNumber.sendKeys("000078963");
		Thread.sleep(200);
		Actions actions = new Actions(driver); 
		actions.moveToElement(submit);
		actions.perform(); 
		submit.click();
		
		Thread.sleep(200);		
		String url=driver.getCurrentUrl();
		if(url.equals(urlAfterCompleteData)) {
			System.out.println("Agent Detail Mandatory fields Submited successfully - Passed");
			return true;
		}
		else {
			System.out.println("Agent Detail Mandatory fields Submited Unsuccessfully - Failed");
			return false;
		}
	}
	
	public boolean confirmAgentRegisteration() throws InterruptedException {
		String currentURL;
		String confirmedURL="http://devops-deve.kifiya.et:8082/mfs-admin/agents/completeAgentSignUp";
		Thread.sleep(3000);	
		
		//Scroll down to page slowly
		try {
			if(	driver.findElement(By.xpath("//span[@class='form-error']")).getText()!="") {
				System.out.println("Agent Mobile number has Already been used for Agent Registration, please try another mobile number: Agent Registeration Unsuccessful - Failed");
				return false;
				}
			}
		catch(Exception e) {}	
				
		WebElement sumbit=driver.findElement(By.xpath("//button[@class='btn btn-warning m-b-10 m-l-5']"));
		Actions scroll=new Actions(driver);
		scroll.moveToElement(sumbit);
		scroll.perform();
		sumbit.click();

		currentURL=driver.getCurrentUrl();
		if(currentURL.equals(confirmedURL)) {
			
			System.out.println("Submit Agent Detail Saved Successful - Passed");
			return true;
		}
		else {
			System.out.println("Submit Agent detail Saved Unsuccessful - Failed");
			return false;
		}
	}
	public void scrollDown() throws InterruptedException {
		Thread.sleep(5000);				
		for(int i=0;i<4;i++)
		{
			JavascriptExecutor js=(JavascriptExecutor)driver;
			js.executeScript("scrollBy(0,625)"); //Scrolls the page by a quarter every time. Full page down scroll takes 2500 i.e. scrollBy(0,2500)
			Thread.sleep(500);
		}
	}
	public void logout() throws InterruptedException {
		Thread.sleep(2000);
		try {
		driver.findElement(By.xpath("//img[@class='profile-pic']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='main-wrapper']/div[1]/nav/div[2]/ul[2]/li[3]/div/ul/li[2]/a")).click();
		Thread.sleep(2000);
		}
		catch (Exception e) {}
		driver.close();
	}

	public static void main(String[] args) throws Exception {

		AdminLogin admin = new AdminLogin();
		System.out.flush();
		Thread.sleep(3000);
		boolean loginSuccess = admin.login();
		boolean agentDetail=false;

		Thread.sleep(5000);
		if (loginSuccess) {
			admin.openRegisterAgent();
			agentDetail=admin.agentRegisteration();
			admin.scrollDown();
			if(agentDetail)	{
				admin.confirmAgentRegisteration();
				admin.scrollDown();	
			}
			else
				System.out.println("Save Agent detail Unsuccessful - Failed");
			
		}
		else
			System.out.println("Login Unsuccessful - Failed");
		Thread.sleep(5000);
		admin.logout();
	}
}
