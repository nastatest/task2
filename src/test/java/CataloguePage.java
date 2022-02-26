import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class CataloguePage extends BasicPage{

    @FindBy(xpath = "/html/body/div[1]/div/div/div/div/div/div[1]/ul/li[2]")
    private WebElement menuElement;

    @FindBy(xpath = "/html/body/div[1]/div/div/div/div/div/div[1]/div[4]/div/div[1]/div[1]/div/div[2]/div[1]")
    private WebElement submenuElement;

    @FindBy(xpath = "/html/body/div[1]/div/div/div/div/div/div[1]/div[4]/div/div[1]/div[1]/div/div[2]/div[2]/div/a[1]")
    private  WebElement tvElement;

    @Override
    public String GetURL() {
        return "catalog.onliner.by";
    }

    public CataloguePage(WebDriver driver, PropertyReader config) {
        super(driver, config);
    }

    public TvPage goToTv() {
        System.out.println(driver.getTitle());
        menuElement.click();

        WaitForElement(submenuElement);

        Actions action = new Actions(driver);
        action.moveToElement(submenuElement).click().perform();

        WaitForElement(tvElement).click();

        return new TvPage(driver, config);
    }
}