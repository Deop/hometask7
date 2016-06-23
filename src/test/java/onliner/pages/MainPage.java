package onliner.pages;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Button;
import webdriver.elements.Label;

public class MainPage extends BaseForm {

    private String btnNavMenuPattern = "//span[contains(text(), '%s')]";
    private Label lbLogo = new Label(By.className("b-top-logo"),"onliner.by logo");

    public MainPage() {
        super(By.className("b-main-navigation"), "Main Navigation Bar");
    }

    public void navigateMenu(String menuName){
        Button btnNavMenu = new Button(By.xpath(String.format(btnNavMenuPattern, menuName)), String.format("Nav menu %s button", menuName));
        btnNavMenu.click();
    }

    public void assertLogo(){
        assert(lbLogo.isPresent());
    }

}
