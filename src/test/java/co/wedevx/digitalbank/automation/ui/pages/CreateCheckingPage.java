package co.wedevx.digitalbank.automation.ui.pages;

import co.wedevx.digitalbank.automation.ui.models.AccountCard;
import co.wedevx.digitalbank.automation.ui.models.NewCheckingAccountInfo;
import co.wedevx.digitalbank.automation.ui.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateCheckingPage extends  BaseMenuPage{

    public CreateCheckingPage(WebDriver driver) {
        //this is because it gets inherited from super class BasePage
       super(driver);
    }



    @FindBy(id = "Standard Checking")
    private WebElement standardCheckingAccountTypeRadioButton;

    @FindBy(id = "Interest Checking")
    private WebElement interestCheckingAccountTypeRadioButton;

    @FindBy(id = "Individual")
    private WebElement individualOwnershipTypeRadioButton;

    @FindBy(id = "Joint")
    private WebElement jointOwnershipTypeRadioButton;

    @FindBy(id = "name")
    private WebElement accountNameTxt;

    @FindBy(id = "openingBalance")
    private WebElement openingBalanceTxtBox;

    @FindBy(id = "newCheckingSubmit")
    private WebElement submitBtn;



    //List<AccountCard> accountCardList is model that we created, we trasforming table to list of objectds
    public void createNewChecking(List<NewCheckingAccountInfo> checkingAccountInfoList) {
        //List<AccountCard> accountCardList is model that we created, we trasforming table to list of objectds
        NewCheckingAccountInfo testDataForOneCheckingAccount = checkingAccountInfoList.get(0);

        checkingMenu.click();

        newCheckingButton.click();

        assertEquals(ConfigReader.getPropertiesValue("digitalbank.creatingnewcheckingurl"), driver.getCurrentUrl(), "new checking button didn't take to the url" + ConfigReader.getPropertiesValue("digitalbank.creatingnewcheckingurl"));

        if (testDataForOneCheckingAccount.getCheckingAccountType().equalsIgnoreCase("Standard Checking")) {
            standardCheckingAccountTypeRadioButton.click();
        } else if (testDataForOneCheckingAccount.getCheckingAccountType().equalsIgnoreCase("Interest Checking")) {
            interestCheckingAccountTypeRadioButton.click();
        } else {
            throw new NoSuchElementException("Invalid checking account type option provided. Only supports Standard Checking and Interest Checking");
        }

        if (testDataForOneCheckingAccount.getAccountOwnership().equalsIgnoreCase("Individual")) {
            individualOwnershipTypeRadioButton.click();
        } else if (testDataForOneCheckingAccount.getAccountOwnership().equalsIgnoreCase("Joint")) {
            jointOwnershipTypeRadioButton.click();
        } else {
            throw new NoSuchElementException("Invalid ownership type option provided. Only supports Standard Checking and Interest Checking");
        }

        accountNameTxt.sendKeys(testDataForOneCheckingAccount.getAccountName());
//the user makes initial deposit
        openingBalanceTxtBox.sendKeys(String.valueOf(testDataForOneCheckingAccount.getInitialDepositAmount()));
//the user clicks on the submit button
        submitBtn.click();
    }

}

