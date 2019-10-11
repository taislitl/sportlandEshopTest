package pages;

import baseFunc.BaseFunc;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


import java.io.*;
import java.io.FileWriter;
import java.util.List;


public class FutbolsApavi {
    private BaseFunc baseFunc;

    private final By PERCENTAGE = By.xpath("//p[@class='spodb-product-card__percentage']");
    private final By PRODUCTSAMOUNT = By.xpath("//p[@class='spodb-product-card__title']");
    private final By FILTER = By.xpath("//select");
    private final By BRANDBTN = By.xpath("//div/fieldset/ol/li/label[@for='85']");
    private final By BRANDNAME = By.xpath("//div/a/p[@class='spodb-product-card__title']");
    private final By PRICE = By.xpath("//p[@class='spodb-product-card__new-price']");


    public FutbolsApavi(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }


    public void sortByValue(String value) throws InterruptedException {
        baseFunc.waitForElement(FILTER);
        Select sale = new Select(baseFunc.getElement(FILTER));
        sale.selectByVisibleText(value);
        Thread.sleep(4000);

    }

    public void selectFilter() throws InterruptedException {
        baseFunc.getElement(BRANDBTN).click();
        Thread.sleep(4000);
    }


    public void verifyAllProductsForSale() {

        List<WebElement> percentage = baseFunc.getALLElements(PERCENTAGE);
        List<WebElement> count = baseFunc.getALLElements(PRODUCTSAMOUNT);
        for (int i = 0; i < percentage.size(); i++) {
            for (int j = 0; j < count.size(); j++) {
                Assertions.assertTrue(percentage.size() == count.size(), "all products have cheapest price");
            }
            break;
        }
    }

    public void verifyBrandForAllProducts() {
        List<WebElement> brand = baseFunc.getALLElements(BRANDNAME);
        for (int i = 0; i < brand.size(); i++) {

            Assertions.assertTrue(brand.get(i).getText().contains("NIKE"), "selected products have incorrect Brand Name");

        }
    }

    public void findProductsAndSave(double price) {

        File filedata = new File("C:/Users/694056/Desktop/TestSportland/test.txt");
        try (FileWriter writer = new FileWriter(filedata, false)) {

            List<WebElement> products = baseFunc.getALLElements(PRICE);
            List<WebElement> name = baseFunc.getALLElements(BRANDNAME);
            double actualPrice;
            for (int i = 0; i < products.size(); i++) {
                String prodname = name.get(i).getText();
                String test = products.get(i).getText().substring(0, 5);
                actualPrice = Double.parseDouble(test);
                if (actualPrice < price) {
                    writer.write("Product name: " + prodname + " Product price: " + actualPrice + "\r\n");
                }

            }
            writer.flush();
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }

    public void findProductsAndSaveJson(double price) {
        File pdata = new File("C:/Users/694056/Desktop/TestSportland/Write.json");
        try (FileWriter pWriter = new FileWriter(pdata, false)) {

            JSONObject jObj = new JSONObject();
            JSONArray list = new JSONArray();

            List<WebElement> products = baseFunc.getALLElements(PRICE);
            List<WebElement> name = baseFunc.getALLElements(BRANDNAME);
            double actualPrice;
            for (int i = 0; i < products.size(); i++) {
                String prodname = name.get(i).getText();
                String test = products.get(i).getText().substring(0, 5);
                actualPrice = Double.parseDouble(test);
                if (actualPrice < price) {

                    jObj.put(prodname, actualPrice);
                    list.add(jObj);
                    pWriter.write(jObj.toJSONString());

                }
                pWriter.flush();
            }

        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }


    }


}