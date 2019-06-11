package TestCases;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Listeners(TestCases.ExceptionHandler.class)//This can also be added into <suite> tag's <listeners><listner class=""/></listeners> a recommended approach
public class LoginDataProvider {
	public WebDriver driver;
	public String baseURL="http://devops-deve.kifiya.et:8082/mfs-admin";
	SoftAssert softAssert=new SoftAssert();
	
	@BeforeClass
	public void start() {
		System.setProperty("webdriver.chrome.driver", "D:\\GCS\\Selenium Webdrivers\\Version 74\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		Reporter.log("=====Browser opened and maximized=====", true);
	}
	@AfterTest
	public void end() throws InterruptedException {
		Thread.sleep(3000);
		driver.close();
		Reporter.log("=====Browser closed=====", true);
	}
	
	@Test(dataProvider = "LoginCredentials")
	public void login(String username,String password) throws InterruptedException {
		Thread.sleep(2000);
		driver.get(baseURL);		
		try {
			driver.findElement(By.id("username")).sendKeys(username);
		}
		catch (Exception e) {
			driver.findElement(By.id("username")).clear();
		}
		try {
			driver.findElement(By.id("password")).sendKeys(password);
		}
		catch (Exception e) {
			driver.findElement(By.id("password")).clear();
		}
		
		driver.findElement(By.xpath("//button[@class='form-control btn-block btn-lg primary-button btn']")).click();
		//Assert.assertEquals(driver.getCurrentUrl(), "http://devops-deve.kifiya.et:8082/mfs-admin/dashboard?loggedInUserName=mfs&appicationVersion=Build+Version%3A+1.1.0");
		softAssert.assertEquals(driver.getCurrentUrl(), "http://devops-deve.kifiya.et:8082/mfs-admin/dashboard?loggedInUserName=mfs&appicationVersion=Build+Version%3A+1.1.0");
		softAssert.assertAll();
	}
	
	@DataProvider(name = "LoginCredentials")
	public String[][] loginInfo() throws InvalidFormatException, IOException {
		FileInputStream f=new FileInputStream("D:\\GCS\\MFS\\DigitalBanking\\Login.xlsx");
		XSSFWorkbook workbook=new XSSFWorkbook(f);
		XSSFSheet sheet=workbook.getSheetAt(0);
		XSSFRow rows=sheet.getRow(0);
		int columnCount=rows.getLastCellNum();
		int rowCount=sheet.getLastRowNum()+1;		
						
		String [][] loginData=new String[rowCount][columnCount];
		
		for(int i=1;i<rowCount;i++)
			for(int j=0;j<columnCount;j++)
				loginData[i][j]=sheet.getRow(i).getCell(j).toString();		
		
		workbook.close();
		return loginData;		
	}
}
