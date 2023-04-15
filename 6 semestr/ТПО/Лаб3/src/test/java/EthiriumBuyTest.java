import org.example.Util;
import org.junit.BeforeClass;
import org.junit.Test;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.*;

public class EthiriumBuyTest {
    private final long timeout = 2;

    @BeforeClass
    public static void init() {
        Util.prepareDrivers();
    }

    @Test
    public void ethiriumBuyTest() throws IOException, InterruptedException {
        List<WebDriver> drivers = Util.getDrivers();
        for (WebDriver driver : drivers) {
            driver.get("https://simpleswap.io/");
            driver.manage().window().setSize(new Dimension(1920, 1080));
            Util.waitTime(timeout);
            driver.findElement(By.xpath("//div[@id=\'__next\']/div/div/div/div/div/div/div/div[2]")).click();
            driver.findElement(By.xpath("//div[@id=\'__next\']/div/div/div/div/div/div/div[2]/button")).click();
            Util.waitTime(timeout);
            driver.findElement(By.xpath("//div[@id=\'__next\']/div/div/div/div/div/div[3]/div[2]")).click();
            driver.findElement(By.xpath("//div[@id=\'__next\']/div/div/div/div/div/div[3]/div[3]/div[2]/div/div/div/div[3]/div")).click();
            driver.findElement(By.xpath("//div[@id=\'__next\']/div/div/div/div/div[2]/div[2]/div[2]/div")).click();
            driver.findElement(By.xpath("//div[@id=\'__next\']/div/div/div/div/div[2]/div[2]/div[2]/input")).sendKeys("0x71C7656EC7ab88b098defB751B7401B5f6d8976F");
            driver.findElement(By.xpath("//div[@id=\'__next\']/div/div/div/div/div[2]/div[2]/button")).click();
            Util.waitTime(timeout);
            driver.findElement(By.xpath("//div[3]/div/div/div/div[2]/div[1]")).click();
            driver.findElement(By.xpath("//div[3]/div/div/div/div[3]/div[1]")).click();
            driver.findElement(By.xpath("//div[3]/div/div/div/div[4]")).click();
            Util.waitTime(timeout);
            List<WebElement> elements = driver.findElements(By.xpath("//*[@id=\"__next\"]/div[2]/div/div/div[1]/div[1]"));
            assert (elements.size() > 0);
            driver.quit();
        }
    }
}
