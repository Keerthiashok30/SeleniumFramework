package workproject.testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import workproject.pageobjects.LandingPage;

public class BaseTest
{
	public WebDriver driver;
	public LandingPage landingPage;
	public Properties prop=new Properties();

	public WebDriver initializeDriver() throws IOException
	{
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"//src//main//java//workproject//resources//GlobalData.properties");
		prop.load(fis);
		String browserName = prop.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("chrome"))
		{
		WebDriverManager.chromedriver().setup();
		driver= new ChromeDriver();
		System.out.println("chrome");
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			  WebDriverManager.firefoxdriver().setup();
		      driver = new FirefoxDriver();
			  System.out.println("firefox");
		}
		else if(browserName.equalsIgnoreCase("edge"))
		{
	        WebDriverManager.edgedriver().setup();
	        driver = new EdgeDriver();
			System.out.println("edge");
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		return driver;
	}
		
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
		{
			String jsonContent= FileUtils.readFileToString(new File(System.getProperty("user.dir")+filePath),StandardCharsets.UTF_8);
			ObjectMapper mapper= new ObjectMapper();
		    List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {});
			return data;
		}
	
	public String getScreenshot(String testCaseName,WebDriver driver) throws IOException
		{
			TakesScreenshot ts=(TakesScreenshot)driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			File file=new File(System.getProperty("user.dir")+"//reports//" + testCaseName + ".png");
			FileUtils.copyFile(source, file);
			return System.getProperty("user.dir")+"//reports//" + testCaseName + ".png";
		}
	

	
	@BeforeMethod
	public LandingPage launchApp() throws IOException
	{
		 driver= initializeDriver();
		 landingPage=new LandingPage(driver);
		 landingPage.goTo();
		 return landingPage;
	}
	
	@AfterMethod
	public void tearDown()
	{
		 driver.quit();
	}

}
