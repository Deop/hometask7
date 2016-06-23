package onliner.tests;

import onliner.pages.CatalogPage;
import onliner.pages.MainPage;
import onliner.pages.TvPage;
import webdriver.BaseTest;
import webdriver.PropertiesResourceManager;

public class SearchTvTest extends BaseTest {

    private String menuName;
    private String catalogName;
    private String manufacturer;
    private String maxPrice;
    private String minDate;
    private String minScreenSize;
    private String maxScreenSize;

    public void runTest() {
        readProperties();

        logger.step(1);
        MainPage mainPage = new MainPage();
        mainPage.assertLogo();

        logger.step(2);
        mainPage.navigateMenu(menuName);
        CatalogPage catalogPage = new CatalogPage();

        logger.step(3);
        catalogPage.navigateCatalog(catalogName);
        TvPage tvPage = new TvPage();
        tvPage.assertTitleLabel();

        logger.step(4);
        tvPage.selectManufacturer(manufacturer);
        tvPage.enterMaxPrice(maxPrice);
        tvPage.enterMinDate(minDate);
        tvPage.selectMinScreenSize(minScreenSize);
        tvPage.selectMaxScreenSize(maxScreenSize);

        logger.step(5);
        tvPage.waitForResultsToRefresh();
        tvPage.checkResultsToBeRelevant(manufacturer, maxPrice, minScreenSize, maxScreenSize);
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void readProperties(){
        PropertiesResourceManager props = new PropertiesResourceManager("searchTvTest.properties");
        this.menuName = props.getProperty("menuName");
        this.catalogName = props.getProperty("catalogName");
        this.manufacturer = props.getProperty("manufacturer");
        this.maxPrice = props.getProperty("maxPrice");
        this.minDate = props.getProperty("minDate");
        this.minScreenSize = props.getProperty("minScreenSize");
        this.maxScreenSize = props.getProperty("maxScreenSize");
    }

}
