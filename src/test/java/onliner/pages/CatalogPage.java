package onliner.pages;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Button;

public class CatalogPage extends BaseForm {

    private String catalogLinkPattern = "//a[contains(@class, 'catalog-bar__link') and contains(text(), '%s')]";


    public CatalogPage() {
        super(By.className("b-main-navigation"), "Catalog Navigation Bar");
    }

    public void navigateCatalog(String catalogName){
        Button btnCatalogMenu = new Button(By.xpath(String.format(catalogLinkPattern, catalogName)), String.format("Catalog menu %s button", catalogName));
        btnCatalogMenu.click();
    }

    public void asserCatalogNavigation(){

    }

}
