package locator;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import model.DataTableCourse;

public class LocatorTask2 {


    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Belajar QA\\qa-web-automation\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        //setup to link website
        driver.get("https://rahulshettyacademy.com/AutomationPractice");


        //1. SELECT RADIO BUTTON
       WebElement valueRadioButton = driver.findElement(By.xpath("//input[@value='radio2']"));
        System.out.println("Total radio button: " + valueRadioButton);
        valueRadioButton.click();
        Thread.sleep(800);


        //2. SELECT COMBO BOX
        driver.findElement(By.xpath("//input[@id='autocomplete']")).sendKeys("Ind");
        Thread.sleep(800);
        List<WebElement> options = driver.findElements(By.xpath("//li[@class='ui-menu-item']/child::div"));

        for (WebElement option : options) {
            System.out.println("Item: " + option.getText());
            if (option.getText().equalsIgnoreCase("Indonesia")) {
                option.click();
                break;
            }
        }
        System.out.println("Total options: " + options);
        Thread.sleep(800);


        //3. SELECT DROPDOWN
        driver.findElement(By.xpath("//select[@id='dropdown-class-example']")).click();

        List<WebElement> dropdown = driver.findElements(By.xpath("//select[@id='dropdown-class-example']//option"));

        for(WebElement item : dropdown){
            System.out.println("Item option: " + item.getText());
            if(item.getText().equalsIgnoreCase("Option2")){
                item.click();
                break;
            }
        }
        

        //4. CHECKBOX
        driver.findElement(By.xpath("//input[@id='checkBoxOption3']")).click();
        Thread.sleep(800);


        //5. POPUP WINDOW
        driver.findElement(By.xpath("//button[@id='openwindow']")).click();
        Object[] windows = driver.getWindowHandles().toArray();
        System.out.println("Windowss: " + windows);
        driver.switchTo().window(windows[1].toString());
        Thread.sleep(4000);
        WebElement validatePopUp = driver.findElement(By.xpath("//div[@class='cont']//span[contains(text(),'info@qaclickacademy.com')]"));

        if(validatePopUp.getText().equalsIgnoreCase("info@qaclickacademy.com")){
            Assert.assertTrue(true, "Pop up window is displayed");
            System.out.println("Pop up window is displayed");
            driver.close();
            driver.switchTo().window(windows[0].toString());
        }else{
            Assert.assertTrue(false, "Pop up window is not displayed");
            System.out.println("Pop up window is not displayed");
            driver.close();
            driver.switchTo().window(windows[0].toString());
        }
        Thread.sleep(800);


        // 6. OPEN TAB
        driver.findElement(By.xpath("//a[@id='opentab']")).click();
        Object[] tabs = driver.getWindowHandles().toArray();
        System.out.println("Tabs: " + tabs);
        driver.switchTo().window(tabs[1].toString());
        String titleTab = driver.getTitle();
        Assert.assertEquals(titleTab, "QAClick Academy - A Testing Academy to Learn, Earn and Shine");
        Thread.sleep(2000);
        driver.switchTo().window(tabs[0].toString());
        Thread.sleep(800);


        //7. ALERT POPUP
        String myName = "Anas Ardiansyah";
        driver.findElement(By.xpath("//input[@placeholder='Enter Your Name']")).sendKeys(myName);
        driver.findElement(By.id("alertbtn")).click();
        Thread.sleep(800);
        String messageAlert = driver.switchTo().alert().getText();
        System.out.println("Alert message is displayed: " + messageAlert);
        driver.switchTo().alert().accept();
        Thread.sleep(1000);


        //for Confirm Popup
        driver.findElement(By.xpath("//input[@placeholder='Enter Your Name']")).sendKeys(myName);
        driver.findElement(By.id("confirmbtn")).click();
        Thread.sleep(800);
        String messageConfirm = driver.switchTo().alert().getText();
        Assert.assertTrue(messageConfirm.contains(myName), "Confirm message is displayed");
        driver.switchTo().alert().accept();
        Thread.sleep(800);


        //8. WEB TABLE
        List<WebElement> rows = driver.findElements(By.xpath("//table[@id='product' and @name='courses']//tbody"));
        List<String> dataTable = new ArrayList<>(3);

        for (int rowCount = 0; rowCount < rows.size(); rowCount++) {
            List<WebElement> columns = rows.get(rowCount).findElements(By.tagName("td"));
            for (int columnCount = 0; columnCount < columns.size(); columnCount++) {
                dataTable.add(columns.get(columnCount).getText());
                if (dataTable.size() == 3) {
                    System.out.println("dataTable: " + dataTable);
                    DataTableCourse data = new DataTableCourse(dataTable.get(0), dataTable.get(1), dataTable.get(2));
                    data.validateDataNullorEmpty();
                    data.printData();
                    dataTable.clear();
                }
            }
        }
        Thread.sleep(800);


        //9. HIDE AND SHOW ELEMENT
        js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.id("hide-textbox")));
        Thread.sleep(800);
        driver.findElement(By.id("hide-textbox")).click();
        Thread.sleep(1000);
        WebElement hideElement = driver.findElement(By.xpath("//input[@id='displayed-text']"));
        Assert.assertFalse(hideElement.isDisplayed(), "Element is hidden");
        Thread.sleep(800);
        
        driver.findElement(By.id("show-textbox")).click();
        Thread.sleep(1000);
        Assert.assertTrue(hideElement.isDisplayed(), "Element is displayed");
        Thread.sleep(800);


        //10. TABLE FIXED HEADER
        List<WebElement> tableFixedBody = driver.findElements(By.xpath("//table[@id='product']//tbody"));
        WebElement targetScroll = driver.findElement(By.xpath("//table[@id='product']//tbody//tr" + "[" + (tableFixedBody.size() - 1) + "]"));
        
        js.executeScript("arguments[0].scrollIntoView();", targetScroll);
        Thread.sleep(2000);

        WebElement scrollDiv = driver.findElement(By.xpath("//div[@class='tableFixHead']"));
        js.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight", scrollDiv);
        Thread.sleep(2000);
        List<WebElement> tableFixedHeader = driver.findElements(By.xpath("//table[@id='product']//thead//tr"));

        for (WebElement tableFixed : tableFixedHeader) {
            for (WebElement tableFixedItem : tableFixed.findElements(By.tagName("th"))) {
                Assert.assertTrue(tableFixedItem.isDisplayed(), "Table Fixed Header is displayed");
            }
        }
        Thread.sleep(800);


        //12. MOUSE HOVER
        WebElement mouseHoverTarget = driver.findElement(By.xpath("//button[@id='mousehover']"));
        js.executeScript("arguments[0].scrollIntoView();", mouseHoverTarget);
        Thread.sleep(800);

        Actions action = new Actions(driver);

        action.moveToElement(mouseHoverTarget).build().perform();

        List<WebElement> mouseHoverOptions = driver.findElements(By.xpath("//div[@class='mouse-hover-content']//a"));
        Thread.sleep(1000);

        for (WebElement mouseHoverOption : mouseHoverOptions) {
            System.out.println("Mouse Hover Option: " + mouseHoverOption.getText());
            if (mouseHoverOption.getText().equalsIgnoreCase("Reload")) {
                mouseHoverOption.click();
                Thread.sleep(2000);
                break;
            }
        }
        Thread.sleep(800);


        //12. IFRAME
        Assert.assertTrue(driver.getPageSource().contains("iframe"), messageConfirm);
        js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.id("courses-iframe")));
        driver.switchTo().defaultContent();
        driver.switchTo().frame(driver.findElement(By.id("courses-iframe")));

        List<WebElement> iframeNavbar = driver.findElements(By.xpath("//ul[@class='navigation clearfix']//li"));

        for (WebElement iframeNav: iframeNavbar) {
            System.out.println("Iframe Nav: " + iframeNav.getText());
            if (iframeNav.getText().equalsIgnoreCase("Practice")) {
                iframeNav.click();
                Thread.sleep(4000);
                WebElement validationIframePractice = driver.findElement(By.tagName("h2"));
                Assert.assertTrue(validationIframePractice.getText().contains("Join now to Practice"), "Iframe Practice is displayed");
                break;
            }
        }
        Thread.sleep(800);
        driver.switchTo().defaultContent();
        Thread.sleep(800);

        
        driver.close();
        driver.quit();

    }

}
