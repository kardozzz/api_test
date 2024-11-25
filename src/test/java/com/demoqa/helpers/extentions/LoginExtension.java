package com.demoqa.helpers.extensions;

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
    private static final String COOKIES_KEY = "loginCookies";

    @Override
    public void beforeEach(ExtensionContext context) {
        ExtensionContext.Store store = context.getStore(ExtensionContext.Namespace.create(getClass()));

        step("Получение cookies для авторизации", () -> {
            LoginRsModel cookies = AccountApi.getAuthorizationCookie();
            store.put(COOKIES_KEY, cookies); // Сохраняем cookies в Store
        });

        step("Добавление cookies в браузер", () -> {
            LoginRsModel cookies = store.get(COOKIES_KEY, LoginRsModel.class);
            if (cookies != null) {
                addCookiesToBrowser(cookies);
            } else {
                throw new IllegalStateException("Cookies не найдены в Store!");
            }
        });

        step("Проверка успешного входа в учетную запись", this::verifyLogin);
    }

    private void addCookiesToBrowser(LoginRsModel cookies) {
        open("/favicon.ico"); // Открытие технической страницы для установки cookies

        addCookie("userID", cookies.getUserId());
        addCookie("expires", cookies.getExpires());
        addCookie("token", cookies.getToken());
    }

    private void verifyLogin() {
        open("/profile"); // Открываем страницу профиля
        String expectedUserName = System.getProperty("userName", "defaultUserName");
        $("#userName-value").shouldHave(text(expectedUserName)); // Проверяем имя пользователя
    }

    private void addCookie(String name, String value) {
        if (value != null && !value.isEmpty()) {
            getWebDriver().manage().addCookie(new Cookie(name, value));
        } else {
            throw new IllegalArgumentException("Cookie " + name + " имеет некорректное значение.");
        }
    }
}