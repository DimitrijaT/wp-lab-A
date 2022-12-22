package mk.finki.ukim.mk.lab.selenium;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Getter
public class AbstractPage {

    protected WebDriver driver;

    @FindBy(css = ".go-to-balloons")
    private WebElement goToBalloons;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

    // Помошен метод со кој што го wrap-уваме земањето на абсолутната патека на нашиот сервер која што е localhost 9999
    static void get(WebDriver driver, String relativeUrl) {
        String url = System.getProperty("geb.build.baseUrl", "http://localhost:9999") + relativeUrl;
        driver.get(url);
    }

}