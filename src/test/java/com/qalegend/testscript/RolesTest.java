package com.qalegend.testscript;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.qalegend.automationcore.Base;
import com.qalegend.constants.ErrorMessages;
import com.qalegend.constants.ExtentLogMessage;
import com.qalegend.listeners.TestListener;
import com.qalegend.pages.*;
import com.qalegend.utilities.ExcelUtility;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class RolesTest extends Base {
    LoginPage login;
    HomePage home;
    UserManagementPage userManagement;
    UsersPage user;
    RolesPage roles;
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();
    private String userName;
    @Test(priority = 1,description = "TC020_verifyRolesPageTitle",groups = {"Regression"})
    public void TC020_verifyRolesPageTitle() {
        extentTest.get().assignCategory("Regression");
        List<ArrayList<String>> loginData = ExcelUtility.excelDataReader("LoginPage");
        String UserName = loginData.get(1).get(1);
        String Password = loginData.get(1).get(2);
        List<ArrayList<String>> rolesData = ExcelUtility.excelDataReader("RolesPage");
        String expUserPageTitle = rolesData.get(1).get(0);
        login = new LoginPage(driver);
        login.enterUserName(UserName);
        login.enterUserPassword(Password);
        login.rememberMeCheckBoxClick();
        home = login.clickLoginButton();
        userManagement = home.clickOnTheUserManagementMenu();
        roles = userManagement.clickOnRolesMenu();
        String actUserPageTitle = roles.getRolesPageTitle();
        extentTest.get().log(Status.PASS, ExtentLogMessage.ROLES_TITLE_RECEIVED_MESSAGE);
        Assert.assertEquals(actUserPageTitle, expUserPageTitle, ErrorMessages.TITLE_FAILURE_MESSAGE);
        extentTest.get().log(Status.PASS, ExtentLogMessage.ROLES_TITLE_VALIDATION_MESSAGE);
    }
}
