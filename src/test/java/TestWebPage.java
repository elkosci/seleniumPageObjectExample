import utilities.*;
import org.openqa.selenium.Keys;
import org.testng.annotations.*;
import static org.assertj.core.api.Assertions.assertThat;
import java.io.IOException;
import java.util.Map;

public class TestWebPage {
    static String baseUrl;
    static Map<String, String> configuration;
    private GooglePage gPage;
    @BeforeSuite(alwaysRun = true)
    public void setUpSuite(){
        try {
            configuration = Configuration.getProperites();
            baseUrl = configuration.get("baseUrl");
        } catch(IOException e){
            System.out.println("FileNotFoundException thrown: " + e);
        }
    }
    @AfterSuite(alwaysRun = true)
    public void tearDownSuite(){
        WebDriverFactory.finishBrowser();
    }
    @Parameters(value="browser")
    @BeforeTest(alwaysRun = true)
    public void setUp(String browser) {
        assertThat(baseUrl).isNotEmpty();
        gPage = GooglePage.getGooglePage(browser, baseUrl);
        assertThat(gPage).isNotNull();
    }
    @AfterTest(alwaysRun = true)
    public void tearDown() {
        gPage.cleanup();
    }
    @Parameters(value="pageTitle")
    @Test(priority = 1)
    public void testGooglePageTitleEquals(String pageTitle) {
        assertThat(gPage.isOpen()).isTrue();
        assertThat(gPage.getPageTitle()).isEqualTo(pageTitle);
    }
    @Test(priority = 2)
    public void testGooglePageMainElementPresence() {
        assertThat(gPage.isOpen()).isTrue();
        assertThat(gPage.getPageMain()).isNotNull();
    }
    @Parameters(value="searchPhrase")
    @Test(priority = 3)
    public void testGooglePageSearchResultStatsElementPresence(String searchPhrase) {
        assertThat(gPage.isOpen()).isTrue();
        assertThat(gPage.getPageMain()).isNotNull();
        gPage.getSearchInput().sendKeys(searchPhrase);
        gPage.getSearchInput().sendKeys(Keys.ENTER);
        assertThat(gPage.getResultStats()).isNotNull();
    }
}
