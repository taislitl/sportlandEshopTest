package baseFunc;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.List;
import java.util.concurrent.TimeUnit;

public class BaseFunc {

    private WebDriver driver;
    private WebDriverWait wait;



    public BaseFunc() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/694056/Downloads/chromedriver_win32/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 25);

    }


    public void openPage(String url) {
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "https://" + url;
        }
        driver.get(url);

    }

    public WebElement getElement(By locator) {
        Assertions.assertFalse(driver.findElements(locator).isEmpty(), "Element is not found");
        return driver.findElement(locator);
    }

    public List<WebElement> getALLElements(By locator) {
        Assertions.assertFalse(driver.findElements(locator).isEmpty(), "Element is not found");
        return driver.findElements(locator);
    }

    public boolean isElementPresent(By locator) {

        return getALLElements(locator).isEmpty();
    }

    public void waitForElement(By locator) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }
    public void waitUntil() {
        driver.manage().timeouts().implicitlyWait(4000, TimeUnit.SECONDS);
    }
    public void waitUntilCbl(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
}
