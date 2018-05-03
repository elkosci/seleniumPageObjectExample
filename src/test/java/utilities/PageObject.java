package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PageObject {
    protected WebDriver driver;

    protected PageObject(WebDriver driver, String baseUrl){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.driver.get(baseUrl);
    }
}
