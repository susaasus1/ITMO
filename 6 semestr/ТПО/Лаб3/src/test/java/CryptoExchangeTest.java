import org.example.Util;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Dimension;


import java.io.IOException;
import java.util.*;

public class CryptoExchangeTest {

    private final long timeout = 2;

    @BeforeClass
    public static void init() {
        Util.prepareDrivers();
    }

    @Test
    public void cryptoExchangeTest() throws IOException, InterruptedException {
        List<WebDriver> drivers = Util.getDrivers();
        for (WebDriver driver : drivers) {
            driver.get("https://simpleswap.io/");
            driver.manage().window().setSize(new Dimension(1920, 1080));
            Util.waitTime(timeout);
            driver.findElement(By.xpath("//div[@id=\'__next\']/div/div/div/div/div/div/div[2]/button")).click();
            Util.waitTime(timeout);
            driver.findElement(By.xpath("//div[@id=\'__next\']/div/div/div/div/div[2]/div[2]/div[2]/input")).click();
            Util.waitTime(timeout);
            driver.findElement(By.xpath("//div[@id=\'__next\']/div/div/div/div/div[2]/div[2]/div[2]/input")).sendKeys("0x71C7656EC7ab88b098defB751B7401B5f6d8976F");
            driver.findElement(By.xpath("//div[@id=\'__next\']/div/div/div/div/div[2]/div[2]/button")).click();
            Util.waitTime(timeout + 5);
            assertEquals(driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div/div/div[1]/div[3]/div[2]/div[2]/div[2]/div[1]")).getText(), "0x71C7656EC7ab88b098defB751B7401B5f6d8976F");
            driver.quit();
        }

    }
}
