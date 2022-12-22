package mk.finki.ukim.mk.lab.selenium;// Generated by Selenium IDE
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.*;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SeleniumIDEBalloonShopTestReference {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() {
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void untitled() {
    // Test name: Untitled
    // Step # | name | target | value
    // 1 | open | /login | 
    driver.get("http://localhost:9090/login");
    // 2 | setWindowSize | 817x816 | 
    driver.manage().window().setSize(new Dimension(817, 816));
    // 3 | type | id=username | admin
    driver.findElement(By.id("username")).sendKeys("admin");
    // 4 | type | id=password | admin
    driver.findElement(By.id("password")).sendKeys("admin");
    // 5 | click | css=.btn-lg | 
    driver.findElement(By.cssSelector(".btn-lg")).click();
    // 6 | click | css=tr:nth-child(1) > td > .btn > .fa | 
    driver.findElement(By.cssSelector("tr:nth-child(1) > td > .btn > .fa")).click();
    // 7 | mouseDownAt | id=balloonDescription | 61,23
    {
      WebElement element = driver.findElement(By.id("balloonDescription"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).clickAndHold().perform();
    }
    // 8 | mouseMoveAt | id=balloonDescription | 61,23
    {
      WebElement element = driver.findElement(By.id("balloonDescription"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }
    // 9 | mouseUpAt | id=balloonDescription | 61,23
    {
      WebElement element = driver.findElement(By.id("balloonDescription"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).release().perform();
    }
    // 10 | click | id=balloonDescription | 
    driver.findElement(By.id("balloonDescription")).click();
    // 11 | click | id=balloonDescription | 
    driver.findElement(By.id("balloonDescription")).click();
    // 12 | type | id=balloonDescription | desc 3
    driver.findElement(By.id("balloonDescription")).sendKeys("desc 3");
    // 13 | click | css=.btn:nth-child(4) | 
    driver.findElement(By.cssSelector(".btn:nth-child(4)")).click();
    // 14 | click | linkText=Add new product | 
    driver.findElement(By.linkText("Add new product")).click();
    // 15 | click | id=balloonName | 
    driver.findElement(By.id("balloonName")).click();
    // 16 | type | id=balloonName | Star Balloon
    driver.findElement(By.id("balloonName")).sendKeys("Star Balloon");
    // 17 | click | id=balloonDescription | 
    driver.findElement(By.id("balloonDescription")).click();
    // 18 | type | id=balloonDescription | star desc
    driver.findElement(By.id("balloonDescription")).sendKeys("star desc");
    // 19 | click | name=manufacturerId | 
    driver.findElement(By.name("manufacturerId")).click();
    // 20 | select | name=manufacturerId | label=Manuf 2
    {
      WebElement dropdown = driver.findElement(By.name("manufacturerId"));
      dropdown.findElement(By.xpath("//option[. = 'Manuf 2']")).click();
    }
    // 21 | click | css=.btn:nth-child(4) | 
    driver.findElement(By.cssSelector(".btn:nth-child(4)")).click();
    // 22 | click | css=tr:nth-child(4) form .fa | 
    driver.findElement(By.cssSelector("tr:nth-child(4) form .fa")).click();
    // 23 | click | id=7 | 
    driver.findElement(By.id("7")).click();
    // 24 | click | css=.btn-success | 
    driver.findElement(By.cssSelector(".btn-success")).click();
    // 25 | click | id=Big | 
    driver.findElement(By.id("Big")).click();
    // 26 | click | css=.btn-success | 
    driver.findElement(By.cssSelector(".btn-success")).click();
    // 27 | click | id=clientName | 
    driver.findElement(By.id("clientName")).click();
    // 28 | type | id=clientName | dimi
    driver.findElement(By.id("clientName")).sendKeys("dimi");
    // 29 | click | id=clientAddress | 
    driver.findElement(By.id("clientAddress")).click();
    // 30 | type | id=clientAddress | 1234567
    driver.findElement(By.id("clientAddress")).sendKeys("1234567");
    // 31 | click | id=dateCreated | 
    driver.findElement(By.id("dateCreated")).click();
    // 32 | click | id=dateCreated | 
    driver.findElement(By.id("dateCreated")).click();
    // 33 | type | id=dateCreated | 2022-12-23T11:38
    driver.findElement(By.id("dateCreated")).sendKeys("2022-12-23T11:38");
    // 34 | click | css=html | 
    driver.findElement(By.cssSelector("html")).click();
    // 35 | click | css=.btn-success | 
    driver.findElement(By.cssSelector(".btn-success")).click();
    // 36 | click | linkText=See all orders | 
    driver.findElement(By.linkText("See all orders")).click();
    // 37 | click | name=username | 
    driver.findElement(By.name("username")).click();
    // 38 | select | name=username | label=Jovan
    {
      WebElement dropdown = driver.findElement(By.name("username"));
      dropdown.findElement(By.xpath("//option[. = 'Jovan']")).click();
    }
    // 39 | click | css=.btn:nth-child(2) | 
    driver.findElement(By.cssSelector(".btn:nth-child(2)")).click();
    // 40 | click | name=username | 
    driver.findElement(By.name("username")).click();
    // 41 | click | id=dateFrom | 
    driver.findElement(By.id("dateFrom")).click();
    // 42 | type | id=dateFrom | 2022-12-20T11:38
    driver.findElement(By.id("dateFrom")).sendKeys("2022-12-20T11:38");
    // 43 | click | css=html | 
    driver.findElement(By.cssSelector("html")).click();
    // 44 | click | id=dateTo | 
    driver.findElement(By.id("dateTo")).click();
    // 45 | type | id=dateTo | 2022-12-28T11:38
    driver.findElement(By.id("dateTo")).sendKeys("2022-12-28T11:38");
    // 46 | click | css=html | 
    driver.findElement(By.cssSelector("html")).click();
    // 47 | click | css=.btn:nth-child(3) | 
    driver.findElement(By.cssSelector(".btn:nth-child(3)")).click();
    // 48 | close |  | 
    driver.close();
  }
}
