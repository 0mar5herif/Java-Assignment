package com.omar.pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class AmazonStore {
    WebDriver driver;
    WebDriverWait wait;
    Actions actions;

    // Locators
    private final By signInButton = By.id("nav-link-accountList");
    private final By emailField = By.id("ap_email");
    private final By passwordField = By.id("ap_password");
    private final By continueButton = By.id("continue");
    private final By loginButton = By.id("signInSubmit");

    private final By productPrice = By.cssSelector(".a-price-whole");
    private final By addToCartButton = By.xpath("//button[contains(@class, 'a-button-text') and contains(text(), 'Add to cart')]");
    private final By nextPage = By.xpath("//a[contains(@class,'s-pagination-next')]");
    private final By cartIcon = By.id("nav-cart");
    private final By proceedToCheckout = By.name("proceedToRetailCheckout");
    private final By cashOnDelivery = By.xpath("//span[contains(text(), 'Cash on Delivery (COD)')]");
    private final By orderTotal = By.cssSelector("#sc-subtotal-amount-buybox");

    // webdriver init
    public AmazonStore(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions = new Actions(driver);
    }

    // methods
    public void login(String email, String password) {
        driver.findElement(signInButton).click();
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(continueButton).click();
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    public void navigateAllVideoGames() {
    }

    public void addProductsBelowPrice(int maxPrice) throws InterruptedException {
        boolean foundAffordable = false;
    //dont use do while
        do {
            Thread.sleep(3000);
            List<WebElement> prices = driver.findElements(productPrice);
            List<WebElement> addToCartButtonsList = driver.findElements(addToCartButton);
    
            if (prices.isEmpty()) {
                System.out.println("No products found on this page.");
                break;
            }
            System.out.println(prices.size());
            // Loop through each product and check the price
            for (int i = 0; i < prices.size(); i++) {
                try {
                    String priceText = prices.get(i).getText().replace(",", "").trim();
                    int price = Integer.parseInt(priceText);
    
                    // If the product price is below the max price, add it to the cart
                    if (price < maxPrice) {
                        addToCartButtonsList.get(i).click();
                        Thread.sleep(1000);
                        // actions.moveToElement(addToCartButtonsList.get(i)).click().perform();
                        foundAffordable = true;
                        System.out.println(foundAffordable);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Skipping invalid price: " + prices.get(i).getText());
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Skipping due to missing 'Add to Cart' button.");
                }
            }
    
            // If we did not find affordable products, move to the next page
            if (!foundAffordable) {
                System.out.println(foundAffordable);
                // If no products were added to the cart, move to the next page
                List<WebElement> nextPageElements = driver.findElements(nextPage);
                if (!nextPageElements.isEmpty()) {
                    nextPageElements.get(0).click();
                } else {
                    break; // No more pages
                }
            }
        } while (!foundAffordable);
    }

    public boolean verifyCartItems() {
        driver.findElement(cartIcon).click();
        return driver.findElements(By.xpath("//input[@value='Delete']")).size() > 0;
    }

    public void proceedToCheckout() {
        driver.findElement(proceedToCheckout).click();
        driver.findElement(cashOnDelivery).click();
    }

    public boolean verifyTotalAmount(int expectedTotal) {
        String totalText = driver.findElement(orderTotal).getText().replace(",", "");
        int totalAmount = Integer.parseInt(totalText);
        return totalAmount == expectedTotal;
    }
}