import io.qameta.allure.Epic;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.MainPage;
import pages.SignInPage;
import pages.UserCabinetPage;

import static org.junit.Assert.assertEquals;

@Epic("Переходы на разные экраны")
public class GoToScreensTest {

    private WebDriver driver;
    private String accessToken;
    User user;
    UserClient userClient = new UserClient();


    @Rule
    public DriverRule driverRule = new DriverRule();

    @Before
    public void createTestUser() {
        user = new UserGenerator().random();
        accessToken = userClient.userCreate(user);
        System.out.println("Тестовый пользователь создан");
        System.out.println("Email: " + user.getEmail());
        System.out.println("Password: " + user.getPassword());
        System.out.println("Token is: " + accessToken);
    }

    @After
    public void deleteTestUser() {
        if(accessToken != null) {
            userClient.userDelete(accessToken);
            System.out.println("Тестовый пользователь удален");
        }
    }

    @Test
    @DisplayName("Переход по клику на «Личный кабинет»")
    public void goToCabinet() {
        driver = driverRule.getDriver();
        driver.get(Env.BASE_URL);
        WebDriverWait wait = new WebDriverWait(driverRule.getDriver(), 3);

        MainPage mainPage = new MainPage(driverRule.getDriver());
        mainPage.goToLogin();

        SignInPage signInPage = new SignInPage(driverRule.getDriver());
        signInPage.login(user.getEmail(), user.getPassword());
        //ожидание сокрытия Loader'а для обхода проблемы на Firefox
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'Modal_modal__P3_V5')]")));

        //По редиректу на главную проверяем успешность авторизации
        mainPage.waitRedirectToMain();
        String primaryButtonText = mainPage.getPrimaryButtonText();
        assertEquals("Оформить заказ", primaryButtonText);

        mainPage.goToLk();
        //ожидание сокрытия Loader'а для обхода проблемы на Firefox
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'Modal_modal__P3_V5')]")));

        UserCabinetPage userCabinetPage = new UserCabinetPage(driverRule.getDriver());
        userCabinetPage.getName();
        assertEquals(user.getName(), userCabinetPage.getName());
        assertEquals(user.getEmail(), userCabinetPage.getLogin());
    }

    @Test
    @DisplayName("Переход по клику на «Конструктор»")
    public void goToMainByMenuItem() {
        driver = driverRule.getDriver();
        driver.get(Env.BASE_URL);
        WebDriverWait wait = new WebDriverWait(driverRule.getDriver(), 3);

        MainPage mainPage = new MainPage(driverRule.getDriver());
        mainPage.goToLogin();

        SignInPage signInPage = new SignInPage(driverRule.getDriver());
        signInPage.login(user.getEmail(), user.getPassword());

        //ожидание сокрытия Loader'а для обхода проблемы на Firefox
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'Modal_modal__P3_V5')]")));

        mainPage.goToLk();
        //ожидание сокрытия Loader'а для обхода проблемы на Firefox
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'Modal_modal__P3_V5')]")));

        UserCabinetPage userCabinetPage = new UserCabinetPage(driverRule.getDriver());
        userCabinetPage.getName();
        assertEquals(user.getName(), userCabinetPage.getName());
        assertEquals(user.getEmail(), userCabinetPage.getLogin());

        userCabinetPage.clickConstructor();
        //ожидание сокрытия Loader'а для обхода проблемы на Firefox
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'Modal_modal__P3_V5')]")));

        //Проверяем переход на главную
        mainPage.waitRedirectToMain();
        String primaryButtonText = mainPage.getPrimaryButtonText();
        assertEquals("Оформить заказ", primaryButtonText);
    }

    @Test
    @DisplayName("Переход по клику на логотип")
    public void goToMainByLogo() {
        driver = driverRule.getDriver();
        driver.get(Env.BASE_URL);
        WebDriverWait wait = new WebDriverWait(driverRule.getDriver(), 3);

        MainPage mainPage = new MainPage(driverRule.getDriver());
        mainPage.goToLogin();

        SignInPage signInPage = new SignInPage(driverRule.getDriver());
        signInPage.login(user.getEmail(), user.getPassword());

        //ожидание сокрытия Loader'а для обхода проблемы на Firefox
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'Modal_modal__P3_V5')]")));

        mainPage.goToLk();
        //ожидание сокрытия Loader'а для обхода проблемы на Firefox
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'Modal_modal__P3_V5')]")));

        UserCabinetPage userCabinetPage = new UserCabinetPage(driverRule.getDriver());
        userCabinetPage.getName();
        assertEquals(user.getName(), userCabinetPage.getName());
        assertEquals(user.getEmail(), userCabinetPage.getLogin());

        userCabinetPage.clickLogo();
        //ожидание сокрытия Loader'а для обхода проблемы на Firefox
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'Modal_modal__P3_V5')]")));

        //Проверяем переход на главную
        mainPage.waitRedirectToMain();
        String primaryButtonText = mainPage.getPrimaryButtonText();
        assertEquals("Оформить заказ", primaryButtonText);
    }

    @Test
    @DisplayName("Выход из Личного кабинета")
    public void logout() {
        driver = driverRule.getDriver();
        driver.get(Env.BASE_URL);
        WebDriverWait wait = new WebDriverWait(driverRule.getDriver(), 3);

        MainPage mainPage = new MainPage(driverRule.getDriver());
        mainPage.goToLogin();

        SignInPage signInPage = new SignInPage(driverRule.getDriver());
        signInPage.login(user.getEmail(), user.getPassword());
        //ожидание сокрытия Loader'а для обхода проблемы на Firefox
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'Modal_modal__P3_V5')]")));

        mainPage.goToLk();
        //ожидание сокрытия Loader'а для обхода проблемы на Firefox
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'Modal_modal__P3_V5')]")));

        UserCabinetPage userCabinetPage = new UserCabinetPage(driverRule.getDriver());
        userCabinetPage.getName();
        assertEquals(user.getName(), userCabinetPage.getName());
        assertEquals(user.getEmail(), userCabinetPage.getLogin());

        userCabinetPage.clickLogout();
        //ожидание сокрытия Loader'а для обхода проблемы на Firefox
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'Modal_modal__P3_V5')]")));

        //Проверяем переход на главную
        signInPage.waitRedirectToLogin();
        String headerText = signInPage.getHeaderText();
        assertEquals("Вход", headerText);
    }
}
