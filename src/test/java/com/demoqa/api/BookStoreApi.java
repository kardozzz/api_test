package api;

import com.demoqa.models.AddBookRequestModel;
import com.demoqa.models.BookModel;

import java.util.List;

import static com.demoqa.data.AuthData.USER_ID;
import static com.demoqa.data.AuthData.USER_TOKEN;
import static com.demoqa.specs.DataSpec.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;


public class BookStoreApi {

    public static void deleteAllBooksInCart() {


        step("Сделать запрос удаления определенной книги из корзины", () -> {
            given(demoQaBookStoreWithJsonRequest)
                    .header("Authorization", "Bearer " + USER_TOKEN)
                    .queryParam("UserId", USER_ID)

                    .when()
                    .delete("/BookStore/v1/Books")

                    .then()
                    .spec(response204);
        });

    }

    public static void addBookToList(String isbn) {

        AddBookRequestModel request = new AddBookRequestModel();
        BookModel bookModel = new BookModel();
        bookModel.setIsbn(isbn);
        request.setUserId(USER_ID);
        request.setCollectionOfIsbns(List.of(bookModel));


        step("Сделать запрос добавления книги в корзину", () -> {
            given(demoQaBookStoreWithJsonRequest)
                    .header("Authorization", "Bearer " + USER_TOKEN)
                    .body(request)

                    .when()
                    .post("/BookStore/v1/Books")

                    .then()
                    .spec(response201);
        });

    }
}