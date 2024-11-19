package com.demoqa.api;

import com.demoqa.data.AuthData;
import com.demoqa.data.LoginData;
import com.demoqa.models.LoginRqModel;
import com.demoqa.models.LoginRsModel;
import io.qameta.allure.Step;

import static com.demoqa.specs.DataSpec.requestSpec;
import static com.demoqa.specs.DataSpec.response200;
import static io.restassured.RestAssured.given;

public class AccountApi {

    @Step("Авторизация пользователя с именем {0}")
    public static LoginRsModel loginUser() {
        LoginRqModel request = new LoginRqModel();
        LoginData loginData = new LoginData();
        request.setUserName(loginData.getUserName()); // Установите имя пользователя
        request.setPassword(loginData.getPassword()); // Установите пароль

        return given(requestSpec)
                .contentType("application/json")
                .body(request)
                .when()
                .post("/Account/v1/Login") // URL для авторизации
                .then()
                .spec(response200)// Проверка успешности
                .extract().as(LoginRsModel.class); // Десериализация ответа
    }

    @Step("Получить данные профиля пользователя")
    public static void getUserProfile() {
        given(requestSpec)
                .contentType("application/json")
                .header("Authorization", "Bearer " + AuthData.token) // Передаём токен
                .when()
                .get("/Account/v1/User/" + AuthData.userId) // URL запроса
                .then()
                .spec(response200);// Проверяем успешный ответ
    }
}
