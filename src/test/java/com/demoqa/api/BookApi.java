package api;

import com.demoqa.models.*;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.demoqa.data.AuthData.token;
import static com.demoqa.data.AuthData.userId;
import static com.demoqa.specs.DataSpec.*;
import static io.restassured.RestAssured.given;
//Добавить тесты на добавление и удаление конкретной книги, передать книги в женкинсе, и проверить удаление этой книги

public class BookApi {


    @Step
    public static void deleteAllBooksInProfile() {

        given(requestSpec)
                .header("Authorization", "Bearer " + token)
                .queryParam("UserId",userId)

                .when()
                .delete("/BookStore/v1/Books")

                .then()
                .spec(response204);
    }

    @Step
    public static void addBooksInProfile() {
        IsbnModel isbnModel = new IsbnModel();
        AddBookRqModel request = new AddBookRqModel(userId,List.of(isbnModel));

        given(requestSpec)
                .header("Authorization", "Bearer " + token)
                .body(request)
                .when()
                .post("BookStore/v1/Books")

                .then()
                .spec(response201);
    }

    @Step
    public static void deleteBooksInProfile() {
        IsbnModel isbnModel = new IsbnModel();
        DeleteBookRqModel request = new DeleteBookRqModel(isbnModel.getIsbn(),userId);
        given(requestSpec)
                .header("Authorization", "Bearer " + token)
                .body(request)

                .when()
                .delete("/BookStore/v1/Book")

                .then()
                .spec(response204);
    }

}




