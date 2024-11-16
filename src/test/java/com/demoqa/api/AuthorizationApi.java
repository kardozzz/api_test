package com.demoqa.api;

import com.demoqa.data.LoginData;
import com.demoqa.models.LoginRequestModel;
import com.demoqa.models.LoginResponseModel;

import static com.demoqa.specs.DataSpec.demoQaBookStoreWithJsonRequest;
import static com.demoqa.specs.DataSpec.response200;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;

public class AuthorizationApi {
    public static LoginResponseModel getAuthorizationCookie() {
        LoginResponseModel response;
        LoginRequestModel request = new LoginRequestModel();
        LoginData loginData = new LoginData();
        request.setUserName(loginData.getUserName());
        request.setPassword(loginData.getPassword());

        response = step("Сделать запрос логина, и записать ответ", () ->
                given(demoQaBookStoreWithJsonRequest)
                        .body(request)

                        .when()
                        .post("/Account/v1/Login")

                        .then()
                        .spec(response200)
                        .extract().as(LoginResponseModel.class));

        return response;
    }
}
