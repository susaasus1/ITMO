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


public class GooglePlayRedirectTest {
    private Map<String, Object> vars;
    private JavascriptExecutor js;

    @BeforeClass
    public static void init() {
        Util.prepareDrivers();
    }

    @Test
    public void googlePlayRedirectTest() throws IOException, InterruptedException {
        List<WebDriver> drivers = Util.getDrivers();
        for (WebDriver driver : drivers) {
            js = (JavascriptExecutor) driver;
            vars = new HashMap<>();

            driver.get("https://simpleswap.io/");

            driver.manage().window().setSize(new Dimension(1920, 1080));
            driver.findElement(By.xpath("//div[@id=\'__next\']/div/footer/div/div/div[2]/div/div/a[2]/img")).click();
            vars.put("pageurl", js.executeScript("return window.location.href;"));
            Util.waitTime(2);
            assertEquals(vars.get("pageurl").toString(), "https://play.google.com/store/apps/details?id=com.simpleswapapp&shortlink=4d83a727&c=Footer_Site_Android&pid=site&source_caller=ui");
            driver.quit();
        }
    }
}
