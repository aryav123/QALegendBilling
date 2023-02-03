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
}
