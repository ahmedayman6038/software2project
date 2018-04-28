package software2project.E2ETest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TC4_TestRegisterPage_InvalidData {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "E:\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:8080/register");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
		driver.findElement(By.name("name")).clear();
		driver.findElement(By.name("name")).sendKeys("ali123");
		driver.findElement(By.name("email")).clear();
		driver.findElement(By.name("email")).sendKeys("ali@gmail.com");
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys("456456");
		Select type = new Select(driver.findElement(By.name("type")));
		type.selectByIndex(1);
		driver.findElement(By.name("submit")).click();
	}

}
