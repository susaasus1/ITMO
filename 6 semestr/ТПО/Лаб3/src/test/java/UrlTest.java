import org.example.Util;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.*;

public class UrlTest {
    private Map<String, Object> vars;
    private JavascriptExecutor js;
    private final long timeout = 2;

    @BeforeClass
    public static void init() {
        Util.prepareDrivers();
    }


    @Test
    public void urlTest() throws IOException, InterruptedException {
        List<WebDriver> drivers = Util.getDrivers();
        for (WebDriver driver : drivers) {
            js = (JavascriptExecutor) driver;
            vars = new HashMap<>();

            driver.get("https://simpleswap.io/");

            driver.manage().window().setSize(new Dimension(1920, 1080));
            Util.login(driver);
            Util.waitTime(timeout);

            driver.findElement(By.xpath("//div[@id=\'__next\']/div/header/div/div/div[2]/div[2]/div[2]/div")).click();
            driver.findElement(By.xpath("//div[@id=\'__next\']/div/header/div/div/div[2]/div[2]/div[2]/div[4]/div[5]/a")).click();
            vars.put("pageurl", js.executeScript("return window.location.href;"));
            assertEquals(vars.get("pageurl").toString(), "https://simpleswap.io/customer-account/overview");
            Util.waitTime(timeout);
            driver.findElement(By.xpath("//div[@id=\'root\']/div/div/div/header/div/div[2]/div[4]/button/div")).click();
            Util.waitTime(timeout);

            driver.findElement(By.xpath("//div[@id=\'root\']/div/div/div/header/div/div[2]/div[4]/div/div/div[5]/a[2]/div/div")).click();
            vars.put("pageurl", js.executeScript("return window.location.href;"));
            assertEquals(vars.get("pageurl").toString(), "https://simpleswap.io/customer-account/history");
            driver.findElement(By.xpath("//div[@id=\'root\']/div/div/div/header/div/div[2]/div[4]/button/div")).click();
            Util.waitTime(timeout);

            driver.findElement(By.xpath("//div[@id=\'root\']/div/div/div/header/div/div[2]/div[4]/div/div/div[5]/a[3]/div/div")).click();
            vars.put("pageurl", js.executeScript("return window.location.href;"));
            assertEquals(vars.get("pageurl").toString(), "https://simpleswap.io/customer-account/loyalty");
            driver.findElement(By.xpath("//div[@id=\'root\']/div/div/div/header/div/div[2]/div[4]/button/div")).click();
            Util.waitTime(timeout);

            driver.findElement(By.xpath("//div[@id=\'root\']/div/div/div/header/div/div[2]/div[4]/div/div/div[5]/a[4]/div/div")).click();
            vars.put("pageurl", js.executeScript("return window.location.href;"));
            assertEquals(vars.get("pageurl").toString(), "https://simpleswap.io/customer-account/payouts");
            driver.quit();
        }
    }
}
