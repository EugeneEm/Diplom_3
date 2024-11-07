import io.qameta.allure.Epic;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pages.MainPage;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
@Epic("Переходы по категориям конструктора")
public class ConstructorParamTest {
    private String categoryFrom;
    private String categoryTo;
    private WebDriver driver;

    @Rule
    public DriverRule driverRule = new DriverRule();

    public ConstructorParamTest(String categoryFrom, String categoryTo) {
        this.categoryFrom = categoryFrom;
        this.categoryTo = categoryTo;
    }

    @Parameterized.Parameters(name = "Тест перехода на категорию = {1}")
    public static Collection<Object[]> getOrderTestData() {
        return Arrays.asList(new Object[][]{
                {"Соусы", "Булки"},
                {"Начинки", "Соусы"},
                {"Соусы", "Начинки"}
        });
    }

    @Test
    @DisplayName("Переход к категории конструктора")
    public void tabsSwitch() {
        driver = driverRule.getDriver();
        driver.get(Env.BASE_URL);
        MainPage mainPage = new MainPage(driverRule.getDriver());
        mainPage.setTabLocator(categoryFrom);
        mainPage.categoryClick();
        mainPage.setTabLocator(categoryTo);
        mainPage.categoryClick();
        mainPage.checkTab();
    }
}
