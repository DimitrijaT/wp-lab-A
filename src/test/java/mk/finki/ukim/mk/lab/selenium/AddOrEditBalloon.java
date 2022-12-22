package mk.finki.ukim.mk.lab.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class AddOrEditBalloon extends AbstractPage {

    private WebElement balloonName;
    private WebElement balloonDescription;
    private WebElement manufacturer;
    private WebElement submit;

    public AddOrEditBalloon(WebDriver driver) {
        super(driver);
    }

    public static BalloonsPage addBalloon(WebDriver driver, String name, String description, String manufacturer) {
        get(driver, "/balloons/add-form");
        AddOrEditBalloon addOrEditProduct = PageFactory.initElements(driver, AddOrEditBalloon.class);
        addOrEditProduct.balloonName.sendKeys(name);
        addOrEditProduct.balloonDescription.sendKeys(name);
        addOrEditProduct.manufacturer.click();
        addOrEditProduct.manufacturer.findElement(By.xpath("//option[. = '" + manufacturer + "']")).click();

        addOrEditProduct.submit.click();
        return PageFactory.initElements(driver, BalloonsPage.class);
    }

    public static BalloonsPage editBalloon(WebDriver driver, WebElement editButton, String name, String description, String manufacturer) {
        editButton.click();
        System.out.println(driver.getCurrentUrl());
        AddOrEditBalloon addOrEditProduct = PageFactory.initElements(driver, AddOrEditBalloon.class);
        addOrEditProduct.balloonName.sendKeys(name);
        addOrEditProduct.balloonDescription.sendKeys(description);
        addOrEditProduct.manufacturer.click();
        addOrEditProduct.manufacturer.findElement(By.xpath("//option[. = '" + manufacturer + "']")).click();

        addOrEditProduct.submit.click();
        return PageFactory.initElements(driver, BalloonsPage.class);
    }


}