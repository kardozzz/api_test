package com.demoqa.api;

import com.demoqa.models.GetListBoookResponseModel;

import static com.demoqa.data.AuthData.USER_ID;
import static com.demoqa.data.AuthData.USER_TOKEN;
import static com.demoqa.specs.DataSpec.demoQaBookStoreCommonRequest;
import static com.demoqa.specs.DataSpec.response200;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;

public class AccountApi {
        public static GetListBoookResponseModel getListOfBooks() {
            GetListBoookResponseModel response;
            response = step("Сделать запрос списка книг в корзине, и записать его в переменную", () ->
                    given(demoQaBookStoreCommonRequest)
                            .header("Authorization", "Bearer " + USER_TOKEN)
                            .queryParam("UserId", USER_ID)

                            .when()
                            .get("/Account/v1/User/" + USER_ID)

                            .then()
                            .spec(response200)
                            .extract().as(GetListBoookResponseModel.class));

            return response;
        }
    }
