package workproject.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import workproject.pageobjects.FirstLinkLandingPage;
import workproject.pageobjects.SearchEnginePage;
import workproject.testcomponents.BaseTest;
import workproject.testcomponents.Retry;

public class LandingTest extends BaseTest
 
{
	@Test(dataProvider="getData",retryAnalyzer=Retry.class)
	public void isWordPresent(HashMap<String,String> input)
	{
		 try {
				landingPage.searchBox(input.get("searchWord"));
				SearchEnginePage searchEngine=new SearchEnginePage(driver);
				searchEngine.clickFirstLink();
				FirstLinkLandingPage linkLandingPage=new FirstLinkLandingPage(driver);
				linkLandingPage.wordCheck(input.get("wordCheck"));
			 } catch (Exception e) 
			 {
		       e.printStackTrace();
			 } finally {
		     
			 			}
}

	@DataProvider
	public Object[][] getData() throws IOException
	{
		 List<HashMap<String,String>> data= getJsonDataToMap("//src//test//java//workproject//data//wordExists.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}

}
