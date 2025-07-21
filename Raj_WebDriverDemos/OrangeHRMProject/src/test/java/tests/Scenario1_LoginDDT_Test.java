package tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import base.BaseClass;
import pages.LoginPage;
import utils.ExcelUtil;
import utils.ExtentReportManager;
import utils.ScreenshotUtil;

public class Scenario1_LoginDDT_Test extends BaseClass {

    ExtentReports extent;
    ExtentTest test;

    @BeforeTest
    public void setupReport() {
        extent = ExtentReportManager.getInstance();
    }

    @Test(dataProvider = "loginData")
    public void loginTest(String username, String password, String expectedResult) throws Exception {
        test = extent.createTest("Login Test: " + username);
        LoginPage login = new LoginPage(driver);
        login.login(username, password);

        Thread.sleep(2000);
        ScreenshotUtil.captureScreenshot(driver, username + "_" + expectedResult);

        boolean isSuccess = driver.getCurrentUrl().contains("dashboard");

        if (expectedResult.equalsIgnoreCase("Valid")) {
            AssertJUnit.assertTrue(isSuccess);
            test.pass("Valid login passed");
            login.logout();
        } else {
            AssertJUnit.assertFalse(isSuccess);
            test.pass("Invalid login blocked as expected");
        }
    }

    @DataProvider(name = "loginData")
    public Object[][] getData() {
        return ExcelUtil.readExcel("src/test/resources/LoginData.xlsx", "Sheet1");
    }

    @AfterTest
    public void tearDownReport() {
        extent.flush();
    }
}
