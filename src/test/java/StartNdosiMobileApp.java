import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class StartNdosiMobileApp {

    public static AndroidDriver driver;

    @Before
    public void setup() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("app", System.getProperty("user.dir") + "/src/main/Apps/app-qa-release.apk");

        driver= new AndroidDriver(new URL("http://172.20.10.7:4723/"), capabilities);
    }

    @Test
    public void lauchNdosiQAApp() throws MalformedURLException {
        driver.findElement(By.id("com.ndosi.mobile:id/btnClose")).click();
    }

    @After
    public void quitApp(){
        System.out.println("App successfully started");

    }





}
