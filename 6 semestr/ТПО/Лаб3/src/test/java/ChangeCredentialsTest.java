
import org.example.Util;
import org.junit.BeforeClass;
import org.junit.Test;


import static org.junit.Assert.*;

import org.openqa.selenium.*;

import java.io.IOException;
import java.util.*;


public class ChangeCredentialsTest {

    private final long timeout = 2;

    @BeforeClass
    public static void init() {
        Util.prepareDrivers();
    }

    @Test
    public void changeCredentialsTest() throws IOException, InterruptedException {
        List<WebDriver> drivers = Util.getDrivers();
        for (WebDriver driver : drivers) {
            driver.get("https://simpleswap.io/");
            driver.manage().window().setSize(new Dimension(1920, 1080));
            Util.login(driver);
            Util.waitTime(timeout);
            driver.findElement(By.xpath("//div[@id=\'__next\']/div/header/div/div/div[2]/div[2]/div[2]/div")).click();
            Util.waitTime(timeout);
            driver.findElement(By.xpath("//div[@id=\'__next\']/div/header/div/div/div[2]/div[2]/div[2]/div[4]/a/div[2]")).click();
            Util.waitTime(timeout);
            driver.findElement(By.xpath("//div[@id=\'root\']/div/div/div/div/div[2]/div/div[3]/div[3]/div/div/div/div[3]/button/span")).click();
            Util.waitTime(timeout);
            WebElement nameInputField = driver.findElement(By.xpath("//input[@name=\'name\']"));
            for (int i = 0; i < driver.findElement(By.xpath("//div[@id=\'root\']/div/div/div/div/div[2]/div/div/div[2]/div/div[3]/div/div")).getText().length(); i++) {
                nameInputField.sendKeys(Keys.BACK_SPACE);
            }
            Util.waitTime(timeout);
            nameInputField.sendKeys("lab3neitmo");
            driver.findElement(By.xpath("//button[@type=\'submit\']")).click();
            Util.waitTime(timeout);
            driver.findElement(By.cssSelector(".sc-cTQhss path")).click();
            Util.waitTime(timeout);
            assertEquals(driver.findElement(By.xpath("//div[@id=\'root\']/div/div/div/div/div[2]/div/div/div[2]/div/div[3]/div/div")).getText(),"lab3neitmo");
            driver.findElement(By.xpath("//div[@id=\'root\']/div/div/div/div/div[2]/div/div[3]/div[3]/div/div/div/div[3]/button/span")).click();
            Util.waitTime(timeout);
            WebElement nameInputField2 = driver.findElement(By.xpath("//input[@name=\'name\']"));
            for (int i = 0; i < driver.findElement(By.xpath("//div[@id=\'root\']/div/div/div/div/div[2]/div/div/div[2]/div/div[3]/div/div")).getText().length(); i++) {
                nameInputField2.sendKeys(Keys.BACK_SPACE);
            }
            Util.waitTime(timeout);
            nameInputField2.sendKeys("lab3itmo");
            driver.findElement(By.xpath("//button[@type=\'submit\']")).click();
            Util.waitTime(timeout);
            driver.findElement(By.cssSelector(".sc-cTQhss path")).click();
            Util.waitTime(timeout);
            assertEquals(driver.findElement(By.xpath("//div[@id=\'root\']/div/div/div/div/div[2]/div/div/div[2]/div/div[3]/div/div")).getText(), "lab3itmo");
            driver.quit();
        }

    }
}
