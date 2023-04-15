import org.example.Util;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;


import java.io.IOException;
import java.util.*;

public class SignInTest {
    private Map<String, Object> vars;
    private JavascriptExecutor js;
    private final long timeout = 2;

    @BeforeClass
    public static void init() {
        Util.prepareDrivers();
    }


    @Test
    public void signInTest() throws IOException, InterruptedException {
        List<WebDriver> drivers = Util.getDrivers();
        for (WebDriver driver : drivers) {
            js = (JavascriptExecutor) driver;
            vars = new HashMap<>();

            driver.get("https://simpleswap.io/");

            driver.manage().window().setSize(new Dimension(1920, 1080));

            List<WebElement> elements = driver.findElements(By.xpath("//*[@id=\"__next\"]/div/header/div/div/div[3]"));
            assert (elements.size() > 0);

            driver.findElement(By.xpath("//div[@id=\'__next\']/div/header/div/div/div[2]/div[2]/a/div")).click();
            driver.findElement(By.xpath("//div[@id=\'root\']/div/div/div/div[2]/div/form/div/div[5]/div/div/div[2]/input")).sendKeys("ivandolgihcapt@gmail.com");
            driver.findElement(By.xpath("//div[@id=\'root\']/div/div/div/div[2]/div/form/div/div[7]/div/div/div[2]/input")).sendKeys("Itmotpotest1");
            driver.findElement(By.xpath("//div[@id=\'root\']/div/div/div/div[2]/div/form/div/button")).click();
            Util.waitTime(timeout);

            vars.put("pageurl", js.executeScript("return window.location.href;"));
            assertEquals(vars.get("pageurl").toString(), "https://simpleswap.io/customer-account/overview");
            driver.quit();
        }
    }
}
