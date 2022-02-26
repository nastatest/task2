import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TvPage extends BasicPage{

    @FindBy(xpath = "/html[@class='responsive-layout']/body[@class='no-touch']/div[@class='layout-container']/div[@id='container']/div[@class='g-container-outer']/div[@class='l-gradient-wrapper']/div[@class='g-middle']/div[@class='g-middle-i']/div[@class='catalog-content js-scrolling-area']/div[@class='schema-grid__wrapper']/div[@class='schema-grid']/div[@class='schema-grid__left-column']/div[@class='schema-filter__wrapper']/div[@id='schema-filter']/div[4]/div[@class='schema-filter__fieldset'][1]/div[@class='schema-filter__facet']/ul[@class='schema-filter__list']/li[2]/label[@class='schema-filter__checkbox-item']/span[@class='i-checkbox']/input")
    private WebElement samsungElement;

    @FindBy(xpath = "/html/body/div[1]/div/div/div/div/div/div[2]/div[1]/div[4]/div[2]/div[2]/div[1]/div[4]/div[5]/div[2]/div/div[2]/input")
    private  WebElement priceElement;

    @FindBy(xpath = "/html[@class='responsive-layout']/body[@class='no-touch']/div[@class='layout-container']/div[@id='container']/div[@class='g-container-outer']/div[@class='l-gradient-wrapper']/div[@class='g-middle']/div[@class='g-middle-i']/div[@class='catalog-content js-scrolling-area']/div[@class='schema-grid__wrapper']/div[@class='schema-grid']/div[@class='schema-grid__left-column']/div[@class='schema-filter__wrapper']/div[@id='schema-filter']/div[4]/div[@class='schema-filter__fieldset'][8]/div[@class='schema-filter__facet']/ul[@class='schema-filter__list']/li[3]/label[@class='schema-filter__checkbox-item']/span[@class='i-checkbox']/input")
    private  WebElement resolutionElement;

    @FindBy(xpath = "/html[@class='responsive-layout']/body[@class='no-touch']/div[@class='layout-container']/div[@id='container']/div[@class='g-container-outer']/div[@class='l-gradient-wrapper']/div[@class='g-middle']/div[@class='g-middle-i']/div[@class='catalog-content js-scrolling-area']/div[@class='schema-grid__wrapper']/div[@class='schema-grid']/div[@class='schema-grid__left-column']/div[@class='schema-filter__wrapper']/div[@id='schema-filter']/div[4]/div[@class='schema-filter__fieldset'][6]/div[@class='schema-filter__facet']/div[@class='schema-filter__group']/div[@class='schema-filter-control schema-filter-control_select'][1]/select[@class='schema-filter-control__item']")
    private  WebElement minSizeElement;

    @FindBy(xpath = "/html[@class='responsive-layout']/body[@class='no-touch']/div[@class='layout-container']/div[@id='container']/div[@class='g-container-outer']/div[@class='l-gradient-wrapper']/div[@class='g-middle']/div[@class='g-middle-i']/div[@class='catalog-content js-scrolling-area']/div[@class='schema-grid__wrapper']/div[@class='schema-grid']/div[@class='schema-grid__left-column']/div[@class='schema-filter__wrapper']/div[@id='schema-filter']/div[4]/div[@class='schema-filter__fieldset'][6]/div[@class='schema-filter__facet']/div[@class='schema-filter__group']/div[@class='schema-filter-control schema-filter-control_select'][2]/select[@class='schema-filter-control__item']")
    private  WebElement maxSizeElement;

    @FindBy(xpath = "//div[@class='schema-product__group']")
    private WebElement tvElement;

    @Override
    public String GetURL() {
        return "catalog.onliner.by/tv";
    }

    public TvPage(WebDriver driver, PropertyReader config) {
        super(driver, config);
    }

    public void filter() throws InterruptedException {
        samsungElement.sendKeys(Keys.SPACE);
        priceElement.sendKeys("1500");
        resolutionElement.sendKeys(Keys.SPACE);
        minSizeElement.sendKeys("40");
        maxSizeElement.sendKeys("50");

        WaitForElement(tvElement);
        Thread.sleep(4000);

        List<WebElement> tvs = driver.findElements(By.xpath("//div[@class='schema-product__group']"));

        Assert.assertEquals(tvs.size(), 5);

        for (WebElement tv : tvs) {
            WebElement description = tv.findElement(By.xpath("//span[@data-bind='html: product.description']"));
            WebElement name = tv.findElement(By.xpath("//span[@data-bind='html: product.extended_name || product.full_name']"));
            WebElement price = tv.findElement(By.xpath("//a[@class='schema-product__price-value schema-product__price-value_primary']/span"));

            int intPrice = 0;
            int intResolution = 0;

            Pattern pattern = Pattern.compile("([0-9]+)");
            Matcher matcher = pattern.matcher(price.getText());
            if (matcher.find())
            {
                intPrice = Integer.parseInt(matcher.group(1));
            }

            pattern = Pattern.compile("(..)\"");
            matcher = pattern.matcher(description.getText());
            if (matcher.find())
            {
                intResolution = Integer.parseInt(matcher.group(1));
            }

            assert(name.getText().toLowerCase().contains("samsung"));
            assert(intPrice <= 1500);
            assert(intResolution >= 40 && intResolution <= 50);
            assert(description.getText().contains("1920x1080"));

        }

    }
}