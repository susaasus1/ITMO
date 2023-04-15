import org.example.Util;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;

import java.io.IOException;
import java.util.*;

public class BaseUrlTest {
    private Map<String, Object> vars;
    private JavascriptExecutor js;
    private final long timeout = 2;

    @BeforeClass
    public static void init() {
        Util.prepareDrivers();
    }

    @Test
    public void baseURLTest() throws IOException, InterruptedException {
        List<WebDriver> drivers = Util.getDrivers();
        for (WebDriver driver : drivers) {
            js = (JavascriptExecutor) driver;
            vars = new HashMap<>();
            driver.get("https://simpleswap.io/");
            driver.manage().window().setSize(new Dimension(1920, 1080));
            driver.findElement(By.xpath("//div[@id=\'__next\']/div/header/div/div/div[2]/div/a")).click();
            Util.waitTime(timeout);
            vars.put("pageurl", js.executeScript("return window.location.href"));
            assertEquals(vars.get("pageurl").toString(), "https://simpleswap.io/how-it-works");
            driver.findElement(By.xpath("//div[@id=\'__next\']/div/header/div/div/div[2]/div/a[2]")).click();
            Util.waitTime(timeout);
            vars.put("pageurl", js.executeScript("return window.location.href"));
            assertEquals(vars.get("pageurl").toString(), "https://simpleswap.io/loyalty");
            driver.findElement(By.xpath("//div[@id=\'__next\']/div/header/div/div/div[2]/div/a[3]")).click();
            Util.waitTime(timeout);
            vars.put("pageurl", js.executeScript("return window.location.href"));
            assertEquals(vars.get("pageurl").toString(), "https://simpleswap.io/faq");
            driver.findElement(By.xpath("//div[@id=\'__next\']/div/header/div/div/div[2]/div/a[4]")).click();
            Util.waitTime(timeout);
            vars.put("pageurl", js.executeScript("return window.location.href"));
            assertEquals(vars.get("pageurl").toString(), "https://simpleswap.io/contacts");
            driver.quit();
        }
    }
}
