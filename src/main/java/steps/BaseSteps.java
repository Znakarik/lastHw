package steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import util.TestProperties;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseSteps {
    private static WebDriver driver;
    protected static String baseUrl;
    public static Properties properties = TestProperties.gitInstance().getProperties();

    public static WebDriver getDriver() {
        return driver;
    }

    @Before
    public void testUp() {
        switch (properties.getProperty("browser")) {
            case "chrome":
                System.setProperty("web.driver.chrome.driver", properties.getProperty("web.driver.chrome.driver"));
                driver = new ChromeDriver();
                break;
        }
        baseUrl = properties.getProperty("app.url");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(baseUrl);
    }

    @After
    public void tearDown() {
        driver.close();
        driver.quit();
    }
}
