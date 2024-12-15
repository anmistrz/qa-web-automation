package locator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.checkerframework.checker.units.qual.s;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class LocatorTask1 {

    public static void main(String[] args) throws InterruptedException, ParseException {
        System.setProperty("webdriver.chrome.driver", "C:\\Belajar QA\\qa-web-automation\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        JavascriptExecutor js = (JavascriptExecutor) driver;

        //setup to link website
        driver.get("https://rahulshettyacademy.com/angularpractice/");

        //1. FILL NAME
        String name = "Test Anas";
        WebElement elementName = driver.findElement(By.xpath("//input[@name='name']"));
        elementName.sendKeys(name);
        Thread.sleep(800);

        //2. FILL EMAIL
        String email = "testanas@gmail.com";
        WebElement elementEmail = driver.findElement(By.xpath("//input[@name='email']"));
        elementEmail.sendKeys(email);
        Thread.sleep(800);

        //3. FILL PASSWORD
        String password = "123456";
        WebElement elementPassword = driver.findElement(By.xpath("//input[@id='exampleInputPassword1']"));
        elementPassword.sendKeys(password);
        Thread.sleep(800);

        //4. SELECT CHECKBOX
        WebElement elementCheckbox = driver.findElement(By.id("exampleCheck1"));
        elementCheckbox.click();
        boolean checkbox = driver.findElement(By.id("exampleCheck1")).isSelected();
        Thread.sleep(800);

        //5. SELECT GENDER
        WebElement elementGender = driver.findElement(By.xpath("//select[@id='exampleFormControlSelect1']"));
        elementGender.click();
        List<WebElement> options = driver.findElements(By.xpath("//select[@id='exampleFormControlSelect1']//option"));
        String gender = null;
        for (WebElement option : options) {
            if(option.getText().equalsIgnoreCase("Male")){
                option.click();
                gender = option.getText();
                break;
            }
        }
        Thread.sleep(800);

        //6. EMPLOYMENT STATUS
        WebElement elementEmployment = driver.findElement(By.xpath("//input[@id='inlineRadio1']"));
        elementEmployment.click();
        boolean employment = driver.findElement(By.xpath("//input[@id='inlineRadio1']")).isSelected();
  
        //7. SELECT DATE OF BIRTH
        WebElement elementDateOfBirth = driver.findElement(By.xpath("//input[@name='bday']"));
        elementDateOfBirth.sendKeys("01/01/2000");
        Date dateOfBirth = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2000");
        Thread.sleep(800);


        //8. SUBMIT DATA
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Password: " + password);
        System.out.println("Checkbox: " + checkbox);
        System.out.println("Gender: " + gender);
        System.out.println("Employment: " + employment);
        System.out.println("Date of Birth: " + df.format(dateOfBirth));

        Assert.assertEquals(name, "Test Anas");
        Assert.assertEquals(email, "testanas@gmail.com");
        Assert.assertEquals(password, "123456");
        Assert.assertEquals(df.format(dateOfBirth), "01/01/2000");
        Assert.assertEquals(gender, "Male");
        Assert.assertTrue(driver.findElement(By.id("exampleCheck1")).isSelected());
        Assert.assertTrue(driver.findElement(By.xpath("//input[@id='inlineRadio1']")).isSelected());

        driver.findElement(By.xpath("//input[@type='submit']")).click();
        Thread.sleep(1000);

        // Check Alert
        WebElement alert = driver.findElement(By.xpath("//div[@class='alert alert-success alert-dismissible']"));
        Assert.assertTrue(alert.isDisplayed());
        js.executeScript("arguments[0].scrollIntoView();", alert);
        System.out.println("Alert is displayed: " + alert.getText());
        Thread.sleep(800);

        driver.close();
        driver.quit();

    }

}
