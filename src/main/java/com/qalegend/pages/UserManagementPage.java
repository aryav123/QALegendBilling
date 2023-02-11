package com.qalegend.pages;

import com.qalegend.utilities.TestHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserManagementPage extends TestHelper {
    public WebDriver driver;
    /** Page Constructor **/
    public UserManagementPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    private final String _usersMenu="//i[@class='fa fa-user']//following-sibling::span";
    @FindBy(xpath = _usersMenu )private WebElement usersMenu;
    private final String _rolesMenu="//i[@class='fa fa-briefcase']//following-sibling::span";
    @FindBy(xpath = _rolesMenu )private WebElement rolesMenu;
    private final String _salesCommissionMenu="//i[@class='fa fa-handshake-o']//following-sibling::span";
    @FindBy(xpath = _salesCommissionMenu )private WebElement salesCommissionMenu;
    public boolean userMenuIsDisplayed(){
        Boolean status= page.isDisplayed(usersMenu);
        return status;
    }
    public boolean roleMenuIsDisplayed(){
        Boolean status= page.isDisplayed(rolesMenu);
        return status;
    }

    public boolean salesCommissionMenuIsDisplayed(){
        Boolean status= page.isDisplayed(salesCommissionMenu);
        return status;
    }
    public UsersPage clickOnUsersMenu(){
        page.clickOnElement(usersMenu);
        return new UsersPage(driver);
    }
}
