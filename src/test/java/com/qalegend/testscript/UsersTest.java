package com.qalegend.testscript;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.qalegend.automationcore.Base;
import com.qalegend.constants.ErrorMessages;
import com.qalegend.constants.ExtentLogMessage;
import com.qalegend.listeners.TestListener;
import com.qalegend.pages.*;
import com.qalegend.utilities.ExcelUtility;
import com.qalegend.utilities.RandomUtility;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class UsersTest extends Base {
    LoginPage login;
    HomePage home;
    UserManagementPage userManagement;
    UsersPage user;
    AddUserPage adduser;
    SignoutPage signout;
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();
    private String userName;

        @Test(priority = 1,description = "TC010_verifyUsersPageTitle",groups = {"Regression"})
    public void TC010_verifyUsersPageTitle() {
        extentTest.get().assignCategory("Regression");
        List<ArrayList<String>> loginData = ExcelUtility.excelDataReader("LoginPage");
        String UserName = loginData.get(1).get(1);
        String Password = loginData.get(1).get(2);
        List<ArrayList<String>> UsersData = ExcelUtility.excelDataReader("UserPage");
        String expUserPageTitle = UsersData.get(0).get(1);
        login = new LoginPage(driver);
        login.enterUserName(UserName);
        login.enterUserPassword(Password);
        login.rememberMeCheckBoxClick();
        home = login.clickLoginButton();
        home.clickOnEndTourButton();
        userManagement = home.clickOnTheUserManagementMenu();
        user = userManagement.clickOnUsersMenu();
        String actUserPageTitle = user.getUsersPageTitle();
        extentTest.get().log(Status.PASS, ExtentLogMessage.USERS_TITLE_RECEIVED_MESSAGE);
        Assert.assertEquals(actUserPageTitle, expUserPageTitle, ErrorMessages.TITLE_FAILURE_MESSAGE);
        extentTest.get().log(Status.PASS, ExtentLogMessage.USERS_TITLE_VALIDATION_MESSAGE);
    }
    @Test(priority = 1,description = "TC011 Verify users search with validData",groups = {"Regression"})
    public void TC011_verifyUsersSearchWithValidData() {
        extentTest.get().assignCategory("Regression");
        List<ArrayList<String>> loginData = ExcelUtility.excelDataReader("LoginPage");
        String userName = loginData.get(1).get(1);
        String password = loginData.get(1).get(2);
        List<ArrayList<String>> UsersData = ExcelUtility.excelDataReader("AddUser");
        String userPrefix = UsersData.get(1).get(0);
        String fName = RandomUtility.getfName();
        String lName = RandomUtility.getlName();
        String email=RandomUtility.getRandomEmail();
        String username=fName+lName;
        String userPassword = fName + "@123";
        String percentage=UsersData.get(1).get(1);
        login = new LoginPage(driver);
        login.enterUserName(userName);
        login.enterUserPassword(password);
        login.rememberMeCheckBoxClick();
        home = login.clickLoginButton();
        home.clickOnEndTourButton();
        userManagement = home.clickOnTheUserManagementMenu();
        user = userManagement.clickOnUsersMenu();
        adduser=user.clickOnAddButton();
        adduser.enterPrefix(userPrefix);
        adduser.enterFirstName(fName);
        adduser.enterLastName(lName);
        adduser.enterEmail(email);
        adduser.enterUsername(username);
        adduser.enterPassword(userPassword);
        adduser.enterConfirmPassword(userPassword);
        adduser.enterPercentage(percentage);
        adduser.clickSaveButton();
        user.enterSearchValue(email);
        user.getTableContent(email);
    }
    @Test(priority = 1,description = "TC012 Verify users search with invalid Data",groups = {"Regression"})
    public void TC012_verifyUsersSearchWithInValidData() {
        extentTest.get().assignCategory("Regression");
        List<ArrayList<String>> loginData = ExcelUtility.excelDataReader("LoginPage");
        List<ArrayList<String>> emptyData = ExcelUtility.excelDataReader("UserPage");
        String expEmptyTableMessage=emptyData.get(1).get(1);
        String userName = loginData.get(1).get(1);
        String password = loginData.get(1).get(2);
        login = new LoginPage(driver);
        login.enterUserName(userName);
        login.enterUserPassword(password);
        login.rememberMeCheckBoxClick();
        home = login.clickLoginButton();
        home.clickOnEndTourButton();
        userManagement = home.clickOnTheUserManagementMenu();
        user = userManagement.clickOnUsersMenu();
        String email=RandomUtility.getRandomEmail();
        user.enterSearchValue(email);
        String actEmptyTableMessage = user.getNoRecordsFoundMessage();
        Assert.assertEquals(actEmptyTableMessage, expEmptyTableMessage, ErrorMessages.TITLE_FAILURE_MESSAGE);
    }
    @Test(priority = 1,description = "TC013 Verify field validation messages without filling mandatory fields",groups = {"Regression"})
    public void TC013_verifyAddFieldValidationMessageWithoutFillingMandatoryFields() {
                extentTest.get().assignCategory("Regression");
        List<ArrayList<String>> loginData = ExcelUtility.excelDataReader("LoginPage");
        String userName = loginData.get(1).get(1);
        String password = loginData.get(1).get(2);
        List<ArrayList<String>> UsersData = ExcelUtility.excelDataReader("AddUser");
        String expValidationMessage=UsersData.get(1).get(2);
        String userPrefix = UsersData.get(1).get(0);
        String fName = "";
        String lName = RandomUtility.getlName();
        String email=RandomUtility.getRandomEmail();
        String username=fName+lName;
        String userPassword = fName + "@123";
        String percentage=UsersData.get(1).get(1);
        login = new LoginPage(driver);
        login.enterUserName(userName);
        login.enterUserPassword(password);
        login.rememberMeCheckBoxClick();
        home = login.clickLoginButton();
        home.clickOnEndTourButton();
        userManagement = home.clickOnTheUserManagementMenu();
        user = userManagement.clickOnUsersMenu();
        adduser=user.clickOnAddButton();
        adduser.enterPrefix(userPrefix);
        adduser.enterFirstName(fName);
        adduser.enterLastName(lName);
        adduser.enterEmail(email);
        adduser.enterUsername(username);
        adduser.enterPassword(userPassword);
        adduser.enterConfirmPassword(userPassword);
        adduser.enterPercentage(percentage);
        adduser.clickSaveButton();
        String actValidationMessage=adduser.firstNameFieldValidation();
        Assert.assertEquals(actValidationMessage, expValidationMessage, ErrorMessages.INVALID_FAILURE_MESSAGE);
    }
    @Test(priority = 1,description = "TC014 Verify user login with newly added user",groups = {"Regression"})
        public void TC014_verifyUsersLoginWithNewlyAddedUser() {
        extentTest.get().assignCategory("Regression");
        List<ArrayList<String>> UsersData = ExcelUtility.excelDataReader("AddUser");
        List<ArrayList<String>> loginData = ExcelUtility.excelDataReader("LoginPage");
        String userName = loginData.get(1).get(1);
        String password = loginData.get(1).get(2);
        String expLoginPageTitle = loginData.get(1).get(0);
        String userPrefix = UsersData.get(1).get(0);
        String fName = RandomUtility.getfName();
        String lName = RandomUtility.getlName();
        String email=RandomUtility.getRandomEmail();
        String newusername=fName+lName;
        String newuserPassword = fName + "@123";
        String percentage=UsersData.get(1).get(1);
        login = new LoginPage(driver);
        login.enterUserName(userName);
        login.enterUserPassword(password);
        login.rememberMeCheckBoxClick();
        home = login.clickLoginButton();
        home.clickOnEndTourButton();
        userManagement = home.clickOnTheUserManagementMenu();
        user = userManagement.clickOnUsersMenu();
        adduser=user.clickOnAddButton();
        adduser.enterPrefix(userPrefix);
        adduser.enterFirstName(fName);
        adduser.enterLastName(lName);
        adduser.enterEmail(email);
        adduser.enterUsername(newusername);
        adduser.enterPassword(newuserPassword);
        adduser.enterConfirmPassword(newuserPassword);
        adduser.enterPercentage(percentage);
        adduser.clickSaveButton();
        user.enterSearchValue(email);
        user.getTableContent(email);
        signout = home.clickOnLoggedInUserName();
        login = signout.clickOnSignOutButton();
        String actPageTitle = login.getLoginPageTitle();
        Assert.assertEquals(actPageTitle, expLoginPageTitle, ErrorMessages.SIGN_OUT_FAILED_MESSAGE);
        extentTest.get().log(Status.PASS, ExtentLogMessage.SIGN_OUT_SUCCESS_MESSAGE);
        login.enterUserName(newusername);
        login.enterUserPassword(newuserPassword);
        login.rememberMeCheckBoxClick();
        home = login.clickLoginButton();
        extentTest.get().log(Status.PASS, ExtentLogMessage.SIGNIN_SUCCESS_MESSAGE);
    }
}
