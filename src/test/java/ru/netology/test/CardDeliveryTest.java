package ru.netology.test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {

    @BeforeAll
    static void setUpAll() {
        // Автоматически определяем ОС и настраиваем драйвер
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("linux")) {
            System.setProperty("webdriver.chrome.driver", "/usr/bin/google-chrome");
        }
        Configuration.browser = "chrome";
        Configuration.headless = true;
        Configuration.timeout = 10000;
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void shouldReplanMeeting() {
        DataGenerator.UserInfo firstUser = DataGenerator.generateValidUser();

        open("http://localhost:9999");

        $("[data-test-id='city'] input").setValue(firstUser.getCity());
        $("[data-test-id='date'] input").doubleClick().sendKeys(firstUser.getDate());
        $("[data-test-id='name'] input").setValue(firstUser.getName());
        $("[data-test-id='phone'] input").setValue(firstUser.getPhone());
        $("[data-test-id='agreement'] .checkbox__box").click();

        $("button").click();

        $("[data-test-id='success-notification']").shouldBe(visible)
                .shouldHave(text("Встреча успешно запланирована"));

        DataGenerator.UserInfo secondUser = DataGenerator.generateUserWithNewDate();

        $("[data-test-id='date'] input").doubleClick().sendKeys(secondUser.getDate());

        $("button").click();

        $("[data-test-id='replan-notification']").shouldBe(visible)
                .shouldHave(text("У вас уже запланирована встреча на другую дату. Перепланировать?"));

        $("[data-test-id='replan-notification'] button").click();

        $("[data-test-id='success-notification']").shouldBe(visible)
                .shouldHave(text("Встреча успешно перепланирована"));
    }
}