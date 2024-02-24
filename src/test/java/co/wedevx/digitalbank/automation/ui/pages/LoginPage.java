package co.wedevx.digitalbank.automation.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    //Use @FindBy annotation to locate and initialize web elements

    //annotation @FindBy automatically go to driver.findElement.By.id
    //and it initializes found WebElement to usernameField variable
    @FindBy(id = "username")
    private WebElement usernameTxBox;
    //so this will make out code shorter, since we do not need
    //to sue driver.findElement.findElement.By.Id("username)

    @FindBy(id = "password")
    private WebElement passwordTxtBox;

    @FindBy(id = "remember-me")
    private WebElement remember_meCheckBox;

    @FindBy(xpath = "//button")
    private WebElement submitBtn;

    @FindBy(xpath = "//a[contains(text(), 'Sign Up Here')]")
    private WebElement signUpHereLink;

    //create action methods
    public void login(String username, String password) {
        usernameTxBox.sendKeys(username);
        passwordTxtBox.sendKeys(password);
        submitBtn.click();
    }


}
