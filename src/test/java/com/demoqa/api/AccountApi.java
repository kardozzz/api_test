package com.demoqa.api;

import com.demoqa.models.auth.LoginRqModel;
import com.demoqa.models.auth.LoginRsModel;
import io.qameta.allure.Step;

import static com.demoqa.specs.DataSpec.requestSpec;
import static com.demoqa.specs.DataSpec.responseSpec200;
import static io.restassured.RestAssured.given;


public class AccountApi {

    @Step("Take authorization data")
    public static LoginRsModel getAuthData(String userName, String userPassword) {

        LoginRqModel request = new LoginRqModel();
        request.setUserName(userName);
        request.setPassword(userPassword);

        return given()
                .spec(requestSpec)
                .body(request)

                .when()
                .post("/Account/v1/Login")

                .then()
                .spec(responseSpec200)
                .extract().as(LoginRsModel.class);
    }
}

