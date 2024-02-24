package co.wedevx.digitalbank.automation.ui.pages;

import com.fasterxml.jackson.databind.ser.Serializers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BaseMenuPage extends BasePage {
    //we will locate here all elements that are located on the left side panel on home page and these elements are present on all pages
    public BaseMenuPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(id = "checking-menu")
    //we make them protected, not private since we can't see them in CreateCheckingPage
    //other option is create getterss, but to make them protected is easier for now
    protected WebElement checkingMenu;

    @FindBy(id = "new-checking-menu-item")
    protected WebElement newCheckingButton;

    @FindBy(id = "view-checking-menu-item")
    protected WebElement viewCheckingButton;

    @FindBy(id = "home-menu-item")
    protected WebElement homeButton;

    public void goToHomePage(){

    }

}
