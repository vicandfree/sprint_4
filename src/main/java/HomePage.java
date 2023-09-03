import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    public static final String URL = "https://qa-scooter.praktikum-services.ru/";

    private final WebDriver driver;
    private final By upperOrderButton = By.className("Button_Button__ra12g");
    private final By lowerOrderButton = By.className("Button_Middle__1CSJM");
    private final By cookieButton = By.cssSelector(".App_CookieButton__3cvqF");
    private final By homeHeader = By.className("Home_Header__iJKdX");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToHomePage() {
        driver.navigate().to(URL);
    }

    public void waitUntilLoaded() {
        new WebDriverWait(driver, 5).until(driver -> driver.findElement(homeHeader).getText() != null && !driver.findElement(homeHeader).getText().isEmpty());
    }

    public void acceptCookies() {
        driver.findElements(cookieButton).forEach(WebElement::click);
    }

    public void pressUpperOrderButton() {
        driver.findElement(upperOrderButton).click();
    }

    public void pressLowerOrderButton() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(lowerOrderButton));
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(lowerOrderButton)).click();
    }

    public String getAnswerToFAQ(int FAQnum) {
        WebElement FAQuest;
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
                FAQuest = driver.findElement(By.id("accordion__heading-" + FAQnum)));

        new WebDriverWait(driver, 5).
                until(ExpectedConditions.elementToBeClickable(FAQuest)).click();

        new WebDriverWait(driver, 5).
                until(ExpectedConditions.visibilityOfElementLocated(By.id("accordion__panel-" + FAQnum)));

        return driver.findElement(By.id("accordion__panel-" + FAQnum)).getText();
    }
}