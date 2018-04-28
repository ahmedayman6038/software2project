package software2project.E2ETest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TC8_AddProductToStore_InvalidData {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "E:\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:8080/login");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
		driver.findElement(By.name("email")).clear();
		driver.findElement(By.name("email")).sendKeys("ali@gmail.com");
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys("456");
		driver.findElement(By.name("submit")).click();
		driver.findElement(By.xpath("//a[@class='btn btn-default'][contains(text(),'Products')]")).click();
		driver.findElement(By.xpath("//a[@class='btn btn-default'][contains(text(),'Add Product')]")).click();
		Select product = new Select(driver.findElement(By.name("productId")));
		product.selectByIndex(0);
		Select brand = new Select(driver.findElement(By.name("brand")));
		brand.selectByIndex(0);
		driver.findElement(By.name("price")).clear();
		driver.findElement(By.name("price")).sendKeys("950");
		driver.findElement(By.name("quantity")).clear();
		driver.findElement(By.name("quantity")).sendKeys("15");
		driver.findElement(By.name("submit")).click();
	}

}
