package workproject.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage 
{
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//input[@id='searchbox_input']")
    private WebElement search;
	
	public void searchBox(String searchWord)
	{
		search.sendKeys(searchWord);
		search.submit();
	}
	
	public void goTo()
	{
		driver.get("https://duckduckgo.com");
	}
}
