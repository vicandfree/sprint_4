import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DetailsPage {

    private final WebDriver driver;
    private final By orderHeader = By.cssSelector(".Order_Content__bmtHS .Order_Header__BZXOb");
    private final By deliveryDateInput = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private final By rentalTermField = By.cssSelector(".Dropdown-control");
    private final By rentalTermOptions = By.className("Dropdown-option");
    private final By blackColorCheckBox = By.id("black");
    private final By grayColorCheckBox = By.id("grey");
    private final By commentInput = By.xpath("//input[@placeholder='Комментарий для курьера']");
    private final By submitButton = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    private final By confirmationButton = By.xpath("//button[text()='Да']");
    private final By successOrder = By.xpath("//div[text()='Заказ оформлен']");

    public DetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitUntilLoaded() {
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(orderHeader));
    }

    public void inputDeliveryDate(String calendarDate) {
        driver.findElement(deliveryDateInput).click();
        driver.findElement(deliveryDateInput).sendKeys(calendarDate);
        driver.findElement(deliveryDateInput).sendKeys(Keys.ENTER);
    }

    public void setRentalDuration(int days) {
        driver.findElement(rentalTermField).click();

        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(rentalTermOptions));

        driver.findElements(rentalTermOptions).get(days).click();
    }

    public void setBlackColorCheckBox() {
        driver.findElement(blackColorCheckBox).click();
    }

    public void setGrayColorCheckBox() {
        driver.findElement(grayColorCheckBox).click();
    }

    public void leaveComment(String comment) {
        WebElement commentInputField = driver.findElement(commentInput);
        commentInputField.clear();
        commentInputField.sendKeys(comment);
    }

    public void pressSubmitButton() {
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(submitButton)).click();

    }

    public void confirmOrder() {
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(confirmationButton)).click();;
    }

    public void checkSuccessOrder() {
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(successOrder));
    }
}