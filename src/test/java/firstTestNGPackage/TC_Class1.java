package firstTestNGPackage;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TC_Class1 
{
	public String baseUrl = "http://demo.guru99.com/test/newtours/";
	WebDriver driver=null;
	
	@BeforeTest
	  public void initiateBrowser()
	  {
		  System.setProperty("webdriver.chrome.driver", "C:\\Users\\kagrawa\\eclipse-workspace\\TestNGFramework\\chromedriver.exe"); 
		  driver = new ChromeDriver(); 
//		  driver.get(baseUrl);
//		  System.out.println("Before test done");
//		  String handle = driver.getWindowHandle();
//		  driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
//		  driver.switchTo().frame("gdpr-consent-notice");
//		  driver.findElement(By.id("denyAll")).click();
//		  driver.switchTo().activeElement();
//		  driver.findElement(By.xpath("//*[@id=\"mat-dialog-0\"]/ng-component/app-theme/div/div/div[2]/button[2]")).click();	  
//		  driver.switchTo().window(handle);
	  }

	
	@Test(groups = { "bonding", "strong_ties" })	
        public void tc01LaunchURL() {	
        driver.manage().window().maximize();	
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);	
        driver.get("http://www.demo.guru99.com/V4/");
        String handle = driver.getWindowHandle();
		  driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
		  driver.switchTo().frame("gdpr-consent-notice");
		  driver.findElement(By.id("denyAll")).click();
		  driver.switchTo().activeElement();
		  driver.findElement(By.xpath("//*[@id=\"mat-dialog-0\"]/ng-component/app-theme/div/div/div[2]/button[2]")).click();	  
		  driver.switchTo().window(handle);
    }
	
	@Test(groups = { "bonding" })	
    public void tc02VerifyLaunchPage() {	
        Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Guru99 Bank']")).isDisplayed(),	
        "Home Page heading is not displayed");	
        System.out.println("Home Page heading is displayed");	
    }
	
	@Test(groups = { "bonding", "strong_ties" })	
    public void tc03EnterCredentials() {	
        driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr28642");	
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("ydAnate");	
        driver.findElement(By.xpath("//input[@name='btnLogin']")).click();	
    }
	@Test(groups = { "strong_ties" })	
    public void tc04VerifyLoggedInPage() {	
        Assert.assertTrue(driver.findElement(By.xpath("//td[contains(text(),'Manger Id')]")).isDisplayed(),	
                "Manager ID label is not displayed");	
        System.out.println("Manger Id label is displayed");	
    }
	
	@Test(groups = { "bonding" })	
    public void tc05VerifyHyperlinks() {	
        Assert.assertTrue(driver.findElement(By.xpath("//a[@href='addcustomerpage.php']")).isEnabled(),	
                "New customer hyperlink is not displayed");	
        System.out.println("New customer hyperlink is displayed");	

        Assert.assertTrue(driver.findElement(By.xpath("//a[@href='FundTransInput.php']")).isEnabled(),	
                "Fund Transfer hyperlink is not displayed");	
        System.out.println("Fund Transfer hyperlink is displayed");
    }	
}
