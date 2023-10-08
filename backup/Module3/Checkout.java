package QKART_SANITY_LOGIN.Module1;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Checkout {
    RemoteWebDriver driver;
    String url = "https://crio-qkart-frontend-qa.vercel.app/checkout";

    public Checkout(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public void navigateToCheckout() {
        if (!this.driver.getCurrentUrl().equals(this.url)) {
            this.driver.get(this.url);
        }
    }

    /*
     * Return Boolean denoting the status of adding a new address
     */
    public Boolean addNewAddress(String addresString) {
        try {
            
            //TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 05: MILESTONE 4
            /*
             * Click on the "Add new address" button, enter the addressString in the address text
             * box and click on the "ADD" button to save the address
             */
            WebElement addAddress = driver.findElement(By.id("add-new-btn"));
            addAddress.click();
            // textarea[@placeholder='Enter your complete address']
            WebElement addressTextArea = driver.findElement(
                    By.xpath("//textarea[@placeholder='Enter your complete address']"));
            addressTextArea.sendKeys(addresString);
            WebElement addButton = driver.findElement(By.xpath("//button[text()='Add']"));
            addButton.click();
            return true;
        } catch (Exception e) {
            System.out.println("Exception occurred while entering address: " + e.getMessage());
            return false;

        }
    }

    /*
     * Return Boolean denoting the status of selecting an available address
     */
    public Boolean selectAddress(String addressToSelect) {
        try {
            
            //TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 05: MILESTONE 4
            /*
             * Iterate through all the address boxes to find the address box with matching text,
             * addressToSelect and click on it
             */
            // div[contains(@class,'address-item selected')]
            List<WebElement> addressRadioButtons =
                    driver.findElements(By.xpath("//input[@name='address']"));
            List<WebElement> addresses = driver.findElements(
                    By.xpath("//input[@name='address']/parent::span/following-sibling::p"));
            int totalAddresses = addresses.size();
            for (int i = 0; i < totalAddresses; i++) {
                if (addresses.get(i).getText().equalsIgnoreCase(addressToSelect)) {
                    addressRadioButtons.get(i).click();
                    return true;
                }
            }
            System.out.println("Unable to find the given address");
            return false;
        } catch (Exception e) {
            System.out.println(
                    "Exception Occurred while selecting the given address: " + e.getMessage());
            return false;
        }

    }

    /*
     * Return Boolean denoting the status of place order action
     */
    public Boolean placeOrder() {
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 05: MILESTONE 4
            // Find the "PLACE ORDER" button and click on it
            WebElement placeOrderButton =
                    driver.findElement(By.xpath("//button[contains(text(),'PLACE ORDER')]"));
            placeOrderButton.click();
            return true;

        } catch (Exception e) {
            System.out.println("Exception while clicking on PLACE ORDER: " + e.getMessage());
            return false;
        }
    }

    /*
     * Return Boolean denoting if the insufficient balance message is displayed
     */
    public Boolean verifyInsufficientBalanceMessage() {
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 08: MILESTONE 7
            // boolean status = driver.findElement(By.id("notistack-snackbar")).isDisplayed();
            WebElement errorElement = driver.findElement(By.xpath(
                    "//div[text()='You do not have enough balance in your wallet for this purchase']"));
            // System.out.println(errorElement.getText());
            boolean status = errorElement.getText()
                    .contains("You do not have enough balance in your wallet for this purchase");
            return status;
        } catch (Exception e) {
            System.out.println(
                    "Exception while verifying insufficient balance message: " + e.getMessage());
            return false;
        }
    }
}
