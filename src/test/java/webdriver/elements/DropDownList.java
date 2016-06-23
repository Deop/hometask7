package webdriver.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DropDownList extends BaseElement {

    /**
     * Constructor
     * @param locator locator
     * @param name name
     */
    public DropDownList(final By locator, final String name) {
        super(locator, name);
    }

    protected String getElementType() {
        return getLoc("loc.dropdown.list");
    }

    public WebElement getOptionElement(By locator){
        return element.findElement(locator);
    }

    /**
     * Selecting an option in dropdown by visible text
     * @param option visible text of the option
     */
    public void selectOptionByVisibleText(String option){
        waitForIsElementPresent();
        if (browser.getDriver() instanceof JavascriptExecutor) {
            ((JavascriptExecutor)browser.getDriver()).executeScript("arguments[0].style.border='3px solid red'", element);
        }

        Select dropdown = new Select(element);
        dropdown.selectByVisibleText(option);
    }
}
