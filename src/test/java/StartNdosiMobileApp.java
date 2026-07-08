import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import java.net.MalformedURLException;
import java.net.URI;

public class StartNdosiMobileApp {

    public static AndroidDriver driver;

    @Before
    public void setup() throws MalformedURLException {

        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setAutomationName("UiAutomator2")
                .setApp(System.getProperty("user.dir") + "/src/main/Apps/app-qa-release.apk");

        driver = new AndroidDriver(URI.create("http://127.0.0.1:4723/").toURL(), options);
    }

    @Test
    public void lauchNdosiQAApp() throws InterruptedException {
        Thread.sleep(5000); // Wait for the app to load
        driver.findElement(By.xpath("//android.widget.Button")).click();
    }

    @After
    public void quitApp(){
        if (driver != null) {
            driver.quit();
        }
    }





}
