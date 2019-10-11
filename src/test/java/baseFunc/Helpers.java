package baseFunc;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Helpers {

    private BaseFunc baseFunc;

    private final By CATEGORY = By.xpath("//ul[@id='menu-product-menu1']/li");


    public Helpers(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }


    public void getProductKind(String category, String apavi, String futbols) {
        List<WebElement> categories = baseFunc.getALLElements(CATEGORY);
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getText().equals(category)) {
                categories.get(i).click();
                List<WebElement> products = baseFunc.getALLElements(By.tagName("a"));
                for (int j = 0; j < products.size(); j++) {
                    if (products.get(j).getText().equals(apavi)) {
                        for (int k = j + 1; k < products.size(); k++) {
                            if (products.get(k).getText().equals(futbols)) {
                                products.get(k).click();
                                break;
                            }
                        }
                        break;
                    }
                }
                break;
            }
        }

    }

}
