package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserCabinetPage {

    private WebDriver driver;

    private By nameInput = By.xpath("//label[contains(text(), 'Имя')]/following-sibling::input");
    private By loginlInput = By.xpath("//label[contains(text(), 'Логин')]/following-sibling::input");
    private By passwordInput = By.xpath("//label[contains(text(), 'Пароль')]/following-sibling::input");
    private By primaryButton = By.xpath("//button[contains(@class, 'primary')]");
    private By constructorButton = By.xpath("//p[contains(text(), 'Конструктор')]");
    private By logo = By.xpath("//div[contains(@class, 'logo')]");
    private By logoutButton = By.xpath("//button[contains(text(), 'Выход')]");


    public UserCabinetPage(WebDriver driver){
        this.driver = driver;
    }

    @Step("Получить значение поля Имя")
    public String getName() {
        return driver.findElement(nameInput).getAttribute("value");
    }

    @Step("Получить значение поля Логин")
    public String getLogin() {
        return driver.findElement(loginlInput).getAttribute("value");
    }

    @Step("Нажать на кнопку Конструктор")
    public void clickConstructor() {
        driver.findElement(constructorButton).click();
    }

    @Step("Нажать на Логотип")
    public void clickLogo() {
        driver.findElement(logo).click();
    }

    @Step("Нажать на Выход")
    public void clickLogout() {
        driver.findElement(logoutButton).click();
    }

}
