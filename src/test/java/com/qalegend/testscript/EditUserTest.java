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

public class EditUserTest extends Base {
    LoginPage login;
    UserManagementPage userManagement;
    HomePage home;
    UsersPage user;
    AddUserPage adduser;
    EditUserPage edituser;
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();
    @Test(priority = 1, description = "TC017 verify Edit user page title", groups = {"Regression"})
    public void TC017_verifyEditUserTitle() {
        extentTest.get().assignCategory("Regression");
        List<ArrayList<String>> loginData = ExcelUtility.excelDataReader("LoginPage");
        List<ArrayList<String>> editUserData = ExcelUtility.excelDataReader("EditUserPage");
        String expEditUserPageTitle = editUserData.get(1).get(0);
        String UserName = loginData.get(1).get(1);
        String Password = loginData.get(1).get(2);
        login = new LoginPage(driver);
        login.enterUserName(UserName);
        login.enterUserPassword(Password);
        login.rememberMeCheckBoxClick();
        home = login.clickLoginButton();
        userManagement = home.clickOnTheUserManagementMenu();
        user = userManagement.clickOnUsersMenu();
        user.enterSearchValue(UserName);
        edituser=user.clickOnEditButton();
        String actualEditUserPageTitle = edituser.getEditUserPageTitle();
        Assert.assertEquals(actualEditUserPageTitle, expEditUserPageTitle, ErrorMessages.TITLE_FAILURE_MESSAGE);
        extentTest.get().log(Status.PASS, ExtentLogMessage.USERS_TITLE_VALIDATION_MESSAGE);
    }
    @Test(priority = 1, description = "TC018 verify user can edit user details", groups = {"Regression"})
    public void TC018_verifyUserCanEditUserDetails() {
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
        edituser=user.clickOnEditButton();
        edituser.editFirstName(fName);
        edituser.editLastName(lName);
        edituser.editPassword(userPassword);
        edituser.editConfirmPassword(userPassword);
        edituser.clickSaveButton();
        user.enterSearchValue(email);
        user.getTableContent(email);
        extentTest.get().log(Status.PASS, ExtentLogMessage.USERS_EDITED_VALIDATION_MESSAGE);
    }
}
