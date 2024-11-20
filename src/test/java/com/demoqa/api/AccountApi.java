package com.demoqa.api;

import com.demoqa.models.AuthRsModel;
import com.demoqa.data.TestData;
import com.demoqa.models.GetBookListRsModel;
import com.demoqa.models.LoginRqModel;
import com.demoqa.models.LoginRsModel;
import io.qameta.allure.Step;

import static com.demoqa.specs.DataSpec.requestSpec;
import static com.demoqa.specs.DataSpec.response200;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class AccountApi {

    @Step("Авторизация пользователя с именем {0}")
    public static LoginRsModel loginUser() {
        LoginRqModel request = new LoginRqModel();
        TestData testData = new TestData();
        request.setUserName(testData.getUserName());
        request.setPassword(testData.getPassword());

        return given(requestSpec)
                .contentType("application/json")
                .body(request)
                .when()
                .post("/Account/v1/Login")
                .then()
                .spec(response200)
                .extract().as(LoginRsModel.class);
    }

    @Step("Получить данные профиля пользователя")
    public static void getUserProfile() {
        given(requestSpec)
                .contentType("application/json")
                .header("Authorization", "Bearer " + AuthRsModel.token)
                .when()
                .get("/Account/v1/User/" + AuthRsModel.userId)
                .then()
                .spec(response200);//
    }

    @Step("Получить данные профиля пользователя с проверкой, что нет книг")
    public static void verifyUserHasNoBooks() {
        GetBookListRsModel userProfile = given(requestSpec)
                .contentType("application/json")
                .header("Authorization", "Bearer " + AuthRsModel.token)
                .when()
                .get("/Account/v1/User/" + AuthRsModel.userId)
                .then()
                .spec(response200)
                .extract().as(GetBookListRsModel.class);

        // Проверяем, что у пользователя нет книг
        assertThat(userProfile.getBooks()).isEmpty();
    }
}

