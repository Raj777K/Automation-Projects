	package pages;
	
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.ui.Select;
	
	import java.util.List;
	
	public class AdminPage {
	
	    WebDriver driver;
	
	    // Constructor
	    public AdminPage(WebDriver driver) {
	        this.driver = driver;
	    }
	    // Locators
	    private By menuItems = By.cssSelector(".oxd-sidepanel-body ul li"); // Adjust if needed
	    private By adminTab = By.xpath("//span[text()='Admin']");
	    private By usernameField = By.xpath("//label[text()='Username']/following::input[1]");
	    private By userRoleDropdown = By.xpath("//label[text()='User Role']/following::div[@class='oxd-select-text-input'][1]");
	    private By userRoleOptions = By.xpath("//div[@role='listbox']//span"); // generic options locator
	    private By statusDropdown = By.xpath("//label[text()='Status']/following::div[@class='oxd-select-text-input'][1]");
	    private By searchButton = By.xpath("//button[normalize-space()='Search']");
	    private By resultRows = By.xpath("//div[@class='oxd-table-body']/div"); // rows of search result
	
	    // Click Admin Tab (useful if not already default)
	    public void goToAdminTab() {
	        driver.findElement(adminTab).click();
	    }
	
	    // 1. Count the number of menu items
	    public int getLeftMenuCount() {
	        List<WebElement> items = driver.findElements(menuItems);
	        return items.size();
	    }
	
	    // 2. Search by username and verify result
	    public boolean searchByUsername(String username) {
	        goToAdminTab();
	        driver.findElement(usernameField).clear();
	        driver.findElement(usernameField).sendKeys(username);
	        driver.findElement(searchButton).click();
	
	        return verifyResultContains(username);
	    }
	
	    // 3. Search by user role
	    public boolean searchByUserRole(String role) {
	        goToAdminTab();
	        selectDropdownValue(userRoleDropdown, role);
	        driver.findElement(searchButton).click();
	
	        return verifyResultContains(role);
	    }
	
	    // 4. Search by status
	    public boolean searchByStatus(String status) {
	        goToAdminTab();
	        selectDropdownValue(statusDropdown, status);
	        driver.findElement(searchButton).click();
	
	        return verifyResultContains(status);
	    }
	
	    // Helper to select from dropdown
	    private void selectDropdownValue(By dropdownLocator, String visibleText) {
	        driver.findElement(dropdownLocator).click();
	
	        List<WebElement> options = driver.findElements(userRoleOptions);
	        for (WebElement option : options) {
	            if (option.getText().equalsIgnoreCase(visibleText)) {
	                option.click();
	                break;
	          
	            
	            }
	        }
	    }
	
	    // Helper to verify result contains a value
	    private boolean verifyResultContains(String value) {
	        List<WebElement> rows = driver.findElements(resultRows);
	        for (WebElement row : rows) {
	            if (row.getText().contains(value)) {
	                return true;
	            }
	        }
	        return false; } }
