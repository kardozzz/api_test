package com.demoqa.api;

import com.demoqa.models.books.*;
import io.qameta.allure.Step;

import java.util.List;
import java.util.Random;

import static com.demoqa.data.AuthData.USER_ID;
import static com.demoqa.data.AuthData.USER_TOKEN;
import static com.demoqa.specs.DataSpec.*;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class BookApi {
    @Step("Удалить все книги из корзины.")
    public BookApi deleteAllBooks() {

        given(requestSpec)
                .header("Authorization", "Bearer " + USER_TOKEN)
                .queryParam("UserId", USER_ID)
                .when()
                .delete("/BookStore/v1/Books")
                .then()
                .spec(responseSpec204);

        return this;
    }

    @Step("Добавить книгу в корзину.")
    public BookApi addBook(String isbn) {

        IsbnModel book = new IsbnModel();
        book.setIsbn(isbn);

        AllBooksProfileRqModel booksCollection = new AllBooksProfileRqModel();
        booksCollection.setUserId(USER_ID);
        booksCollection.setCollectionOfIsbns(List.of(book));

        given(requestSpec)
                .header("Authorization", "Bearer " + USER_TOKEN)
                .body(booksCollection)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .spec(responseSpec201);
        return this;
    }

    @Step("Получить список всех книг пользователя.")
    public List<BookModel> getUserBooks() {

        AllBooksProfileRsModel response =
                given(requestSpec)
                        .when()
                        .header("Authorization", "Bearer " + USER_TOKEN)
                        .get("/Account/v1/User/" + USER_ID)
                        .then()
                        .spec(responseSpec200)
                        .extract().as(AllBooksProfileRsModel.class);

        return response.getBooks();
    }

    @Step("Получить список всех книг магазина.")
    public AllBooksFromStoreRsModel getAllBooks() {

        return given(requestSpec)
                .when()
                .header("Authorization", "Bearer " + USER_TOKEN)
                .get("/BookStore/v1/Books")
                .then()
                .spec(responseSpec200)
                .extract().as(AllBooksFromStoreRsModel.class);
    }

    @Step("Получить случайный ISBN.")
    public String getRandomIsbn() {

        AllBooksFromStoreRsModel booksFromStore = getAllBooks();

        Random random = new Random();
        int randomIndex = random.nextInt(booksFromStore.getBooks().size());

        return booksFromStore.getBooks().get(randomIndex).getIsbn();
    }

    @Step("Проверить, что в корзине пусто (API).")
    public void checkResultOnApi() {

        BookApi booksApi = new BookApi();
        List<BookModel> books = booksApi.getUserBooks();

        assertTrue(books.isEmpty());
    }
}




