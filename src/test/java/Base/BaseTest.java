// Declares the package for the base test class
package Base;

import Pages.DashboardPage;
import Pages.LoginPage;
import Pages.ProfilePage;
import Reports.ExtentReportManager;
import Utilities.DriverFactory;
import Utilities.ScreenshotUtils;
import com.aventstack.extentreports.ExtentTest;
import io.appium.java_client.AppiumDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

// Defines the BaseTest class which serves as a base class for all test classes
public class BaseTest {

    // Declares a protected AppiumDriver variable that can be accessed by subclasses
    protected AppiumDriver driver;
    // Declares a protected Properties variable to store configuration settings accessible by subclasses
    protected Properties config;
    protected LoginPage loginPage;
    protected DashboardPage dashboardPage;
    protected ProfilePage profilePage;
    protected ExtentTest extentTest;

    // Method that sets up the driver and loads configuration before running tests
    @BeforeClass
    public void setUpAndLogin() throws IOException {
        // Initializes a new Properties object to store configuration data
        config = new Properties();
        // Creates a FileInputStream to read the configuration properties file from the resources directory
        FileInputStream fis = new FileInputStream(
                System.getProperty("user.dir") + "/src/test/resources/configs/config.properties");
        // Loads the properties from the configuration file into the Properties object
        config.load(fis);

        // Initializes the AppiumDriver using the DriverFactory with the loaded configuration
        DriverFactory.initDriver(config);
        // Retrieves the initialized driver from the DriverFactory and assigns it to the driver variable
        driver = DriverFactory.getDriver();

        loginPage = new LoginPage(driver, config);
        LoginToNdosiAutomation();

        dashboardPage = new DashboardPage(driver,config);
        profilePage  = new ProfilePage(driver,config);
    }

    @BeforeMethod
    public void createReportTest(Method method)
    {
        extentTest = ExtentReportManager.getExtentReports().createTest(method.getName());
        extentTest.info("Test execution started");
    }

    @AfterMethod(alwaysRun = true)
    public void recordTestResult(ITestResult result)
    {
        String testName = result.getMethod().getMethodName();
        try{
            if(result.getStatus() == ITestResult.FAILURE){
                if(result.getThrowable()!=null)
                {
                    extentTest.fail(result.getThrowable());
                }
                else{
                    extentTest.fail("Test failed");
                }

                AppiumDriver driver = DriverFactory.getDriver();
                if(driver!=null){
                    ScreenshotUtils.captureScreenShot(driver,testName);
                }
                File originalScreenshot = new File("screenshots/"+testName+".png");
                if (originalScreenshot.exists()){
                    Path reportScreenshotFolder = Path.of("target","reports","screenshots");
                    Files.createDirectories(reportScreenshotFolder);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }








    public void LoginToNdosiAutomation() {
        loginPage.clickBurgerMenuButton();
        ScreenshotUtils.captureScreenShot(driver,"Burger Menu Clicked");

        loginPage.clickSignInButton();
        ScreenshotUtils.captureScreenShot(driver,"SignIn button Clicked");

        loginPage.enterEmail(config.getProperty("email"));
        ScreenshotUtils.captureScreenShot(driver,"Email Entered");

        loginPage.enterPassword(config.getProperty("password"));
        ScreenshotUtils.captureScreenShot(driver,"Password Entered");

        loginPage.clickLoginButton();
        ScreenshotUtils.captureScreenShot(driver,"Login button Clicked");

        Assert.assertTrue(loginPage.isLoginSuccess(),"Login was unsuccessfully"
                );
    }

    @AfterClass
    public void tearDown(){
        DriverFactory.quitDriver();
    }


}
