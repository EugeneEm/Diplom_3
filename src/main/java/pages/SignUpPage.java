package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignUpPage {

    private WebDriver driver;

    private By nameInput = By.xpath("//label[contains(text(), 'Имя')]/following-sibling::input[@name = 'name']");
    private By emailInput = By.xpath("//label[contains(text(), 'Email')]/following-sibling::input[@name = 'name']");
    private By passwordInput = By.xpath("//label[contains(text(), 'Пароль')]/following-sibling::input[@name = 'Пароль']");
    private By registerButton = By.xpath("//button[contains(text(), 'Зарегистрироваться')]");
    private By signInButton = By.xpath("//a[contains(text(), 'Войти')]");
    private By passwordError = By.xpath("//p[contains(@class, 'error')]");

    public SignUpPage(WebDriver driver){
        this.driver = driver;
    }

    @Step("Заполнить поле Имя")
    public void fillUpName(String name) {
        driver.findElement(nameInput).sendKeys(name);
    }

    @Step("Заполнить поле Email")
    public void fillUpEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }

    @Step("Заполнить поле Пароль")
    public void fillUpPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    @Step("Нажать кнопку Зарегистрироваться")
    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    @Step("Получить текст ошибки пароля")
    public String checkPasswordError() {
        return driver.findElement(passwordError).getText();
    }

    @Step("Нажать кнопку Войти")
    public void clickLogin() {
        driver.findElement(signInButton).click();
    }
}
