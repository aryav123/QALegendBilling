package com.qalegend.testscript;

import com.aventstack.extentreports.ExtentTest;
import com.qalegend.automationcore.Base;
import com.qalegend.constants.ErrorMessages;
import com.qalegend.listeners.TestListener;
import com.qalegend.pages.*;
import com.qalegend.utilities.ExcelUtility;
import com.qalegend.utilities.RandomUtility;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class ViewUsersTest extends Base {
    LoginPage login;
    HomePage home;
    UserManagementPage userManagement;
    UsersPage user;
    ViewUserPage viewuser;
    AddUserPage adduser;
    SignoutPage signout;
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();
    @Test(priority = 1,description = "TC020 Verify the details displayed on view user page",groups = {"Smoke"})
    public void TC020_verifyUsersDetails() {
        extentTest.get().assignCategory("Smoke");
        List<ArrayList<String>> loginData = ExcelUtility.excelDataReader("LoginPage");
        String userName = loginData.get(1).get(1);
        String password = loginData.get(1).get(2);
        List<ArrayList<String>> UsersData = ExcelUtility.excelDataReader("AddUser");
        String userPrefix = UsersData.get(1).get(0);
        String userRole=UsersData.get(1).get(5);
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
        viewuser = user.clickOnViewButton();
        String viewEmailIdText= viewuser.getEmailID();
        String viewRoleText= viewuser.getRole();
        String viewUserNameText= viewuser.getUserName();
        Assert.assertEquals(viewEmailIdText, email, ErrorMessages.INVALID_TEXT_FOUND);
        Assert.assertEquals(viewRoleText, userRole, ErrorMessages.INVALID_TEXT_FOUND);
        Assert.assertEquals(viewUserNameText, username, ErrorMessages.INVALID_TEXT_FOUND);
    }
}
