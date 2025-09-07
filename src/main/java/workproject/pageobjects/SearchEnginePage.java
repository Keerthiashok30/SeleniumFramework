package workproject.pageobjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchEnginePage 
{
	WebDriver driver;
	
	public SearchEnginePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//ol[contains(@class,'react-results--main')]/li//a")
    private List<WebElement> allLinks;

    public void clickFirstLink()
    {
    	if (!allLinks.isEmpty()) {
            // Click the first link
            WebElement firstLink = allLinks.get(0);
            System.out.println("First link text: " + firstLink.getText());
            System.out.println("First link URL: " + firstLink.getAttribute("href"));
            firstLink.click();
        } else {
            System.out.println("No links found on the page!");
        }


    }
	
}
