package edullent.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class SauceDemo {
	
	@Test
    public void loginAddProductAndVerifyCart() {

        WebDriver driver = new ChromeDriver();

        try {
            WebDriverWait wait = new WebDriverWait(
                driver, Duration.ofSeconds(10)
            );

            // Step 1: Open SauceDemo
            driver.get("https://www.saucedemo.com");

            // Step 2: Enter login credentials
            driver.findElement(By.id("user-name"))
                  .sendKeys("standard_user");

            driver.findElement(By.id("password"))
                  .sendKeys("secret_sauce");

            driver.findElement(By.id("login-button")).click();

            // Step 3: Verify Products page
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.className("inventory_list")
            ));

            // Step 4: Add Sauce Labs Backpack to cart
            driver.findElement(
                By.id("add-to-cart-sauce-labs-backpack")
            ).click();

            // Step 5: Verify cart count is 1
            WebElement cartBadge = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                    By.className("shopping_cart_badge")
                )
            );

            String actualCount = cartBadge.getText();

            Assert.assertEquals(
                actualCount, "1",
                "Cart count should be 1"
            );

            System.out.println("Test Passed: Cart count is 1");

        } finally {
            driver.quit();
        }
    }

}
