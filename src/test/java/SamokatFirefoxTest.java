import org.openqa.selenium.firefox.FirefoxDriver;

public class SamokatFirefoxTest extends SamokatTest{
    @Override
    public void initDriver() {
        driver = new FirefoxDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }
}