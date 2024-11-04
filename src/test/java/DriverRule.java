import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.concurrent.TimeUnit;

public class DriverRule extends ExternalResource {

    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    @Override
    protected void before() throws Throwable {
        initDriver();
    }

    public void initDriver() {
        //Определение по параметру запуска "browser"
        if("firefox".equals(System.getProperty("browser"))) {
            startFirefox();
        } else if("yandex".equals(System.getProperty("browser"))) {
            startYandex();
        } else {
            startChrome();
        }
        //Неявное ожидание в 5 сек
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public void startChrome() {
        WebDriverManager.chromedriver().clearDriverCache();
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    public void startFirefox() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
    }

    public void startYandex() {
        WebDriverManager.chromedriver().driverVersion(System.getProperty("driver.version")).setup();

        var options = new ChromeOptions();
        options.setBinary(System.getProperty("webdriver.yandex.path"));
        driver = new ChromeDriver(options);

    }

    @Override
    protected void after() {
        driver.quit();
    }
}
