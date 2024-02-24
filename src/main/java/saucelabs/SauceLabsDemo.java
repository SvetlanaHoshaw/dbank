package saucelabs;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.Key;

//this code will run the test  remotely using Sauce lab

public class SauceLabsDemo {

    public static void main(String[] args) throws MalformedURLException {
//in this method we change only
//RUN THIS AND AT THE SAME TIME RUN ANOTHER SAUCELAB CLASS and


    //1. HOW TO USE sauseLabs
        //login or create an accout in SauseLab, then username and accessKey take from
        //Account > user settings
        String username = "oauth-svitlanahoshaw-f3843";
        String accessKey = "39aba705-a61d-4dc7-9e25-03cd85759bb6";

        //sauce lab is the company that has bunch of vms
        //setup url to the hub is running on saucelabs VMs (we need to point to one of the VMs)
//Account > user settings > copy paste Driver Creation > Ondemand URL
        String url = "https://oauth-svitlanahoshaw-f3843:39aba705-a61d-4dc7-9e25-03cd85759bb6@ondemand.us-west-1.saucelabs.com:443/wd/hub";
//these all settings to run test remotely
        //this is from Selenium
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", Platform.WIN10); //will use Windows 10 ro run in Sause lab
        capabilities.setCapability("browserName", BrowserType.CHROME); //will use Chrome browser to run in Sauce Lab
        capabilities.setCapability("browserVersion", "latest");

        WebDriver driver = new RemoteWebDriver(new URL(url),capabilities);

        driver.get("https://www.amazon.com/");
        WebElement searchBox = driver.findElement(By.cssSelector("#twotabsearchtextbox"));
        searchBox.sendKeys("Iphone" + Keys.ENTER);

        WebElement appleBrand = driver.findElement(By.cssSelector("li[id='p_89/Apple'']"));

        System.out.println(appleBrand.getText());


    //2. TO CHECK RESULTS IN SAUCE LAB > lef panel: AUTOMATED > Test Results > video, screenshots, logs, metadata, commands
       //click on the test, you wil see video and screeenshot about this test
    }
}
