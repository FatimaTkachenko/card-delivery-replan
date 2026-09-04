import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

class CardDeliveryTest {

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.headless = true;
        Configuration.timeout = 15000;
        Configuration.pageLoadTimeout = 30000;
    }

    @AfterEach
    void tearDown() {
        Selenide.closeWebDriver();
    }

    @Test
    void shouldReplanMeeting() {
        // Замени URL на свой
        open("https://your-app-url.example.com");

        // Тут реальные шаги теста:
        // open(...);
        // $(...).click();
        // $(...).setValue(...);
        // $(...).shouldHave(...);
    }
}
