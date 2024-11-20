package com.demoqa.helpers.extentions;

import com.demoqa.api.AccountApi;
import com.demoqa.models.AuthRsModel;
import com.demoqa.models.LoginRsModel;
import io.qameta.allure.Step;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class LoginExtension implements BeforeEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) {

        performLogin();
    }

    @Step("Выполнить логин через API и сохранить токен")
    private void performLogin() {
        // Выполняем логин через API
        LoginRsModel loginResponse = AccountApi.loginUser();

        // Сохраняем авторизационные данные в AuthRsModel
        AuthRsModel.userId = loginResponse.getUserId();
        AuthRsModel.expires = loginResponse.getExpires();
        AuthRsModel.token = loginResponse.getToken();
    }
}
