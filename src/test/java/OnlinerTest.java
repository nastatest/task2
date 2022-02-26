import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class OnlinerTest {
    private PropertyReader config;

    private WebDriver driver;

    public OnlinerTest()
    {
        config = new PropertyReader("test.properties");
    }

    @BeforeSuite
    public void initDriver() throws Exception {
        System.setProperty("webdriver.chrome.driver", config.getChromeDriverPath());
        driver = new ChromeDriver();
    }

    @Test
    public void tvFilterTest() throws InterruptedException {
        driver.get(config.getUrl());
        IndexPage index = new IndexPage(driver, config);
        index
                .goToCatalogue()
                .goToTv()
                .filter()
        ;
    }

    @AfterSuite
    public void quitDriver() throws Exception {
        driver.quit();
    }
}
