package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ResetPasswordPage {

    private WebDriver driver;

    private By emailInput = By.xpath("//label[contains(text(), 'Email')]/following-sibling::input[@name = 'name']");
    private By resetPasswordButton = By.xpath("//button[contains(@class, 'primary') and contains(text(), 'Восстановить')]");
    private By signInButton = By.xpath("//a[contains(text(), 'Войти')]");

    public ResetPasswordPage(WebDriver driver){
        this.driver = driver;
    }

    @Step("Нажать кнопку Войти")
    public void clickLogin() {
        driver.findElement(signInButton).click();
    }

}
