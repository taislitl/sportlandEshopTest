package pages;

import baseFunc.BaseFunc;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class HomePage {

    private BaseFunc baseFunc;
    private final By MENUBTN = By.xpath(".//button[@class='menu-trigger js-toggle-menu']");
    private final By MENU = By.xpath(".//ul[@id='menu-primary-menu']/li");
    private final By BANNER = By.xpath(".//div[@class='owl-item active']//div[@class='campaign-carousel-slides__slide']");

    public HomePage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
        Assertions.assertFalse(baseFunc.isElementPresent(BANNER), "Banner is not present");
    }

    public void openMainMenu() {
        baseFunc.getElement(MENUBTN).click();
    }

    public void selectProducts(String products) {
        baseFunc.waitForElement(MENU);
        List<WebElement> tabs = baseFunc.getALLElements(MENU);
        for (int i = 0; i < tabs.size(); i++) {
            if (tabs.get(i).getText().contains(products)) {
                tabs.get(i).click();
                break;
            }
        }
    }
}