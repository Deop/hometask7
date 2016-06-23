package webdriver.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

public class CheckBox extends BaseElement {

    /**
     * Constructor
     * @param locator locator
     */
    public CheckBox(final By locator) {
        super(locator);
    }

    /**
     * Constructor
     * @param locator locator
     */
    public CheckBox(final String locator, final String name) {
        super(locator, name);
    }

    /**
     * Constructor
     * @param locator locator
     * @param name name
     */
    public CheckBox(final By locator, final String name) {
        super(locator, name);
    }

    /**
     * Check the checkbox
     */
    public void check(String name){
        waitForIsElementPresent();
        info(String.format(getLoc("loc.checkbox.checking") + " '%1$s' checkbox", name));
        if (browser.getDriver() instanceof JavascriptExecutor) {
            ((JavascriptExecutor)browser.getDriver()).executeScript("arguments[0].style.border='3px solid red'", element);
        }
        element.click();
    }

    protected String getElementType() {
        return getLoc("loc.check.box");
    }

}
