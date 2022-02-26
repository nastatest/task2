import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasicPage {

    PropertyReader config;
    protected WebDriver driver;

    public  abstract String GetURL();

    public BasicPage(WebDriver driver, PropertyReader config) {
        this.config = config;

        if (!driver.getCurrentUrl().contains(GetURL())) {
            throw new IllegalStateException("This page is wrong");
        }

        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    protected WebElement WaitForElement(WebElement element)
    {
        return new WebDriverWait(driver, Duration.ofSeconds(config.getTimeout()))
                .until(ExpectedConditions.elementToBeClickable(element));
    }
}