import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class OrderPage {

    private final WebDriver driver;
    private final By firstNameInput = By.xpath("//input[@placeholder='* Имя']");
    private final By lastNameInput = By.xpath("//input[@placeholder='* Фамилия']");
    private final By addressInput = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metroStationField = By.xpath("//input[1][@placeholder='* Станция метро']");
    private final By phoneNum = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By proceedButton = By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM");
    private static final By orderForm = By.cssSelector(".Order_Content__bmtHS");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillInTextFields(String firstName, String lastName, String address, String phoneNumber) {
        driver.findElement(firstNameInput).sendKeys(firstName);
        driver.findElement(lastNameInput).sendKeys(lastName);
        driver.findElement(addressInput).sendKeys(address);
        driver.findElement(phoneNum).sendKeys(phoneNumber);
    }

    public void selectMetroStation(String station) {
        WebElement selectedStation = driver.findElement(metroStationField);
        selectedStation.click();
        selectedStation.sendKeys(station);
        selectedStation.sendKeys(Keys.ARROW_DOWN);
        selectedStation.sendKeys(Keys.ENTER);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    public void clickProceedButton() {
        driver.findElement(proceedButton).isEnabled();
        driver.findElement(proceedButton).click();

        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(DetailsPage.getOrderHeader()));
    }

    public static By getOrderForm() {
        return orderForm;
    }
}