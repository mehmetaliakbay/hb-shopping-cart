package driver;

import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.net.MalformedURLException;

public class DriverFactory {
    public static java.lang.ThreadLocal<WebDriver> webdriver = new java.lang.ThreadLocal<>();
    private final Driver webDriverFactory = new Driver();

    @BeforeScenario
    public void setUpBrowser()  {
        webDriverFactory.setUp();
        webdriver.set(webDriverFactory.getDriver());

    }

    public static WebDriver getDriver(){
        return webdriver.get();
    }


    @AfterScenario
    public void tearDown() {
        if (DriverFactory.getDriver() == null) {
            return;
        }
        DriverFactory.getDriver().quit();

    }

}
