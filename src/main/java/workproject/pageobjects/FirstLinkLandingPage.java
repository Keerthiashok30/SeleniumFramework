package workproject.pageobjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class FirstLinkLandingPage 
{
	
	WebDriver driver;
	
	public FirstLinkLandingPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

    @FindBy(tagName = "body")
    private WebElement wordSearch;

    
    public void wordCheck(String word) 
    {
    String pageSource = wordSearch.getText().toLowerCase().replaceAll("\\s+", " ");
    word = word.toLowerCase();
    int count = pageSource.split(word, -1).length - 1;
    System.out.println("The word '" + word + "' appears " + count + " times on the page.");
    //SoftAssert softAssert = new SoftAssert();
    Assert.assertTrue(pageSource.contains(word),"The page does NOT contain the text: " + word);
    System.out.println("The page contains the text: " + word);
    }
}
