import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class SamokatOrderPageTest {
    public final String name;
    public final String surName;
    public final String address;
    public final String phoneNum;
    public final String metroStation;
    public final String deliveryDate;
    public final String rentalDuration;
    public final String comment;

    public SamokatOrderPageTest(String name, String surName, String address, String phoneNum, String metroStation, String deliveryDate, String rentalDuration, String comment) {
        this.name = name;
        this.surName = surName;
        this.address = address;
        this.phoneNum = phoneNum;
        this.metroStation = metroStation;
        this.deliveryDate = deliveryDate;
        this.rentalDuration = rentalDuration;
        this.comment = comment;
    }

    @Parameterized.Parameters
    @SuppressWarnings("rawtypes")
    public static Collection testOrderDatas() {
        return Arrays.asList(new Object[][]{
                {"Константин", "Константинов", "Обычный Адрес", "+79876543221", "Тверская", "03.09.23", "1", "Комментарий"},
                {"Илон", "Маск", "Калифорния, фиг пойми где", "+11234561234", "Пушкинская", "05.10.23", "5", "Прикольный комментарий"}
        });
    }

    public WebDriver driver;

    @Before
    public void start() {
        switch (System.getProperty("samokat.test.browser", "firefox")) {
            case "chrome":
                driver = new ChromeDriver(new ChromeOptions().addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage"));
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    @Test
    public void testLowerOrderButton() {
        HomePage home = new HomePage(driver);
        home.navigateToHomePage();
        home.waitUntilLoaded();
        home.acceptCookies();
        home.pressLowerOrderButton();

        OrderPage order = new OrderPage(driver);
        order.waitUntilLoaded();
        order.fillInTextFields(name, surName, address, phoneNum);
        order.selectMetroStation(metroStation);
        order.clickProceedButton();

        DetailsPage details = new DetailsPage(driver);
        details.waitUntilLoaded();
        details.inputDeliveryDate(deliveryDate);
        details.setRentalDuration(Integer.parseInt(rentalDuration));
        details.setBlackColorCheckBox();
        details.leaveComment(comment);
        details.pressSubmitButton();
        details.confirmOrder();
        details.checkSuccessOrder();
    }

    @Test
    public void testUpperOrderButton() {
        HomePage home = new HomePage(driver);
        home.navigateToHomePage();
        home.waitUntilLoaded();
        home.acceptCookies();
        home.pressUpperOrderButton();

        OrderPage order = new OrderPage(driver);
        order.waitUntilLoaded();
        order.fillInTextFields(name, surName, address, phoneNum);
        order.selectMetroStation(metroStation);
        order.clickProceedButton();

        DetailsPage details = new DetailsPage(driver);
        details.waitUntilLoaded();
        details.inputDeliveryDate(deliveryDate);
        details.setRentalDuration(Integer.parseInt(rentalDuration));
        details.setBlackColorCheckBox();
        details.leaveComment(comment);
        details.pressSubmitButton();
        details.confirmOrder();
        details.checkSuccessOrder();
    }

    @After
    public void quit() {
        driver.quit();
    }
}