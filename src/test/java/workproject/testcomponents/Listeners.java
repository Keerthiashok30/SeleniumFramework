package workproject.testcomponents;


import java.util.HashMap;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import workproject.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener
{

	ExtentTest test;
	ExtentReports extent =ExtentReporterNG.getReportObject();
    @Override
    public void onTestStart(ITestResult result) {
        HashMap<String,String> input = (HashMap<String,String>) result.getParameters()[0];
        String testCaseName = input.get("testCaseName");
        test = extent.createTest(testCaseName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, "TestCase Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.log(Status.FAIL, "TestCase Failed");
        test.fail(result.getThrowable());
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
            String filePath = getScreenshot(result.getMethod().getMethodName(), driver);
            test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String reason = "";
        if (result.getThrowable() != null) {
            reason = result.getThrowable().getMessage();
        }

        if (reason.isEmpty()) {
            test.skip("Test Skipped: " + result.getMethod().getMethodName());
        } else {
            test.skip("Test Skipped: " + result.getMethod().getMethodName() + " | Reason: " + reason);
        }

        System.out.println("Skipped Test: " + result.getMethod().getMethodName() + " | Reason: " + reason);
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
