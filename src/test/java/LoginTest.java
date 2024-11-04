import io.qameta.allure.Epic;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.MainPage;
import pages.ResetPasswordPage;
import pages.SignInPage;
import pages.SignUpPage;

import static org.junit.Assert.assertEquals;

@Epic("Вход пользователя")
public class LoginTest {
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
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    public void loginAtMain()  {
        driver = driverRule.getDriver();
        driver.get(Env.BASE_URL);
        MainPage mainPage = new MainPage(driverRule.getDriver());
        mainPage.goToLogin();

        SignInPage signInPage = new SignInPage(driverRule.getDriver());
        signInPage.login(user.getEmail(), user.getPassword());
        //По редиректу на главную проверяем успешность авторизации
        mainPage.waitRedirectToMain();
        String primaryButtonText = mainPage.getPrimaryButtonText();
        assertEquals("Оформить заказ", primaryButtonText);
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void loginAtCabinet() {
        driver = driverRule.getDriver();
        driver.get(Env.BASE_URL);
        MainPage mainPage = new MainPage(driverRule.getDriver());
        mainPage.goToLk();

        SignInPage signInPage = new SignInPage(driverRule.getDriver());
        signInPage.login(user.getEmail(), user.getPassword());
        //По редиректу на главную проверяем успешность авторизации
        mainPage.waitRedirectToMain();
        String primaryButtonText = mainPage.getPrimaryButtonText();
        assertEquals("Оформить заказ", primaryButtonText);
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void loginAtRegister() throws InterruptedException {
        driver = driverRule.getDriver();
        driver.get(Env.BASE_URL);
        MainPage mainPage = new MainPage(driverRule.getDriver());
        mainPage.goToLk();
        Thread.sleep(5000);
        SignInPage signInPage = new SignInPage(driverRule.getDriver());
        signInPage.goToregister();
        Thread.sleep(5000);

        SignUpPage signUpPage = new SignUpPage(driverRule.getDriver());
        signUpPage.clickLogin();
        Thread.sleep(5000);

        signInPage.login(user.getEmail(), user.getPassword());
        //По редиректу на главную проверяем успешность авторизации
        mainPage.waitRedirectToMain();
        String primaryButtonText = mainPage.getPrimaryButtonText();
        assertEquals("Оформить заказ", primaryButtonText);
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void loginAtReset() throws InterruptedException {
        driver = driverRule.getDriver();
        driver.get(Env.BASE_URL);
        MainPage mainPage = new MainPage(driverRule.getDriver());
        mainPage.goToLogin();

        SignInPage signInPage = new SignInPage(driverRule.getDriver());
        signInPage.goResetPassword();
        Thread.sleep(5000);

        ResetPasswordPage resetPasswordPage = new ResetPasswordPage(driverRule.getDriver());
        resetPasswordPage.clickLogin();
        Thread.sleep(5000);

        signInPage.login(user.getEmail(), user.getPassword());
        mainPage.waitRedirectToMain();
        String primaryButtonText = mainPage.getPrimaryButtonText();
        assertEquals("Оформить заказ", primaryButtonText);
    }

}
