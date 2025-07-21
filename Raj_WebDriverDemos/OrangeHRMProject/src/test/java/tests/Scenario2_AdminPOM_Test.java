package tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import base.BaseClass;
import org.testng.annotations.*;
import pages.LoginPage;
import pages.AdminPage;
import utils.ScreenshotUtil;
import org.testng.Assert;

public class Scenario2_AdminPOM_Test extends BaseClass {

    LoginPage login;
    AdminPage admin;

    @BeforeClass
    public void setupPages() {
        login = new LoginPage(driver);
        admin = new AdminPage(driver);
        login.login("Admin", "admin123");
    }

    @Test(priority = 1)
    public void verifyLeftMenuCount() {
        try {
            int actualCount = admin.getLeftMenuCount();  // We'll add this method below
            Assert.assertEquals(actualCount, 11, "Menu count mismatch");
        } catch (Exception e) {
            ScreenshotUtil.captureScreenshot(driver, "verifyLeftMenuCount_Failure");
            AssertJUnit.fail("Exception in verifyLeftMenuCount: " + e.getMessage());
        }
    }

    @Test(priority = 2)
    public void searchByUsername() {
        try {
            boolean result = admin.searchByUsername("Admin");
            Assert.assertTrue(result, "User not found");
        } catch (Exception e) {
            ScreenshotUtil.captureScreenshot(driver, "searchByUsername_Failure");
            AssertJUnit.fail("Exception in searchByUsername: " + e.getMessage());
        }
    }

    @Test(priority = 3)
    public void searchByUserRole() {
        try {
            boolean result = admin.searchByUserRole("Admin");
            Assert.assertTrue(result, "User role search failed");
        } catch (Exception e) {
            ScreenshotUtil.captureScreenshot(driver, "searchByUserRole_Failure");
            AssertJUnit.fail("Exception in searchByUserRole: " + e.getMessage());
        }
    }

    @Test(priority = 4)
    public void searchByStatus() {
        try {
            boolean result = admin.searchByStatus("Enabled");
            Assert.assertTrue(result, "User status search failed");
        } catch (Exception e) {
            ScreenshotUtil.captureScreenshot(driver, "searchByStatus_Failure");
            Assert.fail("Exception in searchByStatus: " + e.getMessage());
        }
    }

    @AfterClass
    public void logout() {
        login.logout();
    }
}
