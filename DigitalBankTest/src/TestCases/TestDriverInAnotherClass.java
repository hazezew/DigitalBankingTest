package TestCases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestDriverInAnotherClass extends LoginDataProvider {

	@Test
	public void checkDriverReuse() throws InterruptedException {
		Thread.sleep(2000);
		driver.get("http://devops-deve.kifiya.et:8082/mfs-admin");
		driver.findElement(By.id("username")).sendKeys("mfs");
		driver.findElement(By.id("password")).sendKeys("system");
		driver.findElement(By.xpath("//button[@class='form-control btn-block btn-lg primary-button btn']")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://devops-deve.kifiya.et:8082/mfs-admin/dashboard?loggedInUserName=mfs&appicationVersion=Build+Version%3A+1.1.0");
	}
}
