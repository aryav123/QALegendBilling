package com.qalegend.pages;

import com.qalegend.utilities.TestHelper;
import com.qalegend.utilities.WaitUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class UsersPage extends TestHelper {
    public WebDriver driver;
    public UsersPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    private final String _addButton="//a[@class='btn btn-block btn-primary']";
    @FindBy(xpath = _addButton) private WebElement addButton;
    private final String _searchField="//input[@class='form-control input-sm']";
    @FindBy(xpath = _searchField) private WebElement searchField;
    private final String _usersRowElement="//table[@id='users_table']//tr";
    @FindBy(xpath = _usersRowElement) private List<WebElement> usersRowElement;
    private final String _usersColumnElement="//table[@id='users_table']//tr/td";
    @FindBy(xpath = _usersColumnElement) private List<WebElement> usersColumnElement;
    private final String _emptyTableData="//td[@class='dataTables_empty']";
    @FindBy(xpath = _emptyTableData) private WebElement emptyTableData;

    private final String _editButton="//a[@class='btn btn-xs btn-primary']";
    @FindBy(xpath = _editButton) private WebElement editButton;
    private final String _deleteButton="//button[@class='btn btn-xs btn-danger delete_user_button']";
    @FindBy(xpath = _deleteButton) private WebElement deleteButton;
    private final String _viewButton="//a[@class='btn btn-xs btn-info']";
    @FindBy(xpath = _viewButton) private WebElement viewButton;
    private final String _okButton="//button[@class='swal-button swal-button--confirm swal-button--danger']";
    @FindBy(xpath = _okButton) private WebElement okButton;

    public String getUsersPageTitle(){
        String title=page.getPageTitle(driver);
        return  title;
    }
    public AddUserPage clickOnAddButton(){
        page.clickOnElement(addButton);
        return new AddUserPage(driver);
    }
    public EditUserPage clickOnEditButton(){
        page.clickOnElement(editButton);
        return new EditUserPage(driver);
    }
    public void clickOnDeleteButton(){
        page.clickOnElement(deleteButton);
    }
    public ViewUserPage clickOnViewButton(){
        page.clickOnElement(viewButton);
        return new ViewUserPage(driver);
    }
    public void clickOnOkButton(){
        page.clickOnElement(okButton);
    }
    public void enterSearchValue(String email) {
        wait.setHardWait();
        wait.waitForElementToBeVisible(driver, _searchField, WaitUtility.LocatorType.Xpath);
        page.enterText(searchField, email);
    }
    public void getTableContent(String email) {
        wait.setHardWait();
        wait.waitForElementToBeVisible(driver, _usersRowElement, WaitUtility.LocatorType.Xpath);
        List<ArrayList<String>> actGridData=table.get_Dynamic_TwoDimension_TablElemnts(usersRowElement,usersColumnElement);
        for (int i = 0; i < actGridData.size(); i++) {
            if (actGridData.get(i).get(0).equals(email)) {
                for (int j = 1; j < actGridData.get(i).size(); j++) {
                    System.out.println(actGridData.get(i).get(j));
                }
            }
        }

    }
    public String getNoRecordsFoundMessage() {
        wait.setHardWait();
        wait.waitForElementToBeVisible(driver, _usersRowElement, WaitUtility.LocatorType.Xpath);
        String emptyMessage= page.getElementText(emptyTableData);
        return emptyMessage;
    }
}
