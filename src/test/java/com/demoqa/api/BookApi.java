package api;

import com.demoqa.models.AddBookRqModel;
import com.demoqa.models.IsbnModel;

import java.util.List;

import static com.demoqa.helpers.extentions.LoginExtension.cookies;
import static com.demoqa.specs.DataSpec.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;


public class BookApi {


    public static void deleteAllBooksInCart() {
        step("Удалить книги из корзины", () -> {
            given(createRequestSpec)
                    .header("Authorization", "Bearer " + cookies.getToken())
                    .queryParam("UserId", cookies.getUserId())
                    .when()
                    .delete("/BookStore/v1/Books")
                    .then()
                    .spec(deleteBook204Spec);
        });

    }

    public static void addBookToList(String isbn) {

        IsbnModel isbnModel = new IsbnModel(isbn);
        AddBookRqModel request = new AddBookRqModel(cookies.getUserId(), List.of(isbnModel));

        step("Добавить книгу в корзину", () -> {
            given(createRequestSpec)
                    .header("Authorization", "Bearer " + cookies.getToken())
                    .body(request)
                    .when()
                    .post("/BookStore/v1/Books")
                    .then()
                    .spec(successfulResponse201Spec);
        });

    }
}




