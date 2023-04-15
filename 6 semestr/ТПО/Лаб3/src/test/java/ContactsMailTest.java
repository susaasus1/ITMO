import org.example.Util;
import org.junit.BeforeClass;
import org.junit.Test;


import static org.junit.Assert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Dimension;

import java.io.IOException;
import java.util.*;

public class ContactsMailTest {
    private final long timeout = 2;

    @BeforeClass
    public static void init() {
        Util.prepareDrivers();
    }

    @Test
    public void contactsMailTest() throws IOException, InterruptedException {
        List<WebDriver> drivers = Util.getDrivers();
        for (WebDriver driver : drivers) {
            driver.get("https://simpleswap.io/");
            driver.manage().window().setSize(new Dimension(1920, 1080));
            driver.findElement(By.xpath("//div[@id=\'__next\']/div/header/div/div/div[2]/div/a[4]")).click();
            Util.waitTime(timeout);
            driver.findElement(By.xpath("//input[@id=\'fromName\']")).click();
            Util.waitTime(timeout);
            driver.findElement(By.xpath("//input[@id=\'fromName\']")).sendKeys("itmotpo3");
            {
                String value = driver.findElement(By.xpath("//input[@id=\'fromName\']")).getAttribute("value");
                assertEquals(value, "itmotpo3");
            }
            driver.findElement(By.xpath("//input[@id=\'fromEmail\']")).click();
            Util.waitTime(timeout);
            driver.findElement(By.xpath("//input[@id=\'fromEmail\']")).sendKeys("ivandolgihcapt@gmail.com");
            {
                String value = driver.findElement(By.xpath("//input[@id=\'fromEmail\']")).getAttribute("value");
                assertEquals(value, "ivandolgihcapt@gmail.com");
            }
            driver.findElement(By.xpath("//div[@id=\'__next\']/div/div/div/div/form/div[2]/div/div/div/div")).click();
            Util.waitTime(timeout);
            driver.findElement(By.xpath("//div[@id=\'react-select-2-listbox\']/div/div")).click();
            Util.waitTime(timeout);
            driver.findElement(By.xpath("//textarea[@id=\'text\']")).click();
            Util.waitTime(timeout);
            driver.findElement(By.xpath("//textarea[@id=\'text\']")).sendKeys("HI!");
            {
                String value = driver.findElement(By.xpath("//textarea[@id=\'text\']")).getAttribute("value");
                assertEquals(value, "HI!");
            }
            driver.findElement(By.xpath("//div[@id=\'__next\']/div/div/div/div/form/div[4]/button")).click();
            Util.waitTime(timeout);
            driver.quit();
        }
    }
}