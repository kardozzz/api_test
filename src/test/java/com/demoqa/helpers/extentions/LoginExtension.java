package com.demoqa.helpers.extentions;

import com.demoqa.api.AccountApi;
import com.demoqa.data.AuthData;
import com.demoqa.data.LoginData;
import com.demoqa.models.LoginRqModel;
import com.demoqa.models.LoginRsModel;
import io.qameta.allure.Step;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;

public class LoginExtension implements BeforeEachCallback {

    @Override
        public void beforeEach(ExtensionContext context) {
            // Просто выполняем логин перед каждым тестом.
            performLogin();
        }

        @Step("Выполнить логин через API и сохранить токен")
        private void performLogin() {
            // Выполняем логин через API
            LoginRsModel loginResponse = AccountApi.loginUser();

            // Сохраняем авторизационные данные в AuthData
            AuthData.userId = loginResponse.getUserId();
            AuthData.expires = loginResponse.getExpires();
            AuthData.token = loginResponse.getToken();
        }
}
