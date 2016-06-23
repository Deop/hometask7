package onliner.pages;

import onliner.elements.ManufacturerCheckBox;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import webdriver.BaseForm;
import webdriver.elements.Button;
import webdriver.elements.DropDownList;
import webdriver.elements.Label;
import webdriver.elements.TextBox;

import java.util.List;

public class TvPage extends BaseForm {

    private long timeout = 5;
    private String strCatalogLinkPattern = "//a[contains(@class, 'catalog-bar__link') and contains(text(), '%s')]";
    private Label lblTvs = new Label(By.xpath("//h1[@class='schema-header__title']"), "TV label");
    private TextBox txbMaxPrice = new TextBox(By.xpath("//input[contains (@class, 'input_price') and contains(@data-bind, 'facet.placeholder.to')]"), "Max Price text box");
    private TextBox txbDateFrom = new TextBox(By.xpath("//span[contains(text(), 'Дата выхода')]/../..//input[contains(@data-bind, 'facet.placeholder.from')]"), "Min Date text box");
    private DropDownList ddlMinScreenSize = new DropDownList(By.xpath("//select[contains(@class, 'schema-filter-control__item') and contains(@data-bind, 'facet.value.from')]"), "Min Size DropDownList");
    private DropDownList ddlMaxScreenSize = new DropDownList(By.xpath("//select[contains(@class, 'schema-filter-control__item') and contains(@data-bind, 'facet.value.to')]"), "Max Size DropDownList");

    public TvPage() {
        super(By.className("b-main-navigation"), "Tv Page");
    }

    public void navigateCatalog(String catalogName){
        Button btnCatalogMenu = new Button(By.xpath(String.format(strCatalogLinkPattern, catalogName)), String.format("Catalog menu %s button", catalogName));
        btnCatalogMenu.click();
    }

    public void assertTitleLabel(){
        assert(lblTvs.isPresent());
    }

    public void selectManufacturer(String name){
        ManufacturerCheckBox chbSamsung = new ManufacturerCheckBox(name, String.format("%s checkbox", name));
        chbSamsung.check(name);
    }

    public void enterMaxPrice(String value){
        txbMaxPrice.type(value);
    }

    public void enterMinDate(String value){
        txbDateFrom.type(value);
    }

    public void selectMinScreenSize(String value) {
        ddlMinScreenSize.selectOptionByVisibleText(value + "\"");
    }

    public void selectMaxScreenSize(String value) {
        ddlMaxScreenSize.selectOptionByVisibleText(value + "\"");
    }

    /**
     * Verifying that all results are relevant to the query
     * @param manufacturer manufacturer
     * @param maxPrice maximum price
     * @param minScreenSize minimum size of the screen
     * @param maxScreenSize maximum size of the screen
     */
    public void checkResultsToBeRelevant(String manufacturer, String maxPrice, String minScreenSize, String maxScreenSize) {
        List<WebElement> resultList = findElements(By.xpath("//div[@class='schema-products']/div[@class='schema-product__group']"));
        info("results size - " + resultList.size());
        for (WebElement result : resultList) {
            verifyResultContainManufacturer(result, manufacturer);
            verifyResultUnderMaxPrice(result, maxPrice);
            verifyResultWithinScreenSize(result, minScreenSize, maxScreenSize);
        }
        info("All results are relevant");
    }

    /**
     * Verifying that result contain manufacturer
     * @param result result element
     * @param manufacturer manufacturer
     */
    private void verifyResultContainManufacturer(WebElement result, String manufacturer) {
        WebElement fullNameElement = result.findElement(By.xpath(".//span[@data-bind='html: product.full_name']"));
        String fullName = fullNameElement.getText();
        Assert.assertTrue(fullName.contains(manufacturer), String.format("\"%s\" does not contain \"%s\"", fullName, manufacturer));
    }

    /**
     * Verifying that result under maximum price
     * @param result result element
     * @param maxPrice maximum price
     */
    private void verifyResultUnderMaxPrice(WebElement result, String maxPrice) {
        WebElement priceElement = result.findElement(By.xpath(".//a[contains(@class, 'value_primary')]//span[contains(@data-bind, 'html: $root.format.minPrice')]"));
        String price = priceElement.getText();
        price = price.substring(0, price.indexOf(","));
        Integer maxPriceInt = Integer.parseInt(maxPrice);
        Integer priceInt = Integer.parseInt(price);
        Assert.assertEquals(maxPriceInt.compareTo(priceInt), 1, String.format("\"%s\" is higher than \"%s\"", price, maxPrice));
    }

    /**
     * Verifying that result with screen size
     * @param result result element
     * @param minScreenSize minimum size of the screen
     * @param maxScreenSize maximum size of the screen
     */
    private void verifyResultWithinScreenSize(WebElement result, String minScreenSize, String maxScreenSize) {
        WebElement priceElement = result.findElement(By.xpath(".//span[contains(@data-bind, 'html: product.description')]"));
        String productDescription = priceElement.getText();
        String screenSize = productDescription.substring(0, productDescription.indexOf("\""));
        Double minScreenSizeValue = Double.parseDouble(minScreenSize);
        Double maxScreenSizeValue = Double.parseDouble(maxScreenSize);
        Double screenSizeValue = Double.parseDouble(screenSize);
        Assert.assertTrue(screenSizeValue >= minScreenSizeValue && screenSizeValue <= maxScreenSizeValue, String.format("\"%s\" is not within \"%s\" and \"%s\"", screenSize, minScreenSize, maxScreenSize));
    }

    public void waitForResultsToRefresh() {
        super.waitSeconds(timeout);
    }

}
