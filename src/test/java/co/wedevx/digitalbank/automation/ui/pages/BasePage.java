package co.wedevx.digitalbank.automation.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

//it is our super class, abstract class
public class BasePage {
    public WebDriver driver;

    public BasePage (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

}
