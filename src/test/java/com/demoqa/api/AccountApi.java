package com.demoqa.api;

import com.demoqa.models.AuthRsModel;
import com.demoqa.models.GetBookListRsModel;
import com.demoqa.models.LoginRqModel;
import com.demoqa.models.LoginRsModel;
import io.qameta.allure.Step;

import static com.demoqa.helpers.extentions.LoginExtension.cookies;
import static com.demoqa.specs.DataSpec.requestSpec;
import static com.demoqa.specs.DataSpec.response200;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class AccountApi {

    public static LoginRsModel getAuthorizationCookie() {
        LoginRsModel response;
        LoginRqModel request = new LoginRqModel("sokol","Sokol22@");

        response = step("Сделать запрос логина, и записать ответ", () ->
                given(requestSpec)
                        .body(request)

                        .when()
                        .post("/Account/v1/Login")

                        .then()
                        .spec(response200)
                        // .statusCode(200)
                        .extract().as(LoginRsModel.class));

        return response;
    }

    @Step("Получить данные профиля пользователя")
    public static void getUserProfile() {
        given(requestSpec)
                .contentType("application/json")
                .header("Authorization", "Bearer " + cookies.getToken())
                .when()
                .get("/Account/v1/User/" + cookies.getUserId())
                .then()
                .spec(response200);//
    }

    @Step("Получить данные профиля пользователя с проверкой, что нет книг")
    public static void verifyUserHasNoBooks() {
        GetBookListRsModel userProfile = given(requestSpec)
                .contentType("application/json")
                .header("Authorization", "Bearer " + cookies.getToken())
                .when()
                .get("/Account/v1/User/" + cookies.getUserId())
                .then()
                .spec(response200)
                .extract().as(GetBookListRsModel.class);

        // Проверяем, что у пользователя нет книг
        assertThat(userProfile.getBooks()).isEmpty();
    }
}

