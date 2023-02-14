package com.qalegend.pages;

import com.qalegend.utilities.TestHelper;
import com.qalegend.utilities.WaitUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends TestHelper {
    public WebDriver driver;
    public HomePage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    private final String _userAccountNameField = "//a[@class='dropdown-toggle']";
    @FindBy(xpath = _userAccountNameField)
    private WebElement userAccountNameField;

    private final String _dateDisplayed="//div[@class='m-8 pull-left mt-15 hidden-xs']//strong";
    @FindBy(xpath = _dateDisplayed) private WebElement dateDisplayed;
    private final String _usernameDisplayed="//a[@class='dropdown-toggle']//span";
    @FindBy(xpath = _usernameDisplayed) private WebElement usernameDisplayed;
    private final String _userManagementMenu="//span[text()='User Management']";
    @FindBy(xpath = _userManagementMenu )private WebElement userManagementMenu;

    public String getUserAccountNameText(){
        wait.setHardWait();
        wait.waitForElementToBeVisible(driver, _userAccountNameField, WaitUtility.LocatorType.Xpath);
        String accountNameText=page.getElementText(userAccountNameField);
        return accountNameText;
    }
    public String getHomePageTitle() {
        String title = page.getPageTitle(driver);
        return title;
    }
    public String getDate(){
        String date=page.getElementText(dateDisplayed);
        return date;
    }
    public SignoutPage clickOnLoggedInUserName(){
        page.clickOnElement(usernameDisplayed);
        return new SignoutPage(driver);
    }
    public UserManagementPage clickOnTheUserManagementMenu(){
        page.clickOnElement(userManagementMenu);
        wait.setHardWait();
        return new UserManagementPage(driver);
    }
}
