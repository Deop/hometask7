package onliner.elements;

import org.openqa.selenium.By;
import webdriver.elements.CheckBox;

public class ManufacturerCheckBox extends CheckBox {


    /**
     * Constructor
     * @param locatorName manufacturer name
     * @param name name
     */

    public ManufacturerCheckBox(final String locatorName, final String name) {
        super(By.xpath(String.format("//li//span[contains(@class, 'schema-filter__checkbox-text') and contains(text(), '%s')]/preceding-sibling::span", locatorName)), name);
    }

}
