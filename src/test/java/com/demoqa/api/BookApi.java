package api;

import com.demoqa.models.AddBookRqModel;
import com.demoqa.models.DeleteBookRqModel;
import com.demoqa.models.IsbnModel;
import io.qameta.allure.Step;

import java.util.List;

import static com.demoqa.models.AuthRsModel.token;
import static com.demoqa.models.AuthRsModel.userId;
import static com.demoqa.helpers.extentions.LoginExtension.cookies;
import static com.demoqa.specs.DataSpec.*;
import static io.restassured.RestAssured.given;


public class BookApi {


    @Step("Удалить все книги у пользователя")
    public static void deleteAllBooksInProfile() {

        given(requestSpec)
                .header("Authorization", "Bearer " + cookies.getToken() )
                .queryParam("UserId", cookies.getUserId())

                .when()
                .delete("/BookStore/v1/Books")

                .then()
                .spec(response204);
    }

    @Step("Добавление книги в профиль")
    public static void addBooksInProfile() {
        IsbnModel isbnModel = new IsbnModel();
        AddBookRqModel request = new AddBookRqModel(cookies.getUserId(), List.of(isbnModel));

        given(requestSpec)
                .header("Authorization", "Bearer " + cookies.getToken())
                .body(request)
                .when()
                .post("BookStore/v1/Books")

                .then()
                .spec(response201);
    }

    @Step("Удаление книги из профиля")
    public static void deleteBooksInProfile() {
        IsbnModel isbnModel = new IsbnModel();
        DeleteBookRqModel request = new DeleteBookRqModel(isbnModel.getIsbn(), cookies.getUserId());
        given(requestSpec)
                .header("Authorization", "Bearer " + cookies.getToken())
                .body(request)

                .when()
                .delete("/BookStore/v1/Book")

                .then()
                .spec(response204);
    }

}




