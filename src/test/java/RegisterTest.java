import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.MainPage;
import pages.SignInPage;
import pages.SignUpPage;
import pages.UserCabinetPage;

import static org.junit.Assert.assertEquals;

@Epic("Регистрация")
public class RegisterTest {

    private WebDriver driver;
    private String accessToken;
    User user;

    public RegisterTest(){}

    @Rule
    public DriverRule driverRule = new DriverRule();

    @After
    public void deleteTestUser() {
        if(accessToken != null) {
            UserClient userClient = new UserClient();
            userClient.userDelete(accessToken);
            System.out.println("Тестовый пользователь удален");
        }
    }

    @Test
    @DisplayName("Регистрация пользователя")
    @Description("Регистрация нового пользователя, вход и удаление")
    public void registerNewUser() {
        driver = driverRule.getDriver();
        driver.get(Env.BASE_URL);
        WebDriverWait wait = new WebDriverWait(driverRule.getDriver(), 3);


        MainPage mainPage = new MainPage(driverRule.getDriver());
        mainPage.goToLk();

        SignInPage signInPage = new SignInPage(driverRule.getDriver());
        signInPage.goToregister();

        SignUpPage signUpPage = new SignUpPage(driverRule.getDriver());
        user = new UserGenerator().random();
        signUpPage.fillUpName(user.getName());
        signUpPage.fillUpEmail(user.getEmail());
        signUpPage.fillUpPassword(user.getPassword());
        signUpPage.clickRegisterButton();
        signInPage.waitRedirectToLogin();
        signInPage.login(user.getEmail(), user.getPassword());
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'Modal_modal__P3_V5')]")));

        LocalStorage localStorage = ((WebStorage) driver).getLocalStorage();
        accessToken = localStorage.getItem("accessToken");
        System.out.println("Token is : " + accessToken);

        mainPage.waitRedirectToMain();
        String primaryButtonText = mainPage.getPrimaryButtonText();
        //Проверяем что если пользователь авторизован, то на главной кнопка "Войти в аккаунт" меняется на "Оформить заказ"
        assertEquals("Оформить заказ", primaryButtonText);

        mainPage.goToLk();
        UserCabinetPage userCabinetPage = new UserCabinetPage(driverRule.getDriver());
        //Проверяем креды зарегистрированного пользователя
        assertEquals(user.getName(), userCabinetPage.getName());
        assertEquals(user.getEmail(), userCabinetPage.getLogin());

    }

    @Test
    @DisplayName("Регистрация с некорректным паролем")
    @Description("Проверка валидации пароля при регистрации")
    public void checkRegisterPassword() {
        driver = driverRule.getDriver();
        driver.get(Env.BASE_URL);

        MainPage mainPage = new MainPage(driverRule.getDriver());
        mainPage.goToLk();

        SignInPage signInPage = new SignInPage(driverRule.getDriver());
        signInPage.goToregister();

        SignUpPage signUpPage = new SignUpPage(driverRule.getDriver());
        User user = new UserGenerator().random();
        user.setPassword("12345");
        signUpPage.fillUpName(user.getName());
        signUpPage.fillUpEmail(user.getEmail());
        signUpPage.fillUpPassword(user.getPassword());
        signUpPage.clickRegisterButton();
        String errorMessage = signUpPage.checkPasswordError();
        //Проверяем что появился текст ошибки при некорректном пароле
        assertEquals("Некорректный пароль", errorMessage);
    }
}
