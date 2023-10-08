package QKART_SANITY_LOGIN.Module1;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResult {
    WebElement parentElement;

    public SearchResult(WebElement SearchResultElement) {
        this.parentElement = SearchResultElement;
    }

    /*
     * Return title of the parentElement denoting the card content section of a search result
     */
    public String getTitleofResult() {
        String titleOfSearchResult = "";
        // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 03: MILESTONE 1
        // Find the element containing the title (product name) of the search result and
        // assign the extract title text to titleOfSearchResult
        titleOfSearchResult = parentElement.getText();
        return titleOfSearchResult;
    }

    /*
     * Return Boolean denoting if the open size chart operation was successful
     */
    public Boolean openSizechart() {
        try {

            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 04: MILESTONE 2
            // Find the link of size chart in the parentElement and click on it
            parentElement.findElement(By.xpath("//button[text()='Size chart']")).click();;
            return true;
        } catch (Exception e) {
            System.out.println("Exception while opening Size chart: " + e.getMessage());
            return false;
        }
    }

    /*
     * Return Boolean denoting if the close size chart operation was successful
     */
    public Boolean closeSizeChart(WebDriver driver) {
        try {
            Thread.sleep(2000);
            Actions action = new Actions(driver);

            // Clicking on "ESC" key closes the size chart modal
            action.sendKeys(Keys.ESCAPE);
            action.perform();
            Thread.sleep(2000);
            return true;
        } catch (Exception e) {
            System.out.println("Exception while closing the size chart: " + e.getMessage());
            return false;
        }
    }

    /*
     * Return Boolean based on if the size chart exists
     */
    public Boolean verifySizeChartExists() {
        Boolean status = false;
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 04: MILESTONE 2
            /*
             * Check if the size chart element exists. If it exists, check if the text of the
             * element is "SIZE CHART". If the text "SIZE CHART" matches for the element, set status
             * = true , else set to false
             */
            status = parentElement.findElement(By.xpath("//button[text()='Size chart']")).getText()
                    .equalsIgnoreCase("SIZE CHART");
            // parentElement.findElement(By.xpath("//div[@role='dialog']"));

            return status;
        } catch (Exception e) {
            return status;
        }
    }

    /*
     * Return Boolean if the table headers and body of the size chart matches the expected values
     */
    public Boolean validateSizeChartContents(List<String> expectedTableHeaders,
            List<List<String>> expectedTableBody, WebDriver driver) {
        Boolean status = true;
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 04: MILESTONE 2

            // * Locate the table element when the size chart modal is open
            WebElement tableElement = driver.findElement(By.xpath("//div[@role='dialog']//table"));
            /*
             * Validate that the contents of expectedTableHeaders is present as the table header in
             * the same order
             */
            List<WebElement> headerElements = tableElement.findElements(By.xpath("//thead/tr/th"));
            Thread.sleep(3000);
            for (int i = 0; i < headerElements.size(); i++) {
                // System.out.println("header: " + i + " value is " +
                // headerElements.get(i).getText());
                // System.out.println("expected value is : " + expectedTableHeaders.get(i));
                if (headerElements.get(i).getText().equalsIgnoreCase(expectedTableHeaders.get(i)))
                    continue;
                else {
                    status = false;
                    break;
                }
            }
            // System.out.println("status value after verifying header is : " + status);
            /*
             * Validate that the contents of expectedTableBody are present in the table body in the
             * same order
             */
            // List<WebElement> tableRows =
            // driver.findElements(By.xpath("//div[@role='dialog']//table/tbody/tr"));
            // Grab the table
            WebElement table = driver.findElement(By.xpath("//div[@role='dialog']//table/tbody"));

            // Now get all the TR elements from the table
            List<WebElement> allRows = table.findElements(By.tagName("tr"));
            // And iterate over them, getting the cells
            // System.out.println("row count is : " + allRows.size());
            Thread.sleep(3000);
            int rowCounter = 0;
            for (WebElement row : allRows) {
                // System.out.println(row.getText());
                List<WebElement> cells = row.findElements(By.tagName("td"));
                // System.out.println("Total number of cells are : " + cells.size());
                List<String> rowString = expectedTableBody.get(rowCounter++);
                int colCounter = 0;
                for (WebElement cell : cells) {
                    // And so on
                    // System.out.println("cell value : " + cell.getText());
                    // System.out.println("expected cell value: " + rowString.get(j));
                    if (cell.getText().equalsIgnoreCase(rowString.get(colCounter++)))
                        continue;
                    else {
                        status = false;
                        break;
                    }
                }
                if (status == false)
                    break;
            }

            return status;

        } catch (Exception e) {
            System.out.println("Error while validating chart contents");
            return false;
        }
    }

    /*
     * Return Boolean based on if the Size drop down exists
     */
    public Boolean verifyExistenceofSizeDropdown(WebDriver driver) {
        Boolean status = false;
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 04: MILESTONE 2
            // If the size dropdown exists and is displayed return true, else return false
            status = driver.findElement(By.xpath("//div/select[1]")).isDisplayed();
            return status;
        } catch (Exception e) {
            return status;
        }
    }
}
