package steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import lombok.Data;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.stqa.selenium.wrapper.HighlightingWrapper;
import util.TestProperties;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

@Data
public class BaseCucu {
    private static WebDriver driver;
    public static Properties properties = TestProperties.gitInstance().getProperties();
    public static boolean first;
    public static boolean second;

    public static WebDriver getDriver() {
        return driver;
    }

    public static boolean isFirst() {
        return first;
    }

    public static boolean isSecond() {
        return second;
    }

    @Before(value = "@First")
    public void test() {
        switch (properties.getProperty("browser")) {
            case "chrome":
                System.setProperty("web.driver.chrome.driver", properties.getProperty("web.driver.chrome.driver"));
                driver = new HighlightingWrapper(new ChromeDriver());
                break;
        }
        first = true;
        second = false;
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        System.out.println("Выполняется бефорТест для ....");
    }

    @Before(value = "@Second")
    public void testUp() {
        switch (properties.getProperty("browser")) {
            case "chrome":
                System.setProperty("web.driver.chrome.driver", properties.getProperty("web.driver.chrome.driver"));
                driver = new HighlightingWrapper(new ChromeDriver());
                break;
        }
        second = true;
        first = false;
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
//        driver.close();
//        driver.quit();
    }
}
