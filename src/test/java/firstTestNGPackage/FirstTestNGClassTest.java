package firstTestNGPackage;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FirstTestNGClassTest {

   public String baseUrl = "http://demo.guru99.com/test/newtours/";
   String userDir = System.getProperty("user.dir") ;
   WebDriver driver=null;	

  @Parameters("Browser")
  @BeforeTest 
  public void initiateBrowser(String browserName)
  {
	//  System.out.println(userDir);
	//  System.out.println(userDir+"\\WebDrivers\\chromedriver.exe");
	  if(browserName.equalsIgnoreCase("edge"))
	  {
		  WebDriverManager.edgedriver().setup();
		  driver= new EdgeDriver();
	  }
	  else if(browserName.equalsIgnoreCase("chrome"))
	  {
		  WebDriverManager.chromedriver().setup();
		  ChromeOptions options = new ChromeOptions();
		  options.addArguments("--no-sandbox");
		  options.addArguments("--disable-dev-shm-usage");
		  options.addArguments("--headless");
		  driver = new ChromeDriver(options);
	  }
	  else
	  {
		  System.out.println("Either choose chrome or edge");
	  }
	  driver.get(baseUrl);
	  driver.manage().window().maximize();
	  System.out.println("Before test done");
	  String handle = driver.getWindowHandle();
	  driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
	  driver.switchTo().frame("gdpr-consent-notice");
	  driver.findElement(By.id("denyAll")).click();
	  driver.switchTo().activeElement();
	  driver.findElement(By.xpath("//*[@id=\"mat-dialog-0\"]/ng-component/app-theme/div/div/div[2]/button[2]")).click();	  
	  driver.switchTo().window(handle);
  }
  
  @BeforeMethod
  public void verifyHomepageTitle()
  {
     String actual= driver.getTitle();
	 AssertJUnit.assertEquals("Welcome: Mercury Tours", actual);
	 System.out.println("Before method done");
  }
  
  @Test(priority = 0)
  public void register(){
      driver.findElement(By.linkText("REGISTER")).click() ;
      String expected = "Register: Mercury Tours";
      String actual = driver.getTitle();
      System.out.println("test priority 0");
      AssertJUnit.assertEquals(actual, expected);
  }
  
  @Test(priority = 1)
  public void support() {
        driver.findElement(By.linkText("SUPPORT")).click() ;
        String expected = "Under Construction: Mercury Tours";
        String actual = driver.getTitle();
        System.out.println("test priority 1");
        AssertJUnit.assertEquals(actual, expected);
  }
  
  @AfterMethod
  public void goBackToHomepage ( ) {
        driver.findElement(By.linkText("Home")).click() ;
        System.out.println("after method done");
  }
   
  @AfterTest
  public void terminateBrowser(){
	  System.out.println("after test");
      driver.close();
  }
  
  
}
