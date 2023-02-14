package com.qalegend.testscript;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.qalegend.automationcore.Base;
import com.qalegend.constants.ErrorMessages;
import com.qalegend.constants.ExtentLogMessage;
import com.qalegend.listeners.TestListener;
import com.qalegend.pages.HomePage;
import com.qalegend.pages.LoginPage;
import com.qalegend.pages.UserManagementPage;
import com.qalegend.utilities.ExcelUtility;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class UserManagementTest extends Base {
    LoginPage login;
    HomePage home;
    UserManagementPage userManagement;
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();

    @Test(priority = 1, description = "TC001_verifyTheUserManagementSubTabsAreDisplayed", groups = {"Regression"})
    public void TC009_verifyTheUserManagementSubTabsAreDisplayed() {
        extentTest.get().assignCategory("Regression");
        List<ArrayList<String>> loginData = ExcelUtility.excelDataReader("LoginPage");
        String UserName = loginData.get(1).get(1);
        String Password = loginData.get(1).get(2);
        login = new LoginPage(driver);
        login.enterUserName(UserName);
        login.enterUserPassword(Password);
        login.clickOnRememberMeButton();
        home = login.clickLoginButton();
        userManagement = home.clickOnTheUserManagementMenu();
        Boolean userMenuStatus = userManagement.userMenuIsDisplayed();
        Assert.assertTrue(userMenuStatus, ErrorMessages.USER_SUB_MENU_NOT_FOUND_MESSAGE);
        extentTest.get().log(Status.PASS, ExtentLogMessage.USER_MANAGEMENT_USERS_MENU_DISPLAYED_MESSAGE);
        Boolean rolesMenuStatus = userManagement.roleMenuIsDisplayed();
        Assert.assertTrue(rolesMenuStatus, ErrorMessages.USER_MANAGEMENT_ROLES_MENU_DISPLAYED_MESSAGE);
        extentTest.get().log(Status.PASS, ExtentLogMessage.USER_MANAGEMENT_ROLES_MENU_DISPLAYED_MESSAGE);
        Boolean salesMenuStatus = userManagement.salesCommissionMenuIsDisplayed();
        Assert.assertTrue(salesMenuStatus, ErrorMessages.SALES_SUB_MENU_NOT_FOUND_MESSAGE);
        extentTest.get().log(Status.PASS, ExtentLogMessage.USER_MANAGEMENT_SALES_MENU_DISPLAYED_MESSAGE);
    }
}