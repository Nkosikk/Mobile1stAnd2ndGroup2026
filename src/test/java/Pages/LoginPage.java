// Declares the package for this Page Object Model class
package Pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Properties;

public class LoginPage {

    // Declares an AppiumDriver variable to control the mobile application
    AppiumDriver driver;
    // Declares a Properties variable to store configuration settings
    Properties config;
    // Declares a WebDriverWait variable for explicit waits on elements
    WebDriverWait wait;

    // Constructor that initializes the LoginPage with an AppiumDriver and configuration Properties
    public LoginPage(AppiumDriver driver, Properties config)
    {
        // Assigns the passed AppiumDriver to the instance variable
        this.driver = driver;
        // Assigns the passed Properties to the instance variable
        this.config = config;
        // Creates a WebDriverWait instance with a timeout of 20 seconds for explicit waits
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        // Initializes page elements using PageFactory and AppiumFieldDecorator for Appium support
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }

    // Defines a By locator for the burger menu button in native applications
    private By burgerMenuButtonNative = By.xpath("//android.widget.Button");
    // Defines a By locator for the burger menu button in web applications
    private By burgerMenuButtonWeb = By.xpath("//button[@class='nav-burger']");

    // End of LoginPage class
}
