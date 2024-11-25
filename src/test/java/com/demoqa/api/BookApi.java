package com.demoqa.api;

import com.demoqa.models.AddBookRqModel;
import com.demoqa.models.IsbnModel;

import java.util.List;

import static com.demoqa.api.AccountApi.getAuthorizationCookie;
import static com.demoqa.specs.DataSpec.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;


public class BookApi {


    public static void deleteAllBooksInCart() {
        step("Удалить книги из корзины", () -> {
            given(createRequestSpec)
                    .header("Authorization", "Bearer " + getAuthorizationCookie().getToken())
                    .queryParam("UserId", getAuthorizationCookie().getUserId())
                    .when()
                    .delete("/BookStore/v1/Books")
                    .then()
                    .spec(deleteBook204Spec);
        });

    }

    public static void addBookToList(String isbn) {

        IsbnModel isbnModel = new IsbnModel(isbn);
        AddBookRqModel request = new AddBookRqModel(getAuthorizationCookie().getUserId(), List.of(isbnModel));

        step("Добавить книгу в корзину", () -> {
            given(createRequestSpec)
                    .header("Authorization", "Bearer " + getAuthorizationCookie().getToken())
                    .body(request)
                    .when()
                    .post("/BookStore/v1/Books")
                    .then()
                    .spec(successfulResponse201Spec);
        });

    }
}




