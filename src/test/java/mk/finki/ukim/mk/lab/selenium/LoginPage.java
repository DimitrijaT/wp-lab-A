package mk.finki.ukim.mk.lab.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractPage {

    private WebElement username;

    private WebElement password;

    private WebElement submit;

    public LoginPage(WebDriver driver) {
        super(driver);
    }


    public static LoginPage openLogin(WebDriver driver) {
        // Велиме на провајдерот да прави повик на /login
        get(driver, "/login");
        System.out.println(driver.getCurrentUrl());
        return PageFactory.initElements(driver, LoginPage.class);
    }

    public static BalloonsPage doLogin(WebDriver driver, LoginPage loginPage, String username, String password) {
        // Да ги иницијализираме елементите што ги имаме како својства и автоматски бара според името на променливата дали има name или id,посотојат и посебни анотации што може да специфицираме што сакаме

        loginPage.username.sendKeys(username);
        loginPage.password.sendKeys(password);
        loginPage.submit.click();
        System.out.println(driver.getCurrentUrl());
        return PageFactory.initElements(driver, BalloonsPage.class);
    }


    public static LoginPage logout(WebDriver driver) {
        get(driver, "/logout");
        return PageFactory.initElements(driver, LoginPage.class);
    }


}