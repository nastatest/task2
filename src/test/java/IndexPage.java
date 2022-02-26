import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class IndexPage extends BasicPage {

    @Override
    public String GetURL() {
        return "onliner.by";
    }

    @FindBy(xpath = "/html/body/div[1]/div/div/div/header/div[2]/div/nav/ul[1]/li[1]/a[2]/span")
    private WebElement catalogueElement;

    public IndexPage(WebDriver driver, PropertyReader config) {
        super(driver, config);
    }

    public CataloguePage goToCatalogue() {
        System.out.println(driver.getTitle());
        catalogueElement.click();

        return new CataloguePage(driver, config);
    }
}