package driver;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Driver {
    public WebDriver driver;

    public void setUp() {
        String browser = System.getenv("BROWSER");
        browser = (browser == null) ? "CHROME" : browser;

        switch (browser) {
            case "IE":
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
            case "FIREFOX":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            default:
                WebDriverManager.chromedriver().setup();

                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments(
                        "--disable-gpu",
                        "--disable-notifications",
                        "--window-size=1920,1080",
                        "--ignore-certificate-errors",
                        "--disable-extensions",
                        "--no-sandbox",
                        "--disable-dev-shm-usage");

                driver = new ChromeDriver(chromeOptions);
        }

        String app_url = System.getenv("APP_URL");
        driver.manage().window().maximize();
        driver.get(app_url + "/");
    }

    public WebDriver getDriver() {
        return driver;
    }


}
