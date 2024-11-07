package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignInPage {
    private WebDriver driver;

    private By loginInput = By.name("name");
    private By passwordInput = By.name("Пароль");
    private By signUpButton = By.xpath("//a[contains(@class, 'Auth') and contains(text(), 'Зарегистрироваться')]");
    private By loginButton = By.xpath("//button[contains(text(), 'Войти')]");
    private By loginHeaderWithText = By.xpath("//h2[text() = 'Вход']");
    private By loginHeader = By.tagName("h2");
    private By resetPasswordButton = By.xpath("//a[contains(text(), 'Восстановить')]");

    public SignInPage(WebDriver driver){
        this.driver = driver;
    }

    @Step("Нажать кнопку Зарегистрироваться")
    public void goToregister() {
        driver.findElement(signUpButton).click();
    }

    @Step("Нажать кнопку Восстановить пароль")
    public void goResetPassword() {
        driver.findElement(resetPasswordButton).click();
    }

    @Step("Заполнить поле Email")
    public void fillUpEmail(String login) {
        driver.findElement(loginInput).sendKeys(login);
    }

    @Step("Заполнить поле Пароль")
    public void fillUpPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    @Step("Войти в систему")
    public void login(String login, String password) {
        fillUpEmail(login);
        fillUpPassword(password);
        driver.findElement(loginButton).click();
    }

    @Step("Получить текст подзаголовка")
    public String getHeaderText() {
        return driver.findElement(loginHeader).getText();
    }

    @Step("Ожидание редиректа на страницу авторизации")
    public void waitRedirectToLogin() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(loginButton));

    }
}
