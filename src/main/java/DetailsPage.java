import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class DetailsPage {

    private final WebDriver driver;
    private static final By orderHeader = By.cssSelector(".Order_Content__bmtHS .Order_Header__BZXOb");
    private final By deliveryDateInput = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private final By monthSelect = By.xpath("//button[@aria-label='Next Month']");
    private final By rentalTermField = By.cssSelector(".Dropdown-control");
    private final By blackColorCheckBox = By.id("black");
    private final By grayColorCheckBox = By.id("grey");
    private final By commentInput = By.xpath("//input[@placeholder='Комментарий для курьера']");
    private final By submitButton = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    private final By confirmationButton = By.xpath("//button[text()='Да']");

    public DetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectDeliveryDate(String calendarRow, String calendarColumn) {
        By dateSelect = By.xpath("//body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/div[2]/div[" + calendarRow + "]/div[" + calendarColumn + "]");
        driver.findElement(deliveryDateInput).click();

        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        driver.findElement(monthSelect).click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        WebElement dateToPick = driver.findElement(dateSelect);
        dateToPick.isEnabled();
        dateToPick.click();

        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    public void inputDeliveryDate(String calendarDate) {
        driver.findElement(deliveryDateInput).click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(deliveryDateInput).sendKeys(calendarDate);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(deliveryDateInput).sendKeys(Keys.ENTER);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public void setRentalDuration(String numberOfDays) {
        driver.findElement(rentalTermField).isEnabled();
        driver.findElement(rentalTermField).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        WebElement dropDownField = driver.findElement(By.xpath
                ("/html/body/div/div/div[2]/div[2]/div[2]/div[2]/div[" + numberOfDays + "]"));
        dropDownField.isEnabled();
        dropDownField.click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    public void setBlackColorCheckBox() {
        driver.findElement(blackColorCheckBox).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    public void setGrayColorCheckBox() {
        driver.findElement(grayColorCheckBox).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    public void leaveComment(String comment) {
        WebElement commentInputField = driver.findElement(commentInput);
        commentInputField.clear();
        commentInputField.sendKeys(comment);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    public void pressSubmitButton() {
        driver.findElement(submitButton).isEnabled();
        driver.findElement(submitButton).click();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

    }

    public void confirmOrder() {
        driver.findElement(confirmationButton).isEnabled();
        driver.findElement(confirmationButton).click();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
    }

    public static By getOrderHeader() {
        return orderHeader;
    }
}