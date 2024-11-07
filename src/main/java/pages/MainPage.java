package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    private WebDriver driver;

    private By lkHeaderButton = By.xpath("//p[contains(@class, 'header') and contains(text(), 'Личный Кабинет')]");
    private By loginButton = By.xpath("//button[contains(text(), 'Войти в аккаунт')]");
    private By orderButton = By.xpath("//button[contains(text(), 'Оформить')]");
    private By primaryButton = By.xpath("//button[contains(@class, 'primary')]");
    String tabs;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        System.out.println("WebDriver инициализирован: " + (driver != null));
    }

    @Step("Нажать кнопку Личный кабинет")
    public void goToLk() {
        driver.findElement(lkHeaderButton).click();
    }

    @Step("Нажать кнопку Войти в аккаунт")
    public void goToLogin() {
        driver.findElement(loginButton).click();
    }

    @Step("Ожидание редиректа на главную после входа")
    public void waitRedirectToMain() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(orderButton));
    }

    @Step("Получить текст главной кнопки")
    public String getPrimaryButtonText() {
        return driver.findElement(primaryButton).getText();
    }

    @Step("Установить локатор таба")
    public String setTabLocator(String category) {
        System.out.println("Category: " + category);
        tabs = "//div[.//span[contains(text(), '" + category + "')] and contains(@class, 'tab')]";
        System.out.println("Tab's locator: " + tabs);
        return tabs;
    }

    @Step("Нажать выбранную категорию")
    public void categoryClick() {
        driver.findElement(By.xpath(tabs)).click();
    }

    @Step("Проверить переход к выбранной категории")
    public void checkTab() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.attributeContains(driver.findElement(By.xpath(tabs)), "class", "current"));
    }
}
