package com.framework.stepdefinitions;
import com.framework.pages.saucedemo.ProductPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.framework.drivers.DriverManager;
import org.testng.Assert;

import java.util.Comparator;
import java.util.List;

public class InventorySteps {


    private ProductPage productPage;

    private ProductPage getProductPage(){
        if (productPage == null){
            productPage = new ProductPage(DriverManager.getDriver());
        }
        return productPage;
    }

    @When("I add one item to the cart")
    public void i_add_one_item_to_the_cart() {

        getProductPage().addToCart("Sauce Labs Backpack");
        Assert.assertTrue(getProductPage().isInCart("Sauce Labs Backpack"), "Backpack was not added to the cart");

    }

    @Then("the cart badge should show {int}")
    public void the_cart_badge_should_show(int expectedCount) {
        Assert.assertEquals(getProductPage().getCartCount(), expectedCount);
    }

    @Given("I have added one item to the cart")
    public void iHaveAddedOneItemToTheCart() {
        getProductPage().addToCart("Sauce Labs Bike Light");

    }

    @When("I remove the item from the cart")
    public void iRemoveTheItemFromTheCart() {

        getProductPage().removeFromCart("Sauce Labs Bike Light");

    }

    @When("I add {int} items to the cart")
    public void iAddItemsToTheCart(int itemsToAdd) {
        getProductPage().addToCart("Sauce Labs Bike Light");
        getProductPage().addToCart("Sauce Labs Backpack");
        Assert.assertEquals(getProductPage().getCartCount(), itemsToAdd);
    }


    @When("I select {string} from the sort dropdown")
    public void iSelectFromTheSortDropdown(String sortOption) {
        getProductPage().sortBy(sortOption);

    }

    @Then("the products should be sorted by {string}")
    public void theProductsShouldBeSortedBy(String sortOption) {
        List<String> names = getProductPage().getProductNames();
        List<Double> prices = getProductPage().getProductPrices();


        switch (sortOption) {
            case "Name (A to Z)" -> Assert.assertEquals(names, names.stream().sorted().toList(), "Products are not sorted A to Z");

            case "Name (Z to A)" -> Assert.assertEquals(names, names.stream().sorted(Comparator.reverseOrder()).toList(), "Products are not sorted Z to A");

            case "Price (low to high)" -> Assert.assertEquals(prices, prices.stream().sorted().toList(), "Products are not sorted by price low to high");

            case "Price (high to low)" -> Assert.assertEquals(prices, prices.stream().sorted(Comparator.reverseOrder()).toList(), "Products are not sorted by price high to low");

            default -> Assert.fail("Unknown sort option: " + sortOption);
        }
    }
}
