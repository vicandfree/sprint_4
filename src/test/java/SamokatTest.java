import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public abstract class SamokatTest {

    public WebDriver driver;
    public abstract void initDriver();

    @Before
    public void start() {
        initDriver();
    }

    public static final String[] answers = {
            "Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
            "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.",
            "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.",
            "Только начиная с завтрашнего дня. Но скоро станем расторопнее."
    };

    @Test
    public void testImportantQuestions() {
        HomePage home = new HomePage(driver);
        home.navigateToHomePage();
        home.acceptCookies();

        for (int index = 0; index < answers.length; index++) {
            assert home.getAnswerToFAQ(index).equals(answers[index]);
        }
    }

    public static final String[][] orderDatas = {
            {"Константин", "Константинов", "Обычный Адрес", "+79876543221", "Тверская", "5", "7", "1", "Комментарий"},
            {"Илон", "Маск", "Калифорния, фиг пойми где", "+11234561234", "Пушкинская", "3", "2", "5", "Прикольный комментарий"}
    };

    @Test
    public void testOrder1() {
        for (String[] data : orderDatas) {
            HomePage home = new HomePage(driver);
            home.navigateToHomePage();
            home.acceptCookies();
            home.pressUpperOrderButton();

            OrderPage order = new OrderPage(driver);
            order.fillInTextFields(data[0], data[1], data[2], data[3]);
            order.selectMetroStation(data[4]);
            order.clickProceedButton();

            DetailsPage details = new DetailsPage(driver);
            details.selectDeliveryDate(data[5], data[6]);
            details.setRentalDuration(data[7]);
            details.setBlackColorCheckBox();
            details.leaveComment(data[8]);
            details.pressSubmitButton();
            details.confirmOrder();
        }
    }

    @After
    public void quit() {
        driver.quit();
    }
}