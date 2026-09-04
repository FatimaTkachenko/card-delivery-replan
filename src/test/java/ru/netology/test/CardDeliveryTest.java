package ru.netology.test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {

    @BeforeAll
    static void setUpAll() {
        Configuration.headless = false;
        Configuration.timeout = 10000;
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

        sleep(5000);
    }
}