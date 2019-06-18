package TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestCases.ExceptionHandler.class)
public class LinkCheck {	
	public WebDriver driver;
	public String url;	
	
	@BeforeClass
	public void login() {
		System.setProperty("webdriver.chrome.driver", "D:\\GCS\\Selenium Webdrivers\\Version 74\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://devops-deve.kifiya.et:8082/mfs-admin/");
		
		driver.findElement(By.id("username")).sendKeys("mfs");		
		driver.findElement(By.id("password")).sendKeys("system");		
		driver.findElement(By.xpath("//button[@class='form-control btn-block btn-lg primary-button btn']")).click();		
	}
	@Test(priority = 1)
	public void mangmenAgentstLink() throws InterruptedException {
		clickDashboard();
		Thread.sleep(1000);
		driver.findElement(By.linkText("Manage Agents")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://devops-deve.kifiya.et:8082/mfs-admin/dashboard");
		
	}
	@Test(priority = 2,dependsOnMethods ="mangmenAgentstLink" )
	public void agentListLink() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.linkText("Agent List")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://devops-deve.kifiya.et:8082/mfs-admin/agents/listAgents");
	}
	@Test(priority = 3,dependsOnMethods ="mangmenAgentstLink" )
	public void staffAgentListLink() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.linkText("Staff Agent List")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://devops-deve.kifiya.et:8082/mfs-admin/agents/listStaffAgents");
	}
	@Test(priority = 4,dependsOnMethods ="mangmenAgentstLink" )
	public void registerAgentLink() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.linkText("Register")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://devops-deve.kifiya.et:8082/mfs-admin/agents/signUpAgents");
	}
	@Test(priority = 5)
	public void manageMerchantsLink() throws InterruptedException {		
		clickDashboard();
		Thread.sleep(1000);
		driver.findElement(By.linkText("Manage Merchants")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://devops-deve.kifiya.et:8082/mfs-admin/dashboard");
	}
	@Test(priority = 6,dependsOnMethods = "manageMerchantsLink")
	public void merchantListLink() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.linkText("list")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://devops-deve.kifiya.et:8082/mfs-admin/organization/list");
	}
	@Test(priority = 7,dependsOnMethods = "manageMerchantsLink")
	public void registerMerchantLink() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.linkText("Register")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://devops-deve.kifiya.et:8082/mfs-admin/organization/create");
	}
	@Test(priority = 8)
	public void manageCustomersLink() throws InterruptedException {
		clickDashboard();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Manage Customers")).click();		
		Assert.assertEquals(driver.getCurrentUrl(), "http://devops-deve.kifiya.et:8082/mfs-admin/dashboard");
	}
	@Test(priority = 9,dependsOnMethods = "manageCustomersLink")
	public void listCustomersLink() throws InterruptedException {		
		Thread.sleep(2000);
		driver.findElement(By.linkText("List")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://devops-deve.kifiya.et:8082/mfs-admin/customer/listCustomers");
	}
	@Test(priority = 10,dependsOnMethods = "manageCustomersLink")
	public void registerCustomersLink() throws InterruptedException {		
		Thread.sleep(2000);
		driver.findElement(By.linkText("Register")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://devops-deve.kifiya.et:8082/mfs-admin/customer/signUpCustomer");
	}
	@Test(priority = 11)
	public void airtimeLink() throws InterruptedException {
		clickDashboard();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Airtime")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://devops-deve.kifiya.et:8082/mfs-admin/dashboard");
	}
	@Test (priority = 12,dependsOnMethods = "airtimeLink")
	public void startBulkAirtimeLink() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.linkText("Start Bulk Airtime")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://devops-deve.kifiya.et:8082/mfs-admin/airtime/bulkAirtimeStart");
	}
	
	@Test (priority = 13)
	public void financialSettlementLink() throws InterruptedException {
		clickDashboard();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Financial Settlement")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://devops-deve.kifiya.et:8082/mfs-admin/dashboard");
	}
	@Test (priority = 14,dependsOnMethods = "financialSettlementLink")
	public void merchantBillSettlementLink() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.linkText("Merchant Bill Settlement")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://devops-deve.kifiya.et:8082/mfs-admin/finance/settlementStart");
	}
	@Test (priority = 15,dependsOnMethods = "financialSettlementLink")
	public void agentCommissionTransactionLink() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.linkText("Agent Commission Transaction")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://devops-deve.kifiya.et:8082/mfs-admin/finance/getAgentCommissionTransaction");
	}
	@Test (priority = 16,dependsOnMethods = "financialSettlementLink")
	public void transactionExceptionsLink() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.linkText("Transaction Exceptions")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://devops-deve.kifiya.et:8082/mfs-admin/finance/getTransactionExceptions");
	}
	
	@Test (priority = 17)
	public void walletFundsLink() throws InterruptedException {
		clickDashboard();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Wallet Funds")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://devops-deve.kifiya.et:8082/mfs-admin/dashboard");
	}
	@Test(priority = 18,dependsOnMethods = "walletFundsLink")
	public void fundWalletLink() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.linkText("Fund Wallet")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://devops-deve.kifiya.et:8082/mfs-admin/finance/fundAgentAccount");
	}
	@Test(priority = 19)
	public void manageTransactionRuleLink() throws InterruptedException {
		clickDashboard();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Manage Transaction Rule")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://devops-deve.kifiya.et:8082/mfs-admin/dashboard");		
	}
	@Test(priority = 20,dependsOnMethods = "manageTransactionRuleLink")
	public void listTransactionRuleLink() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.linkText("List")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://devops-deve.kifiya.et:8082/mfs-admin/admin/ruleList");
	}
	@Test(priority = 21,dependsOnMethods = "manageTransactionRuleLink")
	public void registerTransactionRuleLink() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.linkText("Register")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://devops-deve.kifiya.et:8082/mfs-admin/admin/createRule");
	}
	@Test(priority = 22)
	public void reportsLink() throws InterruptedException {
		clickDashboard();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Reports")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://devops-deve.kifiya.et:8082/mfs-admin/dashboard");
	}
	//All child reports link escaped
	@Test(priority = 23)
	public void manageAdminUsersLink() throws InterruptedException {
		clickDashboard();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Manage Admin Users")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://devops-deve.kifiya.et:8082/mfs-admin/dashboard");
	}
	@Test(priority = 24,dependsOnMethods = "manageAdminUsersLink")
	public void usersLink() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.linkText("Users")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://devops-deve.kifiya.et:8082/mfs-admin/admin/adminUserList");
	}
	@Test(priority = 24,dependsOnMethods = "manageAdminUsersLink")
	public void rolesLink() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.linkText("Roles")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://devops-deve.kifiya.et:8082/mfs-admin/admin/rolesList");
	}
	
	@Test(priority = 25)
	public void changePasswordLink() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.xpath("//img[@class='profile-pic']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"main-wrapper\"]/div[1]/nav/div[2]/ul[2]/li[3]/div/ul/li[1]/a")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://devops-deve.kifiya.et:8082/mfs-admin/changePassword");
	}
	public void clickDashboard() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.linkText("Dashbaord")).click();
	}
	@AfterClass
	public void logout() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.xpath("//img[@class='profile-pic']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"main-wrapper\"]/div[1]/nav/div[2]/ul[2]/li[3]/div/ul/li[2]/a")).click();
		url=driver.getCurrentUrl();
		Thread.sleep(2000);
		driver.close();			
	}

}
