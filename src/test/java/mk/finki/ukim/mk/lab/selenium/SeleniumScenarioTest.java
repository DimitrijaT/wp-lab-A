package mk.finki.ukim.mk.lab.selenium;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.service.AuthService;
import mk.finki.ukim.mk.lab.service.BalloonService;
import mk.finki.ukim.mk.lab.service.ManufacturerService;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SeleniumScenarioTest {


    MockMvc mockMvc;
    @Autowired
    AuthService authService;
    @Autowired
    BalloonService balloonService;
    @Autowired
    ManufacturerService manufacturerService;

    private static Balloon b1;
    private static Balloon b2;
    private static Manufacturer m1;
    private static Manufacturer m2;
    private static boolean dataInitialized = false;


    private HtmlUnitDriver driver;

    private static User adminUser;

    private static String user = "user";
    private static String admin = "admin";


    @BeforeEach
    private void setup() {
        this.driver = new HtmlUnitDriver(true);
        initData();
    }

    @AfterEach
    public void destroy() {
        if (this.driver != null) {
            this.driver.close();
        }
    }


    private void initData() {
        if (!dataInitialized) {
            m1 = manufacturerService.save("m1", "m1", "m1").get();
            m2 = manufacturerService.save("m2", "m2", "m1").get();

            adminUser = authService.register(admin, admin, admin, admin, admin, LocalDate.now());
            dataInitialized = true;
        }
    }

    @Test
    public void testScenario() throws Exception {
        BalloonsPage balloonsPage = BalloonsPage.to(this.driver);
        balloonsPage.assertElements(0, 0, 0, 0, 0);
        LoginPage loginPage = LoginPage.openLogin(this.driver);

        balloonsPage = LoginPage.doLogin(this.driver, loginPage, adminUser.getUsername(), admin);
        balloonsPage.assertElements(0, 0, 0, 0, 1);

        balloonsPage = AddOrEditBalloon.addBalloon(this.driver, "test", "test", m2.getName());
        balloonsPage.assertElements(1, 1, 1, 1, 1);

        balloonsPage = AddOrEditBalloon.addBalloon(this.driver, "test2", "test2", m2.getName());
        balloonsPage.assertElements(2, 2, 2, 2, 1);

        balloonsPage.getDeleteButtons().get(1).click();
        balloonsPage.assertElements(1, 1, 1, 1, 1);

        balloonsPage = AddOrEditBalloon.editBalloon(this.driver, balloonsPage.getEditButtons().get(0), "test2", "test2", m2.getName());
        balloonsPage.assertElements(1, 1, 1, 1, 1);

        LoginPage.logout(this.driver);
        Assert.assertEquals("url do not match", "http://localhost:9999/login", this.driver.getCurrentUrl());


        balloonsPage.getGoToBalloons().click();
        Assert.assertEquals("url do not match", "http://localhost:9999/balloons", this.driver.getCurrentUrl());
        balloonsPage.assertElements(1, 0, 0, 1, 0);
        balloonsPage.getRadioButtons().get(0).click();

        balloonsPage.getSubmit().click();
        Assert.assertEquals("url do not match", "http://localhost:9999/selectBalloon", this.driver.getCurrentUrl());


    }
}