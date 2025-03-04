package com.omar.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.omar.base.BaseTest;
import com.omar.pageObjects.AmazonStore;

public class AmazonStoreTest extends BaseTest {

    @Test
    public void amazonShoppingTest() throws InterruptedException {
        AmazonStore amazon = new AmazonStore(driver);
        Actions actions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // // Step 1: Login - too many issues such as OTP, verification codes, data sensitivity. will skip
        // amazon.login("test@test.com", "password");

        // Step 2: Open all menu and expand
        driver.findElement(By.id("nav-hamburger-menu")).click();
        driver.findElement(By.xpath("//a[contains(@class, 'hmenu-item') and div[text()='See all']]")).click();
        System.out.println("BINGO BONGO SHINGO1");
        // Step 3: Click "Video Games" → "All Video Games"
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@class, 'hmenu-item') and div[text()='Video Games']]"))).click();

        WebElement allVideoGames = driver.findElement(By.xpath("//a[@class='hmenu-item' and text()='All Video Games']"));
        actions.moveToElement(allVideoGames).click().perform();

        // Step 4: Apply filters: "Free Shipping" & "New Condition"
        
        WebElement freeShipping = driver.findElement(By.xpath("//input[@type='checkbox' and @aria-labelledby='Free Shipping']"));
        actions.moveToElement(freeShipping).click().perform();

        driver.findElement(By.xpath("//span[text()='New']")).click();

        // Step 5: Sort by Price: High to Low
        WebElement sortDropdown = driver.findElement(By.id("s-result-sort-select"));
        actions.moveToElement(sortDropdown).click().perform();

        Thread.sleep(1000);

        driver.findElement(By.xpath("//a[@class='a-dropdown-link' and contains(text(),'Price: High to Low')]")).click();
        System.out.println("BINGO BONGO SHINGO2");
        // Step 6: Add all products below 15k EGP
        amazon.addProductsBelowPrice(15000);

        // Step 7: Verify all items are in the cart
        Assert.assertTrue(amazon.verifyCartItems(), "Cart is empty!");

        Thread.sleep(100000);

        // Step 8: Proceed to checkout with cash payment

        // Step 9: Verify total amount with shipping fee
        Assert.assertTrue(amazon.verifyTotalAmount(15000), "Total amount mismatch!");
    }
}
