package Pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Properties;

public class LoginPage {

    AppiumDriver driver;
    Properties config;
    WebDriverWait wait;

    public LoginPage(AppiumDriver driver, Properties config)
    {
        this.driver = driver;
        this.config = config;
        //implicit wait
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }

    private By burgerMenuButtonNative = By.xpath("//android.widget.Button");
    private By burgerMenuButtonWeb = By.xpath("//button[@class='nav-burger']");

}
