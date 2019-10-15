package stepDefs;

import baseFunc.BaseFunc;
import baseFunc.Helpers;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.FutbolsApavi;
import pages.HomePage;


public class TestStepDefs {
    private HomePage homePage;
    private BaseFunc baseFunc = new BaseFunc();
    private Helpers helpers = new Helpers(baseFunc);
    FutbolsApavi futbolsApavi = new FutbolsApavi(baseFunc);


    @Given("open {string} home page")
    public void open_home_page(String url) {
        baseFunc.openPage(url);
        homePage = new HomePage(baseFunc);
    }

    @When("from main menu select {string}")
    public void select_products_in_menu(String item) {
        homePage.openMainMenu();
        homePage.selectProducts(item);
    }

    @When("select {string} category, {string} subcategory for {string} product")
    public void select_fulbols_apavi(String category, String subcategory, String productkind) {
        helpers.getProductKind(category, subcategory, productkind);

    }

    @When("sort by {string}")
    public void sort_by_some_param(String value) {
        futbolsApavi.sortByValue(value);
        futbolsApavi.selectFilter();
    }

    @Then("verify are all products for sale")
    public void verify_all_selected_products() {
        futbolsApavi.verifyAllProductsForSale();
        futbolsApavi.verifyBrandForAllProducts();
    }

    @Then("save text file with products price less than {double} EUR")
    public void save_products_with_price_below_50(double price) {
        futbolsApavi.findProductsAndSave(price);
    }

    @Then("save json file with products price less than {double} EUR")
    public void json_save_products_with_price_below_50(double price) {
        futbolsApavi.findProductsAndSaveJson(price);
    }


}



