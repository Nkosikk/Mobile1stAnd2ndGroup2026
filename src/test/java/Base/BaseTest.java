// Declares the package for the base test class
package Base;

import Pages.DashboardPage;
import Pages.LoginPage;
import Pages.ProfilePage;
import Utilities.DriverFactory;
import io.appium.java_client.AppiumDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.IOException;
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

    public void LoginToNdosiAutomation() {
        loginPage.clickBurgerMenuButton();
        loginPage.clickSignInButton();
        loginPage.enterEmail(config.getProperty("email"));
        loginPage.enterPassword(config.getProperty("password"));
        loginPage.clickLoginButton();

        Assert.assertTrue(loginPage.isLoginSuccess(),"Login was unsuccessfully"
                );
    }

    @AfterClass
    public void tearDown(){
        DriverFactory.quitDriver();
    }


}
