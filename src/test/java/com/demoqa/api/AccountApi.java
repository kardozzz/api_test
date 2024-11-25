package com.demoqa.api;

import com.demoqa.models.GetBookListModel;
import com.demoqa.models.LoginRsModel;
import com.demoqa.models.LoginUserModel;

import static com.demoqa.specs.DataSpec.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;

public class AccountApi {

    public static LoginRsModel getAuthorizationCookie() {
            LoginRsModel response;
            LoginUserModel request = new LoginUserModel(System.getProperty("userName"),
                    System.getProperty("userPassword"));

            response = step("Сделать запрос логина, и записать ответ", () ->
                    given(createRequestSpec)
                            .body(request)

                            .when()
                            .post("/Account/v1/Login")

                            .then()
                            .spec(authUserResponse200Spec)
                            // .statusCode(200)
                            .extract().as(LoginRsModel.class));

            return response;
        }
        public static GetBookListModel getListOfBooks() {
            GetBookListModel response;
            response = step("Получить список книг", () ->
                    given(createBookStoreRequestSpec)
                            .header("Authorization", "Bearer " + getAuthorizationCookie().getToken())
                            .queryParam("UserId", getAuthorizationCookie().getUserId())
                            .when()
                            .get("/Account/v1/User/" + getAuthorizationCookie().getUserId())
                            .then()
                            .spec(authUserResponse200Spec)
                            .extract().as(GetBookListModel.class));

            return response;
        }
}

