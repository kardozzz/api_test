package com.demoqa.helpers.extentions;

import com.demoqa.api.AccountApi;
import com.demoqa.models.LoginRsModel;
import org.openqa.selenium.Cookie;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;

public class LoginExtension implements BeforeEachCallback {
    public static LoginRsModel cookies;

    @Override
    public void beforeEach(ExtensionContext context) {
        cookies = AccountApi.getAuthorizationCookie();

        step("Добавить cookie (в ответе) из браузера", () -> {
            open("/favicon.ico");
            getWebDriver().manage().addCookie(new Cookie("userId", cookies.getUserId()));
            getWebDriver().manage().addCookie(new Cookie("expires", cookies.getExpires()));
            getWebDriver().manage().addCookie(new Cookie("token", cookies.getToken()));
        });

        step("Проверить успешный вход в учетную запись", () -> {
                    open("/profile");
                    $("#userName-value").shouldHave(text("sokol"));
                }
        );

    }

}

