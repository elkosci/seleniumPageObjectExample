package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GooglePage extends PageObject{
    private static GooglePage instance;
    @FindBy(id="main")
    private WebElement pageMain;
    @FindBy(id="lst-ib")
    private WebElement searchInput;
    @FindBy(id="resultStats")
    private WebElement resultStats;

    GooglePage(WebDriver driver, String baseUrl){
        super(driver, baseUrl);
    }
    public static GooglePage getGooglePage(String browser, String baseUrl) {
        if (instance == null) {
            instance = new GooglePage(WebDriverFactory.getDriver(browser), baseUrl);
        }
        return instance;
    }
    public String getPageTitle() {
        return this.driver.getTitle();
    }
    public WebElement getPageMain(){
        return pageMain;
    }
    public WebElement getSearchInput(){
        return searchInput;
    }
    public WebElement getResultStats(){
        return resultStats;
    }
    public boolean isOpen(){
        return getPageMain().isDisplayed() ? true: false;
    }
    public void cleanup(){
        driver.manage().deleteAllCookies();
    }

}
