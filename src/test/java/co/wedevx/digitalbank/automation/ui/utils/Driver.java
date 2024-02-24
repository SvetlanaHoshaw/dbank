package co.wedevx.digitalbank.automation.ui.utils;

import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
//explanation about this class in teh Google Sheet > Singleton tab

public class Driver {
    private static WebDriver driver;

    private Driver() {
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            //so now we are saying that depends on what browser is specified in ConfigReader, those browser will be open
            String browser = ConfigReader.getPropertiesValue("digitalbank.browser");

            switch (browser.toLowerCase()) {
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case "safari":
                    driver = new SafariDriver();
                    break;
                case "ie":
                    WebDriverManager.iedriver().setup();
                    driver = new InternetExplorerDriver();
                    break;
                case "headless":
                    ChromeDriverManager.getInstance(DriverManagerType.CHROME).setup();
                    //ChromeOptions is a class that allows you to add some options to your Driver
                    ChromeOptions options = new ChromeOptions();
                    //next row is optional, te set size of the window, this is standard size
                    options.addArguments("--window-size=1920,1080");
                    //next row is to disable all extantions that added to chrome
                    options.addArguments("disable-extensions");
                    //next row to not use automation extension
                    options.setExperimentalOption("useAutomationExtension", false);
                    options.addArguments("--proxy-server='direct://'");
                    options.addArguments("--proxy-bypass-list=*");
                    options.addArguments("--start-maximized");
                    //run on the background
                    options.addArguments("--headless");
                    driver = new ChromeDriver(options);
                    break;
            /*    case "saucelabs":
            in this case we are taking values from properties file
                    String platform = ConfigReader.getPropertiesValue("digitalbank.saucelabs.platform");
                    String browserSauceLab = ConfigReader.getPropertiesValue("digitalbank.saucelabs.browser");
                    String browserVersion = ConfigReader.getPropertiesValue("digitalbank.saucelabs.browser.version");
*/
                //other method to run parallel testing is to take values from ->.
                //on top of the page, there is like a search wondow, click down arrow and
                // select "Edit Confguration" > vm options (under Build and Run, next to version of JDK)
                // type teh following:
                //-ea
                //-digitalbank.saucelabs.platform=win10
                //-digitalbank.saucelabs.browser=chrome
                //-digitalbank.saucelabs.browser.version=latest
                //And instead of ConfiReader.getPRopertiesValue where we took va;ues from Properties file
                //we use the folowing

                //for this case properties file sohuld have digitalbank.browser=saucelabs
                case "saucelabs":
                    String platform = System.getProperty("digitalbank.saucelabs.platform");
                    String browserSauceLab = System.getProperty("digitalbank.saucelabs.browser");
                    String browserVersion = System.getProperty("digitalbank.saucelabs.browser.version");

                    driver =  loadSauceLabs(platform, browserSauceLab, browserVersion);
                    break;
                case "chrome":
                default:
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
            }
        }
//so and for each driver we apply the following 2 riws

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }

    public static void takeScreenshot(Scenario scenario) {
        if (scenario.isFailed()) {
            //taking screenshot
            //this array of bytes
            //TakesScreenshot is interface, so we convert driver to TakeScreenshot
            //
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            //adding the screenshot to the report
            scenario.attach(screenshot, "image/png", "screenshot");
        }
    }


    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
//could not make it private since could not use it in the method above, so I added "static"
    private static   WebDriver loadSauceLabs(String platform, String browserType, String browserVersion)  {
        //from porperties > digitalbank.properties file
        String USERNAME = ConfigReader.getPropertiesValue("digitalbank.saucelabs.username");
        //   String ACCESS_KEY = ConfigReader.getPropertiesValue("digitalbank.saucelabs.accesskey");
        String ACCESS_KEY = "39aba705-a61d-4dc7-9e25-03cd85759bb6";
        String HOST = ConfigReader.getPropertiesValue("digitalbank.saucelabs.host");

        //sauce lab is the company that has bunch of vms
        //setup url to the hub is running on saucelabs VMs (we need to point to one of the VMs)
//Account > user settings > copy paste Driver Creation > Ondemand URL = url
        //since login and password will often  be changed, we  replace it wuith variables in url
        String url = "https://" + USERNAME + ":" + ACCESS_KEY + "@" + HOST;
//these all settings to run test remotely
        //this is from Selenium
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", platform); //will use Windows 10 ro run in Sause lab
        capabilities.setCapability("browserName", browserType); //will use Chrome browser to run in Sauce Lab
        capabilities.setCapability("browserVersion", browserVersion);

        WebDriver driver = null;
        try {
            driver = new RemoteWebDriver(new URL(url), capabilities);
        } catch (MalformedURLException e) {
          e.printStackTrace();
        }
        return driver;
    }
}
