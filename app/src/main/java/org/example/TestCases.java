package org.example;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCases {
    ChromeDriver driver;
    public TestCases(){
         System.out.println("Constructor: TestCases");

        WebDriverManager.chromedriver().timeout(30).setup();
       
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        // Set log level and type
        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);

        // Set path for log file
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "chromedriver.log");

        driver = new ChromeDriver(options);

        // Set browser to maximize and wait
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    public void endTest()
    {
        System.out.println("End Test: TestCases");
       // driver.close();
        driver.quit();

    }

    public void testCase01()throws InterruptedException{

        System.out.println("Start Test case: testCase01");
        driver.get("https://www.99bookscart.com/");
            // Registration Process
        driver.findElement(By.xpath("//a[@href=\"/profile\"]")).click();
        driver.findElement(By.xpath("//button[@type=\"button\"]")).click();
        driver.findElement(By.xpath("(//input[@type=\"Text\"])[1]")).sendKeys("John");
        driver.findElement(By.xpath("(//input[@type=\"Number\"])[1]")).sendKeys("3");
        driver.findElement(By.xpath("//input[@type=\"Email\"]")).sendKeys("johndoe@example.com");
        driver.findElement(By.xpath("//input[@type=\"Password\"]")).sendKeys("SecurePass123");
        driver.findElement(By.xpath("(//input[@type=\"Text\"])[2]")).sendKeys("401, Wilson Apt");
        driver.findElement(By.xpath("(//input[@type=\"Text\"])[4]")).sendKeys("Mumbai");
        driver.findElement(By.xpath("(//input[@type=\"Number\"])[2]")).sendKeys("400 008");

           try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            WebElement element = driver.findElement(By.xpath("//div[@class='MuiFormControl-root MuiFormControl-fullWidth css-tzsjye']"));

            Actions actionbttn = new Actions(driver);

            // actionbttn.click().perform();
            actionbttn.moveToElement(element).click().perform();
            Thread.sleep(3000);

            WebElement selectMaha = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(text(), \"Maharashtra\")] ")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", selectMaha);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", selectMaha);

                //   // Locate the state dropdown using XPath
                //   List<WebElement> states = driver.findElements(By.xpath("(//ul[contains(@class, 'css-r8u8y9')])"));
            
                //   WebElement specificElement = states.get(2);

                //   specificElement.click();
            
            
            System.out.println("State selected successfully!");
        } catch (ElementClickInterceptedException e) {
            // Handle click interception
            System.out.println("Element click intercepted. Trying with JavaScript click.");
            WebElement element = driver.findElement(By.xpath("//*[@id='demo-simple-select']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);

            System.out.println("Error occurred: " + e.getMessage());
        }


        driver.findElement(By.xpath("//input[@type=\"phone\"]")).sendKeys("1234567890");
        System.out.println("Phone number");

        Actions actions = new Actions(driver);
        WebElement element = driver.findElement(By.xpath("//button[@type='submit']"));
        actions.moveToElement(element).click().build().perform();
        
        System.out.println("TestCace 01 Completed");
        

// // Logout after registration
//         driver.findElement(By.linkText("Logout")).click();

// // Login Process
//         driver.findElement(By.linkText("Login")).click();
//         driver.findElement(By.id("input-email")).sendKeys("johndoe@example.com");
//         driver.findElement(By.id("input-password")).sendKeys("SecurePass123");
//         driver.findElement(By.cssSelector("input.btn.btn-primary")).click();


        endTest();

    }

        public boolean testCase02 ()throws InterruptedException{

        System.out.println("Start Test case: testCase02");
        driver.get("https://www.99bookscart.com/");

        try{
            WebElement searchIcon = driver.findElement(By.xpath("//a[@name=\"search\"]"));
            searchIcon.click();

            WebElement searchBox = driver.findElement(By.xpath("//input[@placeholder=\"Title,Author & ISBN\"]"));

            String bookTitle = "Winners";
            searchBox.sendKeys(bookTitle);

            WebElement submitIcon = driver.findElement(By.xpath("//button[@class=\"search-button-mobile btn btn-outline-success\"]"));
            submitIcon.click();

            Thread.sleep(5000);

            String xpathExpression = "//a[contains(@href, '" + bookTitle + "')]";

                 // Step 4: Check for results on the next page      //a[contains(@href, 'winners')]   (//a[contains(@href, 'winners')])[2]
                 WebElement result = driver.findElement(By.xpath(xpathExpression)); // Adjust XPath as needed
                 if (result != null) {
                     System.out.println("Title found. Clicking on it...");
                     Actions actions = new Actions(driver);
                     actions.moveToElement(result).click().build().perform();
                     result.click();
     
                     // Step 5: Verify the description of the book  ("//a[contains(text(), '" + bookTitle + "')]"));   ("//a[contains(@href, '" +bookTitle+ "')]")
                     WebElement description = driver.findElement(By.xpath("(//div[@class=\"container\"]//ancestor::div//child::h2)[1]")); // Use the actual locator for the description
                     if (description != null) {
                         System.out.println("Description found: " + description.getText());
                     } else {
                         System.out.println("No description found for the book.");
                     }
                 } else {
                     System.out.println("No result found for the title.");
                 }

           return true;
        }catch(Exception e){

        }
        endTest();
        System.out.println("End test case");
     
        return false;

        }

        public void testCase03(){
            System.out.println("Start Test case: testCase03");
            driver.get("https://www.99bookscart.com/");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            WebElement closeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[contains( @class , \\\"css-5bcq5j\\\")])[1]"))); // Adjust XPath as needed

            // Click the close button on the custom modal
            closeButton.click();

        
            int numberOfButtonsToClick = 5; // Number of buttons to click
            for (int i = 0; i < numberOfButtonsToClick; i++) {
                // Refresh the list of "Add" buttons
                List<WebElement> addButtons = driver.findElements(By.xpath("//button[contains(text(),'Add')]"));
                
                if (addButtons.size() > i) {
                    WebElement button = addButtons.get(i);
                    Actions actions = new Actions(driver);
                    actions.moveToElement(button).click().build().perform();
                    // Optional: Capture or process the WebElement if needed
                    System.out.println("Clicked button: " + button.getText());
                    
                    // Optionally, wait for the page to update or new elements to appear if needed
                    // e.g., Thread.sleep(2000); or WebDriverWait for specific condition
                } else {
                    System.out.println("Less than " + numberOfButtonsToClick + " 'Add' buttons found.");
                    break; // Exit loop if there are fewer buttons than expected
                }
            }
               // Navigate to the cart
                WebElement cartButton = driver.findElement(By.xpath("//a[@href=\"/cart\" and @name='cart']"));
                Actions actions = new Actions(driver);
                actions.moveToElement(cartButton).click().build().perform();

             
                try {
                    List<WebElement> bookTitleElements = driver.findElements(By.xpath("//div[@class=\"cart-item-title\"]"));

                    for (WebElement bookTitleElement : bookTitleElements) {
                        System.out.println("Book Title: " + bookTitleElement.getText());
                    }
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
  
               // Retrieve item prices and calculate total
            List<WebElement> prices = driver.findElements(By.xpath("//*[@class='cart-productPrice']"));
            double calculatedTotal = 0.0;
            for (WebElement priceElement : prices) {
                try {
                    String priceText = priceElement.getText().replace("$", "").trim();
                    // Remove non-numeric characters (like '?') before parsing
                    priceText = priceText.replaceAll("[^0-9.]", "");
                    double price = Double.parseDouble(priceText);
                    calculatedTotal += price;
                    System.err.println("Adding price: " + price);
                    System.err.println("Calculated total: " + calculatedTotal);
                } catch (NumberFormatException e) {
                    System.err.println("Error parsing price: " + priceElement.getText());
                }
            }

            // Retrieve displayed total from the cart
            String totalText = driver.findElement(By.xpath("(//*[@class=\"list-group list-group-flush\"]//div[2]//span)[1]")).getText().replace("$", "").trim();
            // Remove non-numeric characters (like '?') before parsing
            totalText = totalText.replaceAll("[^0-9.]", "");
            double displayedTotal = Double.parseDouble(totalText);

            System.err.println("Displayed total: " + displayedTotal);

            // Verify the calculated total matches the displayed total
            if (Math.abs(calculatedTotal - displayedTotal) < 0.01) { // Using a tolerance for floating-point comparison
                System.out.println("Total price verification passed.");
            } else {
                System.out.println("Total price verification failed.");
                System.err.println("Calculated total: " + calculatedTotal);
                System.err.println("Displayed total: " + displayedTotal);
            }

        
        }

        public void testCase04(){
            // Add one more item
driver.findElement(By.name("search")).clear();
driver.findElement(By.name("search")).sendKeys("Hamlet");
driver.findElement(By.cssSelector("button.btn.btn-default.btn-lg")).click();
driver.findElement(By.xpath("//button[@data-original-title='Add to Cart']")).click();

// Recalculate total
// Repeat the price retrieval and calculation steps from above

        }


        public void testCase05(){
            // Navigate to the cart
driver.findElement(By.id("cart-total")).click();
driver.findElement(By.linkText("View Cart")).click();

// Remove the first item
driver.findElement(By.xpath("//button[@data-original-title='Remove']")).click();

// Recalculate total
// Repeat the price retrieval and calculation steps from above

        }
}