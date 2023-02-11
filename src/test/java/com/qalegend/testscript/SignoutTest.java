package com.qalegend.testscript;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.qalegend.automationcore.Base;
import com.qalegend.constants.ErrorMessages;
import com.qalegend.constants.ExtentLogMessage;
import com.qalegend.listeners.TestListener;
import com.qalegend.pages.HomePage;
import com.qalegend.pages.LoginPage;
import com.qalegend.pages.SignoutPage;
import com.qalegend.utilities.ExcelUtility;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class SignoutTest extends Base {
    LoginPage login;
    HomePage home;
    SignoutPage signout;
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();

    @Test(priority = 1, description = "TC008_verify user signOut", groups = {"Regression"})
    public void TC008_verifyUserSignOut() {
        extentTest.get().assignCategory("Regression");
        List<ArrayList<String>> loginData = ExcelUtility.excelDataReader("LoginPage");
        String expLoginPageTitle = loginData.get(1).get(0);
        String UserName = loginData.get(1).get(1);
        String Password = loginData.get(1).get(2);
        login = new LoginPage(driver);
        login.enterUserName(UserName);
        login.enterUserPassword(Password);
        login.rememberMeCheckBoxClick();
        home = login.clickLoginButton();
        home.clickOnEndTourButton();
        signout = home.clickOnLoggedInUserName();
        login = signout.clickOnSignOutButton();
        String actPageTitle = login.getLoginPageTitle();
        Assert.assertEquals(actPageTitle, expLoginPageTitle, ErrorMessages.SIGN_OUT_FAILED_MESSAGE);
        extentTest.get().log(Status.PASS, ExtentLogMessage.SIGN_OUT_SUCCESS_MESSAGE);
    }
}