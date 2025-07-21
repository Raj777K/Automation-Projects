package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // ✅ Fixed locator
    By usernameField = By.name("username");
    By passwordField = By.name("password");
    By loginBtn = By.cssSelector("button[type='submit']");
    By profileMenu = By.className("oxd-userdropdown-tab");
    By logoutLink = By.xpath("//a[text()='Logout']");

    public void login(String username, String password) {
        driver.findElement(usernameField).clear();
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginBtn).click();
    }

    public void logout() {
        driver.findElement(profileMenu).click();
        driver.findElement(logoutLink).click();
    }
}
